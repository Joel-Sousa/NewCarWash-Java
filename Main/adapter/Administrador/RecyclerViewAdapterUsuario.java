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
import com.example.carwash.view.Administrador.usuario.EditarUsuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewAdapterUsuario extends RecyclerView.Adapter<RecyclerViewAdapterUsuario.ViewHolder>{

    Context context;

    List<String> ids = new ArrayList<String>();
    String[] usuarios;
    String[] senhas;
    String[] perfis;

    View viewOnCreate;
    ViewHolder viewHolderLocal;

    public RecyclerViewAdapterUsuario
            (Context contextRecebido, String[] idsRecebidos, String[] usuariosRecebidos,
             String[] senhasRecebidas, String[] perfisRecebidos){
        context = contextRecebido;
        ids.addAll(Arrays.asList(idsRecebidos));
        usuarios = usuariosRecebidos;
        senhas = senhasRecebidas;
        perfis = perfisRecebidos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        public TextView textId;
        public TextView textUsuario;
//        public TextView textSenha;
        public TextView textPerfil;
        public ImageView imageViewEditar;

        public ViewHolder(View itemView){
            super(itemView);

//            textId = itemView.findViewById(R.id.textViewRecyclerViewUsuarioIdUsuario);
            textUsuario = itemView.findViewById(R.id.textViewRecyclerViewUsuarioUsuario);
//            textSenha = itemView.findViewById(R.id.textViewRecyclerViewUsuarioSenha);
            textPerfil = itemView.findViewById(R.id.textViewRecyclerViewUsuarioPerfil);
            imageViewEditar = itemView.findViewById(R.id.ImageViewRecyclerViewUsuarioEditar);

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
    public RecyclerViewAdapterUsuario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.recyclerview_itens_usuario, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterUsuario.ViewHolder holder, final int position) {
//        holder.textId.setText(ids.get(position));
        holder.textUsuario.setText(usuarios[position]);
//        holder.textSenha.setText(senhas[position]);
        holder.textPerfil.setText(perfis[position]);

        // Manda para DadosUsuario
//        viewOnCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DadosUsuario.class);
//                intent.putExtra("id", ids.get(position));
//                intent.putExtra("usuario", usuarios[position]);
//                intent.putExtra("senha", senhas[position]);
//                intent.putExtra("perfil", perfis[position]);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                v.getContext().startActivity(intent);
//            }
//        });

        // Manda para edicao
        holder.imageViewEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditarUsuario.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("usuario", usuarios[position]);
                intent.putExtra("senha", senhas[position]);
                intent.putExtra("perfil", perfis[position]);
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




