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
import com.karen.trabalhofinal.R;
import com.karen.trabalhofinal.View.PartidoAdapter;

public class PartidosActivity extends AppCompatActivity implements LoadReceiverDelegate {

    private RecyclerView recyclerView;
    private PartidoAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_partido);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Partidos");

    }

    @Override
    protected void onStart() {
        super.onStart();

        DataStore.sharedInstance().setContextPartidos(this, this);

        setContentView(R.layout.activity_partido);
        recyclerView = findViewById(R.id.listPartidos);

        adapter = new PartidoAdapter();
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void setLoadStatus(boolean status) {
        adapter.notifyDataSetChanged();
    }
}
