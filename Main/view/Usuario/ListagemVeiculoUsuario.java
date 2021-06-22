package com.example.carwash.view.Usuario;

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
import com.example.carwash.adapter.Usuario.RecyclerViewAdapterVeiculoUsuario;
import com.example.carwash.dao.VeiculoDAO;
import com.example.carwash.objeto.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class ListagemVeiculoUsuario extends AppCompatActivity {

    ImageView imageViewVoltar;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_veiculo_usuario);

        TextView textViewUsuario = findViewById(R.id.textViewistagemVeiculoUsuarioUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewListagemVeiculoUsuarioVoltar);
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewVeiculoUsuario);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void listar() {

        VeiculoDAO dao = new VeiculoDAO(getApplicationContext());
        List<Veiculo> registros = dao.buscarVeiculos();

        List<String> ids = new ArrayList<String>();
        List<String> veiculos = new ArrayList<String>();
        List<String> placas = new ArrayList<String>();
        List<String> servicos = new ArrayList<String>();
        List<String> valorVeiculos = new ArrayList<String>();
        List<String> clientes = new ArrayList<String>();

        String[] dados_ids = new String[]{};
        String[] dados_veiculos = new String[]{};
        String[] dados_placas = new String[]{};
        String[] dados_servicos = new String[]{};
        String[] dados_valorVeiculos = new String[]{};
        String[] dados_clientes = new String[]{};

        for(Veiculo busca:registros){
            ids.add(String.valueOf(busca.getIdVeiculo()));
            veiculos.add(busca.getVeiculo());
            placas.add(String.valueOf(busca.getPlaca()));
            servicos.add(String.valueOf(busca.getServico()));
            valorVeiculos.add(String.valueOf(busca.getValorVeiculo()));
            clientes.add(String.valueOf(busca.getCliente()));
        }

        dados_ids = ids.toArray(new String[0]);
        dados_veiculos = veiculos.toArray(new String[0]);
        dados_placas = placas.toArray(new String[0]);
        dados_servicos = servicos.toArray(new String[0]);
        dados_valorVeiculos = valorVeiculos.toArray(new String[0]);
        dados_clientes = clientes.toArray(new String[0]);

        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapterVeiculoUsuario(context, dados_ids, dados_veiculos,
                dados_placas, dados_servicos, dados_valorVeiculos, dados_clientes);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    // Sobrescricao do metodo listar
    @Override
    public void onResume(){
        super.onResume();
        listar();
    }
}
