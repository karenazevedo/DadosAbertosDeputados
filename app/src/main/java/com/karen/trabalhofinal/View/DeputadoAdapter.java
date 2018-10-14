package com.karen.trabalhofinal.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karen.trabalhofinal.Model.DataStore;
import com.karen.trabalhofinal.Model.Deputado;
import com.karen.trabalhofinal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DeputadoAdapter extends RecyclerView.Adapter<DeputadoAdapter.DeputadoHolder> {

    private List<Deputado> deputados = DataStore.sharedInstance().getDeputados();

    @NonNull
    @Override
    public DeputadoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleview_deputados, viewGroup, false);

        return new DeputadoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeputadoHolder deputadoHolder, int position) {

        Deputado deputado = deputados.get(position);

        deputadoHolder.txtName.setText(deputado.getNome());
        deputadoHolder.txtPartido.setText(String.valueOf(deputado.getSiglaPartido()));

        Picasso.get()
                .load(deputado.getUrlFoto())
                .resize(150, 200)
                .centerCrop()
                .into(deputadoHolder.img);
    }

    @Override
    public int getItemCount() {

        return deputados.size();
    }

    public class DeputadoHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtPartido;
        ImageView img;

        public DeputadoHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPartido = itemView.findViewById(R.id.txtPartido);
            img = itemView.findViewById(R.id.imgDeputado);
        }
    }
}
