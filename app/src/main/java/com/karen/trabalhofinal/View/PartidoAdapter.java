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
import com.karen.trabalhofinal.R;

import java.util.List;

public class PartidoAdapter extends RecyclerView.Adapter<PartidoAdapter.PartidosHolder> {

    private List<Partido> partidos = DataStore.sharedInstance().getPartidos();

    @NonNull
    @Override
    public PartidosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleview_partidos, viewGroup, false);

        return new PartidosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidosHolder pastidosHolder, int position) {

        Partido partido = partidos.get(position);

        if (partido.sigla != null) pastidosHolder.sigla.setText(partido.sigla);
        if (partido.nome != null) pastidosHolder.nome.setText(String.valueOf(partido.nome));
    }

    @Override
    public int getItemCount() {

        return partidos.size();
    }

    public class PartidosHolder extends RecyclerView.ViewHolder {

        TextView sigla;
        TextView nome;
        ImageView foto;

        public PartidosHolder(@NonNull View itemView) {
            super(itemView);

            sigla = itemView.findViewById(R.id.txtSiglaPartido);
            nome = itemView.findViewById(R.id.txtNomePartido);
            foto = itemView.findViewById(R.id.imgDeputado);
        }
    }
}
