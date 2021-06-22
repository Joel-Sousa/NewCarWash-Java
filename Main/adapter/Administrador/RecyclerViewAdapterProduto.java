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
import com.example.carwash.view.Administrador.produto.DadosProduto;
import com.example.carwash.view.Administrador.produto.EditarProduto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewAdapterProduto extends RecyclerView.Adapter<RecyclerViewAdapterProduto.ViewHolder>{

    Context context;

    List<String> ids = new ArrayList<String>();
    String[] produtos;
    String[] descricaoProdutos;
    String[] quantidadeprodutos;

    View viewOnCreate;
    ViewHolder viewHolderLocal;

    public RecyclerViewAdapterProduto
            (Context contextRecebido, String[] idsRecebidos, String[] produtosRecebidos,
             String[] descricaoProdutosRecebidos, String[] quantidadeprodutosRecebidos){
        context = contextRecebido;
        ids.addAll(Arrays.asList(idsRecebidos));
        produtos = produtosRecebidos;
        descricaoProdutos = descricaoProdutosRecebidos;
        quantidadeprodutos = quantidadeprodutosRecebidos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //public TextView textId;
        public TextView textProduto;
        //public TextView textDescricaoProduto;
        public TextView textQuantidadeProduto;
        public ImageView imageViewEditar;

        public ViewHolder(View itemView){
            super(itemView);

//            textId = itemView.findViewById(R.id.textViewRecyclerViewProdutoIdProduto);
            textProduto = itemView.findViewById(R.id.textViewRecyclerViewProdutoProduto);
//            textDescricaoProduto = itemView.findViewById(R.id.textViewRecyclerViewProdutoDescricaoProduto);
            textQuantidadeProduto = itemView.findViewById(R.id.textViewRecyclerViewProdutoQuantidadeProduto);
            imageViewEditar = itemView.findViewById(R.id.ImageViewRecyclerViewProdutoEditar);

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
    public RecyclerViewAdapterProduto.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.recyclerview_itens_produto, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterProduto.ViewHolder holder, final int position) {
//        holder.textId.setText(ids.get(position));
        holder.textProduto.setText(produtos[position]);
//        holder.textDescricaoProduto.setText(descricaoProdutos[position]);
        holder.textQuantidadeProduto.setText(quantidadeprodutos[position]);

        // Manda para DadosUsuario
        viewOnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DadosProduto.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("produto", produtos[position]);
                intent.putExtra("descricaoproduto", descricaoProdutos[position]);
                intent.putExtra("quantidadeproduto", quantidadeprodutos[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        // Manda para edicao
        holder.imageViewEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditarProduto.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("produto", produtos[position]);
                intent.putExtra("descricaoproduto", descricaoProdutos[position]);
                intent.putExtra("quantidadeproduto", quantidadeprodutos[position]);
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
