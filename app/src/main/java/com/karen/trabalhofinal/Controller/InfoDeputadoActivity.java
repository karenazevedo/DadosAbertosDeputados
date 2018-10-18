package com.karen.trabalhofinal.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.karen.trabalhofinal.Interfaces.LoadReceiverDelegate;
import com.karen.trabalhofinal.Model.DataStore;
import com.karen.trabalhofinal.Model.InfoDeputado;

import com.karen.trabalhofinal.R;


public class InfoDeputadoActivity extends AppCompatActivity implements LoadReceiverDelegate {

    private TextView nome;
    private TextView nomeCivil;
    private TextView dataNasc;
    private TextView municipioNasc;
    private TextView ufNasc;
    private TextView escolaridade;
    private TextView partido;
    private Long id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    @Override
    protected void onStart() {
        super.onStart();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Informaçoes do Deputado");

        id = getIntent().getLongExtra("id", 0);

        DataStore.sharedInstance().setContextInfoDeputado(this, this, id);

        setContentView(R.layout.activity_info_deputado);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
                        .build();
            }
        });


        FloatingActionButton desp = (FloatingActionButton) findViewById(R.id.desp);

        desp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoDeputadoActivity.this, DespesaActivity.class);

                intent.putExtra("id", id);

                startActivity(intent);
            }
        });

    }

    @Override
    public void setLoadStatus(boolean status) {

    }

    @Override
    public void processFinish(InfoDeputado result) {

        nome = findViewById(R.id.txtName);
        nome.setText(getIntent().getStringExtra("nome"));

        partido = findViewById(R.id.txtPartido);
        partido.setText(getIntent().getStringExtra("partido"));

        nomeCivil = findViewById(R.id.txtNomeCivil);
        municipioNasc = findViewById(R.id.txtMunicipioNasc);
        dataNasc = findViewById(R.id.txtDataNasc);
        escolaridade = findViewById(R.id.txtEscolaridade);
        ufNasc = findViewById(R.id.txtUfNasc);

        nomeCivil.setText(result.nomeCivil);
        municipioNasc.setText(result.municipioNascimento);
        ufNasc.setText(result.ufNascimento);
        escolaridade.setText(result.escolaridade);
        if (result.dataNascimento != null) this.dataNasc.setText(result.dataNascimento.toString());
    }
}
