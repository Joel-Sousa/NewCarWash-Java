package com.example.carwash.view.Administrador.servico;

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
import com.example.carwash.adapter.Administrador.RecyclerViewAdapterServico;
import com.example.carwash.dao.ServicoDAO;
import com.example.carwash.objeto.Servico;

import java.util.ArrayList;
import java.util.List;

public class ListagemServico extends AppCompatActivity {

    ImageView imageViewVoltar;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_servico);

        TextView textViewUsuario = findViewById(R.id.textViewListaServicosUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewListaServicosVoltar);
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewServico);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void listar() {
        ServicoDAO dao = new ServicoDAO(getApplicationContext());
        List<Servico> registros = dao.buscarServicos();

        List<String> ids = new ArrayList<String>();
        List<String> servicos = new ArrayList<String>();
        List<String> descricaoServicos = new ArrayList<String>();
        List<String> valorServicos = new ArrayList<String>();

        String[] dados_ids = new String[]{};
        String[] dados_servicos = new String[]{};
        String[] dados_descricaoServicos = new String[]{};
        String[] dados_valorServicos = new String[]{};

        for(Servico busca:registros){
            ids.add(String.valueOf(busca.getIdServico()));
            servicos.add(busca.getServico());
            descricaoServicos.add(String.valueOf(busca.getDescricaoServico()));
            valorServicos.add(String.valueOf(busca.getValorServico()));
        }

        dados_ids = ids.toArray(new String[0]);
        dados_servicos = servicos.toArray(new String[0]);
        dados_descricaoServicos = descricaoServicos.toArray(new String[0]);
        dados_valorServicos = valorServicos.toArray(new String[0]);

        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapterServico(context,
                dados_ids, dados_servicos, dados_descricaoServicos, dados_valorServicos);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    // Sobrescricao do metodo listar
    @Override
    public void onResume(){
        super.onResume();
        listar();
    }
}
