package com.karen.trabalhofinal.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karen.trabalhofinal.Model.DataStore;
import com.karen.trabalhofinal.Model.Despesa;
import com.karen.trabalhofinal.R;

import java.util.List;

public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.DespesaHolder> {

    private List<Despesa> despesas = DataStore.sharedInstance().getDespesas();

    @NonNull
    @Override
    public DespesaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleview_despesas, viewGroup, false);

        return new DespesaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DespesaHolder despesaHolder, int position) {

        Despesa despesa = despesas.get(position);

        if (despesa.tipoDespesa != null) despesaHolder.txtTipoDocumento.setText(despesa.tipoDespesa);
        if (despesa.valorDocumento != null) despesaHolder.txtValor.setText(String.valueOf(despesa.valorDocumento));
        if (despesa.dataDocumento != null) despesaHolder.txtDataDocumento.setText(String.valueOf(despesa.dataDocumento));
    }

    @Override
    public int getItemCount() {

        return despesas.size();
    }

    public class DespesaHolder extends RecyclerView.ViewHolder {

        TextView txtTipoDocumento;
        TextView txtValor;
        TextView txtDataDocumento;

        public DespesaHolder(@NonNull View itemView) {
            super(itemView);

            txtTipoDocumento = itemView.findViewById(R.id.txtTipoDespesa);
            txtValor = itemView.findViewById(R.id.txtValor);
            txtDataDocumento = itemView.findViewById(R.id.txtDataDocumento);
        }
    }
}
