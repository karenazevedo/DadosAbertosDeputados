package com.karen.trabalhofinal.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karen.trabalhofinal.Model.DataStore;
import com.karen.trabalhofinal.Model.Partido;
import com.karen.trabalhofinal.Model.Ranking;
import com.karen.trabalhofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingHolder> {

    private List<Ranking> ranking = DataStore.sharedInstance().getRanking();

    @NonNull
    @Override
    public RankingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleview_ranking, viewGroup, false);

        return new RankingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingHolder rankingHolder, int position) {

        Ranking obj = ranking.get(position);

        rankingHolder.despesa.setText(String.valueOf(obj.despesa));
        rankingHolder.tipo.setText(obj.tipoDespesa);
        rankingHolder.nome.setText(obj.nome);

    }

    @Override
    public int getItemCount() {

        return ranking.size();
    }

    public class RankingHolder extends RecyclerView.ViewHolder {

        TextView despesa;
        TextView tipo;
        TextView nome;

        public RankingHolder(@NonNull View itemView) {
            super(itemView);

            despesa = itemView.findViewById(R.id.txtValorDespesa);
            tipo = itemView.findViewById(R.id.txtTipoDespesa);
            nome = itemView.findViewById(R.id.txtNomeDeputado);
        }
    }
}
