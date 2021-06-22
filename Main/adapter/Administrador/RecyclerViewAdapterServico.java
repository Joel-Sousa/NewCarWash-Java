package com.example.carwash.adapter.Administrador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carwash.R;
import com.example.carwash.view.Administrador.servico.DadosServico;
import com.example.carwash.view.Administrador.servico.EditarServico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewAdapterServico extends RecyclerView.Adapter<RecyclerViewAdapterServico.ViewHolder>{

    Context context;

    List<String> ids = new ArrayList<String>();
    String[] servicos;
    String[] descricaoServicos;
    String[] valorServicos;

    View viewOnCreate;
    ViewHolder viewHolderLocal;

    public RecyclerViewAdapterServico
            (Context contextRecebido, String[] idsRecebidos, String[] servicosRecebidos,
             String[] descricaoServicosRecebidos, String[] valorServicosRecebidos){
        context = contextRecebido;
        ids.addAll(Arrays.asList(idsRecebidos));
        servicos = servicosRecebidos;
        descricaoServicos = descricaoServicosRecebidos;
        valorServicos = valorServicosRecebidos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        public TextView textId;
        public TextView textServico;
//        public TextView textDescricaoServico;
        public TextView textValorServico;
        public ImageView imageViewEditar;

        public ViewHolder(View itemView){
            super(itemView);

//            textId = itemView.findViewById(R.id.textViewRecyclerViewServicoIdServico);
            textServico = itemView.findViewById(R.id.textViewRecyclerViewServicoServico);
//            textDescricaoServico = itemView.findViewById(R.id.textViewRecyclerViewServicoDescricaoServico);
            textValorServico = itemView.findViewById(R.id.textViewRecyclerViewServicoValorServico);
            imageViewEditar = itemView.findViewById(R.id.ImageViewRecyclerViewServicoEditar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("Testando...", "Click");
                }
            });
        }
        @Override
        public void onClick(View v){

        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapterServico.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.recyclerview_itens_servico, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterServico.ViewHolder holder, final int position) {
//        holder.textId.setText(ids.get(position));
        holder.textServico.setText(servicos[position]);
//        holder.textDescricaoServico.setText(descricaoServicos[position]);
        holder.textValorServico.setText(valorServicos[position]);

        // Manda para DadosUsuario
        viewOnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DadosServico.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("servico", servicos[position]);
                intent.putExtra("descricaoservico", descricaoServicos[position]);
                intent.putExtra("valorservico", valorServicos[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        // Manda para edicao
        holder.imageViewEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditarServico.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("servico", servicos[position]);
                intent.putExtra("descricaoservico", descricaoServicos[position]);
                intent.putExtra("valorservico", valorServicos[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }
}
