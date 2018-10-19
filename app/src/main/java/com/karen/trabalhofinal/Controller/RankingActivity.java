package com.karen.trabalhofinal.Controller;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.karen.trabalhofinal.Interfaces.LoadReceiverDelegate;
import com.karen.trabalhofinal.Model.DataStore;
import com.karen.trabalhofinal.Model.InfoDeputado;
import com.karen.trabalhofinal.R;
import com.karen.trabalhofinal.View.RankingAdapter;


public class RankingActivity extends AppCompatActivity implements LoadReceiverDelegate {

    private RecyclerView recyclerView;
    private RankingAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);



    }

    @Override
    protected void onStart() {
        super.onStart();

        DataStore.sharedInstance().setContextRanking(this, this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ranking de Despesas - 2017");

        setContentView(R.layout.activity_ranking);
        recyclerView = findViewById(R.id.listRanking);

        adapter = new RankingAdapter();
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
