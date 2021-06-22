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
import com.example.carwash.adapter.Usuario.RecyclerViewAdapterServicoRealizadoUsuario;
import com.example.carwash.dao.LavagemDAO;
import com.example.carwash.objeto.Lavagem;

import java.util.ArrayList;
import java.util.List;

public class ServicoRealizadoUsuario extends AppCompatActivity {

    TextView textViewUsuario;
    ImageView imageViewVoltar;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_realizado_usuario);

        TextView textViewUsuario = findViewById(R.id.textViewServicoRealizadoUsuarioUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewServicoRealizadoUsuarioVoltar);
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewServicoRealizadoUsuario);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void listar() {

        LavagemDAO dao = new LavagemDAO(getApplicationContext());
        List<Lavagem> registros = dao.buscarLavagem();

        List<String> ids = new ArrayList<String>();
        List<String> veiculos = new ArrayList<String>();
        List<String> placas = new ArrayList<String>();
        List<String> servicos = new ArrayList<String>();
        List<String> valorServicos = new ArrayList<String>();
        List<String> clientes = new ArrayList<String>();
        List<String> usuarios = new ArrayList<String>();
        List<String> horas = new ArrayList<String>();
        List<String> datas = new ArrayList<String>();

        String[] dados_ids = new String[]{};
        String[] dados_veiculos = new String[]{};
        String[] dados_placas = new String[]{};
        String[] dados_servicos = new String[]{};
        String[] dados_valorServicos = new String[]{};
        String[] dados_clientes = new String[]{};
        String[] dados_usuarios = new String[]{};
        String[] dados_horas = new String[]{};
        String[] dados_datas = new String[]{};

        for(Lavagem busca:registros){
            ids.add(String.valueOf(busca.getIdLavagem()));
            veiculos.add(busca.getVeiculo());
            placas.add(String.valueOf(busca.getPlaca()));
            servicos.add(String.valueOf(busca.getServico()));
            valorServicos.add(String.valueOf(busca.getValorServico()));
            clientes.add(String.valueOf(busca.getCliente()));
            usuarios.add(String.valueOf(busca.getUsuario()));
            horas.add(String.valueOf(busca.getHora()));
            datas.add(String.valueOf(busca.getData()));
        }

        dados_ids = ids.toArray(new String[0]);
        dados_veiculos = veiculos.toArray(new String[0]);
        dados_placas = placas.toArray(new String[0]);
        dados_servicos = servicos.toArray(new String[0]);
        dados_valorServicos = valorServicos.toArray(new String[0]);
        dados_clientes = clientes.toArray(new String[0]);
        dados_usuarios = usuarios.toArray(new String[0]);
        dados_horas = horas.toArray(new String[0]);
        dados_datas = datas.toArray(new String[0]);

        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapterServicoRealizadoUsuario(context, dados_ids, dados_veiculos,
                dados_placas, dados_servicos, dados_valorServicos, dados_clientes, dados_usuarios, dados_horas, dados_datas);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    // Sobrescricao do metodo listar
    @Override
    public void onResume(){
        super.onResume();
        listar();
    }
}

