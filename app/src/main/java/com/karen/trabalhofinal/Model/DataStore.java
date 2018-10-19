package com.karen.trabalhofinal.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karen.trabalhofinal.Interfaces.LoadReceiverDelegate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DataStore {

    private static DataStore instance = null;

    private List<Deputado> deputados = new ArrayList<>();
    private List<Despesa> despesas = new ArrayList<>();
    private List<Partido> partidos = new ArrayList<>();
    private List<Ranking> ranking = new ArrayList<>();
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    protected DataStore() {}

    public static DataStore sharedInstance() {

        if (instance == null)
            instance = new DataStore();

        return instance;
    }

    public FirebaseAuth connectFirebase() {
        mAuth = FirebaseAuth.getInstance();
        return mAuth;
    }

    public void signOutFirebase() {
        mAuth.signOut();
    }

    public void setContext(Context context, LoadReceiverDelegate delegate, Integer page) {

        this.context = context;
        if (delegate != null) new LoadDeputados(delegate, page).execute("https://dadosabertos.camara.leg.br/api/v2/deputados?pagina="+ page + "&ordem=ASC&ordenarPor=nome");
    }

    public void setContextPartidos(Context context, LoadReceiverDelegate delegate) {

        this.context = context;
        if (delegate != null) new LoadPartidos(delegate).execute("https://dadosabertos.camara.leg.br/api/v2/partidos?itens=1000&ordem=ASC&ordenarPor=sigla");
    }

    public void setContextDespesas(Context context, LoadReceiverDelegate delegate, long id) {

        this.context = context;

        if (delegate != null) new LoadDespesas(delegate).execute("https://dadosabertos.camara.leg.br/api/v2/deputados/" + id + "/despesas?itens=1000&ordem=ASC&ordenarPor=ano");
    }

    public void setContextInfoDeputado(Context context, LoadReceiverDelegate delegate, Long id) {

        this.context = context;
        if (delegate != null) new LoadInfoDeputado(delegate).execute("https://dadosabertos.camara.leg.br/api/v2/deputados/"+ id);
    }

    public void setContextRanking(Context context, LoadReceiverDelegate delegate) {
        this.context = context;
        getDataFirebase(delegate);
    }

    private void getDataFirebase(final LoadReceiverDelegate delegate) {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        ranking.clear();

        myRef.child("ranking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {


                for (DataSnapshot messageSnapshot: snapshot.getChildren()) {
                    Double despesa = Double.parseDouble(messageSnapshot.child("despesa").getValue().toString());
                    String tipo = (String) messageSnapshot.child("tipoDespesa").getValue();
                    String data = (String) messageSnapshot.child("dataDocumento").getValue();
                    String nome = (String) messageSnapshot.child("nome").getValue();

                    Ranking rank = new Ranking(Long.parseLong(messageSnapshot.getKey()), despesa, tipo, data, nome);
                    ranking.add(rank);

                }

                Collections.sort(ranking, new Comparator<Ranking>() {

                    @Override
                    public int compare(Ranking o1, Ranking o2) {
                        return -1 * Double.compare(o1.despesa, o2.despesa);
                    }

                });

                delegate.setLoadStatus(true);

                Log.d("Ranking", "values." + ranking);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Ranking", "Failed to read value.", databaseError.toException());
            }
        });

    }

    public List<Ranking> getRanking() {

        return ranking;
    }

    public List<Deputado> getDeputados() {

        return deputados;
    }

    public List<Despesa> getDespesas() {

        return despesas;
    }

    public List<Partido> getPartidos() {

        return partidos;
    }

    public FirebaseAuth getConnFirebase() {

        return mAuth;
    }

    public Deputado getDeputado(int position) {

        return deputados.get(position);
    }

    public List<Deputado> findByName(String name, LoadReceiverDelegate delegate) {

        deputados.clear();
        new LoadDeputados(delegate, 1).execute("https://dadosabertos.camara.leg.br/api/v2/deputados?pagina="+ 1 + "&nome=" + name + "&ordem=ASC&ordenarPor=nome");

        List<Deputado> newDeputados = this.getDeputados();

        for (Deputado d : newDeputados) {
            newDeputados.add(d);
        }
        return newDeputados;
    }


    //funções de acesso à API web
    private class LoadDeputados extends AsyncTask<String, Void, String> {

        private LoadReceiverDelegate delegate;
        private Integer page;

        public LoadDeputados(LoadReceiverDelegate delegate, Integer page) {

            this.delegate = delegate;
            this.page = page;
        }

        @Override
        protected String doInBackground(String... urls) {

            return backgroudAPI(urls);
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);

            try {
                JSONObject json = new JSONObject(jsonStr);
                JSONArray deputadosArray = json.getJSONArray("dados");
                for (int i=0; i<deputadosArray.length(); i++) {
                    JSONObject deputado = deputadosArray.getJSONObject(i);
                    Integer id = deputado.getInt("id");
                    String nome = deputado.getString("nome");
                    String partido = deputado.getString("siglaPartido");
                    String foto = deputado.getString("urlFoto");

                    Deputado newDeputado = new Deputado(nome, partido, foto);
                    newDeputado.setId(id);
                    deputados.add(newDeputado);
                }
                delegate.setLoadStatus(true);
            } catch (JSONException e) {
                e.printStackTrace();
                delegate.setLoadStatus(false);
            }
        }
    }

    //funções de acesso à API web
    private class LoadDespesas extends AsyncTask<String, Void, String> {

        private LoadReceiverDelegate delegate;

        public LoadDespesas(LoadReceiverDelegate delegate) {

            this.delegate = delegate;
        }

        @Override
        protected String doInBackground(String... urls) {

            return backgroudAPI(urls);
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);

            try {
                JSONObject json = new JSONObject(jsonStr);
                JSONArray despesasArray = json.getJSONArray("dados");
                for (int i=0; i<despesasArray.length(); i++) {
                    JSONObject despesa = despesasArray.getJSONObject(i);
                    Long id = despesa.getLong("idDocumento");
                    Long valorLiquido = despesa.getLong("valorLiquido");
                    Long valorDocumento = despesa.getLong("valorDocumento");
                    String tipoDespesa = despesa.getString("tipoDespesa");
                    String dataDocumento = despesa.getString("dataDocumento");

                    Despesa newDespesa = new Despesa(valorDocumento, valorLiquido, tipoDespesa, dataDocumento);
                    newDespesa.setIdDocumento(id);
                    despesas.add(newDespesa);
                }
                delegate.setLoadStatus(true);
            } catch (JSONException e) {
                e.printStackTrace();
                delegate.setLoadStatus(false);
            }
        }
    }

    //funções de acesso à API web
    private class LoadPartidos extends AsyncTask<String, Void, String> {

        private LoadReceiverDelegate delegate;

        public LoadPartidos(LoadReceiverDelegate delegate) {

            this.delegate = delegate;
        }

        @Override
        protected String doInBackground(String... urls) {

            return backgroudAPI(urls);
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);

            try {
                JSONObject json = new JSONObject(jsonStr);
                JSONArray pastidosArray = json.getJSONArray("dados");
                for (int i=0; i<pastidosArray.length(); i++) {
                    JSONObject partido = pastidosArray.getJSONObject(i);
                    Long id = partido.getLong("id");
                    String nome = partido.getString("nome");
                    String sigla = partido.getString("sigla");
                    String uri = partido.getString("uri");

                    Partido newPartido = new Partido(sigla, nome, uri);
                    newPartido.id = id;
                    partidos.add(newPartido);
                }
                delegate.setLoadStatus(true);
            } catch (JSONException e) {
                e.printStackTrace();
                delegate.setLoadStatus(false);
            }
        }
    }

    //funções de acesso à API web
    private class LoadInfoDeputado extends AsyncTask<String, Void, String> {

        private LoadReceiverDelegate delegate;

        public LoadInfoDeputado(LoadReceiverDelegate delegate) {

            this.delegate = delegate;
        }

        @Override
        protected String doInBackground(String... urls) {

            return backgroudAPI(urls);
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);

            try {
                JSONObject json = new JSONObject(jsonStr);
                JSONObject dadosDeputado = json.getJSONObject("dados");

                Long id = dadosDeputado.getLong("id");
                String nomeCivil = dadosDeputado.getString("nomeCivil");

                String dataNascimento = dadosDeputado.getString("dataNascimento");

                String municipioNascimento = dadosDeputado.getString("municipioNascimento");
                String ufNascimento = dadosDeputado.getString("ufNascimento");
                String escolaridade = dadosDeputado.getString("escolaridade");

                InfoDeputado info = new InfoDeputado(nomeCivil, dataNascimento, municipioNascimento, ufNascimento, escolaridade);
                info.id = id;

                delegate.processFinish(info);

            } catch (JSONException e) {
                e.printStackTrace();
                delegate.setLoadStatus(false);
            }
        }
    }

    private String backgroudAPI(String... urls) {

        String jsonStr = "";

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(45000);
            connection.setReadTimeout(30000);
            connection.setDoInput(true);
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                jsonStr += line;
                line = reader.readLine();
            }
            reader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStr;
    }

}
