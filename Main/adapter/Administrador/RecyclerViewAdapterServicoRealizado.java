package com.example.carwash.adapter.Administrador;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carwash.R;
import com.example.carwash.view.Administrador.Outros.DadosServicoRealizado;
import com.example.carwash.view.Administrador.Outros.ExcluirServicoRealizado;
import com.example.carwash.view.Administrador.Outros.ServicoRealizado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewAdapterServicoRealizado extends RecyclerView.Adapter<RecyclerViewAdapterServicoRealizado.ViewHolder>{


    Context context;

    List<String> ids = new ArrayList<String>();
    String[] veiculos;
    String[] placas;
    String[] servicos;
    String[] valorServico;
    String[] clientes;
    String[] usuarios;
    String[] horas;
    String[] datas;

    View viewOnCreate;
    ViewHolder viewHolderLocal;

    public RecyclerViewAdapterServicoRealizado
            (Context contextRecebido, String[] idsRecebidos, String[] veiculosRecebidos,
             String[] placasRecebidas, String[] servicosRecebidos, String[] valorValorServico,
             String[] clientesRecebidos, String[] usuariosRecebidos, String[] horasRecebidas, String[] datasRecebidas){
        context = contextRecebido;
        ids.addAll(Arrays.asList(idsRecebidos));
        veiculos = veiculosRecebidos;
        placas = placasRecebidas;
        servicos = servicosRecebidos;
        valorServico = valorValorServico;
        clientes = clientesRecebidos;
        usuarios = usuariosRecebidos;
        horas = horasRecebidas;
        datas = datasRecebidas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //        public TextView textId;
        public TextView textUsuario;
        //        public TextView textPlaca;
        public TextView textVeiculo;
        //        public TextView textValorVeiculo;
        public TextView textServico;
        public ImageView imageViewExcluir;
        DialogInterface.OnClickListener diDeletar;

        public ViewHolder(View itemView){
            super(itemView);

//            textId = itemView.findViewById(R.id.textViewRecyclerViewVeiculoIdVeiculo);
            textUsuario = itemView.findViewById(R.id.textViewRecyclerViewServicoRealizadoUsuario);
            textVeiculo = itemView.findViewById(R.id.textViewRecyclerViewServicoRealizadoVeiculo);
            textServico = itemView.findViewById(R.id.textViewRecyclerViewServicoRealizadoServico);

//            textPlaca = itemView.findViewById(R.id.textViewRecyclerViewVeiculoPlaca);
//            textServico = itemView.findViewById(R.id.textViewRecyclerViewVeiculoServico);
//            textValorVeiculo = itemView.findViewById(R.id.textViewRecyclerViewVeiculoValorVeiculo);
//            textCliente = itemView.findViewById(R.id.textViewRecyclerViewVeiculoCliente);
            imageViewExcluir = itemView.findViewById(R.id.imageViewServicoRealizadoExcluir);

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
    public RecyclerViewAdapterServicoRealizado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.recyclerview_itens_servicorealizado, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterServicoRealizado.ViewHolder holder, final int position) {
//        holder.textId.setText(ids.get(position));
        holder.textUsuario.setText(usuarios[position]);
        holder.textVeiculo.setText(veiculos[position]);
        holder.textServico.setText(servicos[position]);
//        holder.textPlaca.setText(placas[position]);
//        holder.textServico.setText(servicos[position]);
//        holder.textValorVeiculo.setText(valorVeiculos[position]);
//        holder.textCliente.setText(clientes[position]);

        // Manda para DadosUsuario
        viewOnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DadosServicoRealizado.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("veiculo", veiculos[position]);
                intent.putExtra("placa", placas[position]);
                intent.putExtra("servico", servicos[position]);
                intent.putExtra("valorservico", valorServico[position]);
                intent.putExtra("cliente", clientes[position]);
                intent.putExtra("usuario", usuarios[position]);
                intent.putExtra("hora", horas[position]);
                intent.putExtra("data", datas[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });



        // Manda para edicao
        holder.imageViewExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExcluirServicoRealizado.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("veiculo", veiculos[position]);
                intent.putExtra("placa", placas[position]);
                intent.putExtra("servico", servicos[position]);
                intent.putExtra("valorservico", valorServico[position]);
                intent.putExtra("cliente", clientes[position]);
                intent.putExtra("usuario", usuarios[position]);
                intent.putExtra("hora", horas[position]);
                intent.putExtra("data", datas[position]);
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
