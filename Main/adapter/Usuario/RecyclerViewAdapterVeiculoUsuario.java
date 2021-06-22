package com.example.carwash.adapter.Usuario;

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
import com.example.carwash.view.Administrador.veiculo.CheckVeiculo;
import com.example.carwash.view.Administrador.veiculo.DadosVeiculo;
import com.example.carwash.view.Administrador.veiculo.EditarVeiculo;
import com.example.carwash.view.Usuario.DadosVeiculoUsuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewAdapterVeiculoUsuario extends RecyclerView.Adapter<RecyclerViewAdapterVeiculoUsuario.ViewHolder>{

    Context context;

    List<String> ids = new ArrayList<String>();
    String[] veiculos;
    String[] placas;
    String[] servicos;
    String[] valorVeiculos;
    String[] clientes;

    View viewOnCreate;
    ViewHolder viewHolderLocal;

    public RecyclerViewAdapterVeiculoUsuario
            (Context contextRecebido, String[] idsRecebidos, String[] veiculosRecebidos,
             String[] placasRecebidas, String[] servicosRecebidos, String[] valorVeiculosRecebido,
             String[] clientesRecebidos){
        context = contextRecebido;
        ids.addAll(Arrays.asList(idsRecebidos));
        veiculos = veiculosRecebidos;
        placas = placasRecebidas;
        servicos = servicosRecebidos;
        valorVeiculos = valorVeiculosRecebido;
        clientes = clientesRecebidos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //        public TextView textId;
        public TextView textVeiculo;
        //        public TextView textPlaca;
        public TextView textServico;
        //        public TextView textValorVeiculo;
        public TextView textCliente;
        public ImageView imageViewCheck;


        public ViewHolder(View itemView){
            super(itemView);

//            textId = itemView.findViewById(R.id.textViewRecyclerViewVeiculoIdVeiculo);
            textVeiculo = itemView.findViewById(R.id.textViewRecyclerViewVeiculoVeiculoUsuarioVeiculo);
//            textPlaca = itemView.findViewById(R.id.textViewRecyclerViewVeiculoPlaca);
            textServico = itemView.findViewById(R.id.textViewRecyclerViewVeiculoVeiculoUsuarioServico);
//            textValorVeiculo = itemView.findViewById(R.id.textViewRecyclerViewVeiculoValorVeiculo);
            textCliente = itemView.findViewById(R.id.textViewRecyclerViewVeiculoVeiculoUsuarioCliente);
            imageViewCheck = itemView.findViewById(R.id.imageViewRecyclerViewVeiculoVeiculoUsuarioCheck);

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
    public RecyclerViewAdapterVeiculoUsuario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.recyclerview_itens_veiculousuario, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterVeiculoUsuario.ViewHolder holder, final int position) {
//        holder.textId.setText(ids.get(position));
        holder.textVeiculo.setText(veiculos[position]);
//        holder.textPlaca.setText(placas[position]);
        holder.textServico.setText(servicos[position]);
//        holder.textValorVeiculo.setText(valorVeiculos[position]);
        holder.textCliente.setText(clientes[position]);

        // Manda para DadosUsuario
        viewOnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DadosVeiculoUsuario.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("veiculo", veiculos[position]);
                intent.putExtra("placa", placas[position]);
                intent.putExtra("servico", servicos[position]);
                intent.putExtra("valorveiculo", valorVeiculos[position]);
                intent.putExtra("cliente", clientes[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        holder.imageViewCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CheckVeiculo.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("veiculo", veiculos[position]);
                intent.putExtra("placa", placas[position]);
                intent.putExtra("servico", servicos[position]);
                intent.putExtra("valorservico", valorVeiculos[position]);
                intent.putExtra("cliente", clientes[position]);
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
