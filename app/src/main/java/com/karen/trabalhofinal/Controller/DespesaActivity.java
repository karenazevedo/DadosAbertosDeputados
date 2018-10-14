package com.karen.trabalhofinal.Controller;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.karen.trabalhofinal.Model.DataStore;
import com.karen.trabalhofinal.Model.Despesa;
import com.karen.trabalhofinal.R;
import com.karen.trabalhofinal.View.DespesaAdapter;

import java.util.List;

public class DespesaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DespesaAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_despesa);

        DataStore.sharedInstance().setContext(this, null, 1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Despesas");

    }

    @Override
    protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_despesa);
        recyclerView = findViewById(R.id.listDespesas);

        adapter = new DespesaAdapter();
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }
}
