package com.example.carwash.view.Administrador.usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.adapter.Administrador.RecyclerViewAdapterUsuario;
import com.example.carwash.dao.UsuarioDAO;
import com.example.carwash.objeto.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ListagemUsuario extends AppCompatActivity {

    ImageView imageViewVoltar;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_usuario);

        TextView textViewUsuario = findViewById(R.id.textViewListaUsuariosUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewListaUsuariosVoltar);
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUsuario);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void listar() {
        UsuarioDAO dao = new UsuarioDAO(getApplicationContext());
        List<Usuario> registros = dao.buscarUsuarios();

        List<String> ids = new ArrayList<String>();
        List<String> usuarios = new ArrayList<String>();
        List<String> senhas = new ArrayList<String>();
        List<String> perfis = new ArrayList<String>();

        String[] dados_ids = new String[]{};
        String[] dados_usuarios = new String[]{};
        String[] dados_senhas = new String[]{};
        String[] dados_perfis = new String[]{};

        for(Usuario busca:registros){
            ids.add(String.valueOf(busca.getIdUsuario()));
            usuarios.add(busca.getUsuario());
            senhas.add(String.valueOf(busca.getSenha()));
            perfis.add(String.valueOf(busca.getPerfil()));
        }

        dados_ids = ids.toArray(new String[0]);
        dados_usuarios = usuarios.toArray(new String[0]);
        dados_senhas = senhas.toArray(new String[0]);
        dados_perfis = perfis.toArray(new String[0]);

        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapterUsuario(context,
                dados_ids, dados_usuarios, dados_senhas, dados_perfis);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    // Sobrescricao do metodo listar
    @Override
    public void onResume(){
        super.onResume();
        listar();
    }
}
