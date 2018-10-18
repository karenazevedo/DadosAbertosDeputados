package com.karen.trabalhofinal.Controller;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.karen.trabalhofinal.Interfaces.LoadReceiverDelegate;
import com.karen.trabalhofinal.Model.DataStore;
import com.karen.trabalhofinal.Model.InfoDeputado;
import com.karen.trabalhofinal.R;
import com.karen.trabalhofinal.View.DespesaAdapter;


public class DespesaActivity extends AppCompatActivity implements LoadReceiverDelegate {

    private RecyclerView recyclerView;
    private DespesaAdapter adapter;

    @Override
    protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_despesa);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Despesas");

        long id =  getIntent().getLongExtra("id", 0);

        DataStore.sharedInstance().setContextDespesas(this, this, id);

        setContentView(R.layout.activity_despesa);
        recyclerView = findViewById(R.id.listDespesas);

        adapter = new DespesaAdapter();
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void setLoadStatus(boolean status) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void processFinish(InfoDeputado result) {

    }
}
