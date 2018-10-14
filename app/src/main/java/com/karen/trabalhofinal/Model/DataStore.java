package com.karen.trabalhofinal.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.karen.trabalhofinal.Interfaces.LoadReceiverDelegate;
import com.karen.trabalhofinal.R;
import com.squareup.picasso.Picasso;

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
import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static DataStore instance = null;

    private List<Deputado> deputados = new ArrayList<>();
    private List<Despesa> despesas = new ArrayList<>();
    private List<Partido> partidos = new ArrayList<>();
    private Context context;
    private FirebaseAuth mAuth;

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

                    new LoadDespesas(delegate).execute("https://dadosabertos.camara.leg.br/api/v2/deputados/" + id + "/despesas?ordem=ASC&ordenarPor=ano");
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

                    Despesa newDespesa = new Despesa(valorDocumento, valorLiquido, tipoDespesa);
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
