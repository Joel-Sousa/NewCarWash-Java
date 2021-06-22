package com.example.carwash.view.Administrador.produto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.adapter.Administrador.RecyclerViewAdapterProduto;
import com.example.carwash.dao.ProdutoDAO;
import com.example.carwash.objeto.Produto;

import java.util.ArrayList;
import java.util.List;

public class ListagemProduto extends AppCompatActivity {

    ImageView imageViewVoltar;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_produto);

        TextView textViewUsuario = findViewById(R.id.textViewListagemProdutoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewListaProdutoVoltar);
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProduto);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void listar() {
        ProdutoDAO dao = new ProdutoDAO(getApplicationContext());
        List<Produto> registros = dao.buscarProdutos();

        List<String> ids = new ArrayList<String>();
        List<String> produtos = new ArrayList<String>();
        List<String> descricaoProdutos = new ArrayList<String>();
        List<String> quantidadeProdutos = new ArrayList<String>();

        String[] dados_ids = new String[]{};
        String[] dados_produtos = new String[]{};
        String[] dados_descricaoProdutos = new String[]{};
        String[] dados_quantidadeProdutos = new String[]{};

        for(Produto busca:registros){
            ids.add(String.valueOf(busca.getIdProduto()));
            produtos.add(busca.getProduto());
            descricaoProdutos.add(String.valueOf(busca.getDescricaoProduto()));
            quantidadeProdutos.add(String.valueOf(busca.getQuantidadeProduto()));
        }

        dados_ids = ids.toArray(new String[0]);
        dados_produtos = produtos.toArray(new String[0]);
        dados_descricaoProdutos = descricaoProdutos.toArray(new String[0]);
        dados_quantidadeProdutos = quantidadeProdutos.toArray(new String[0]);

        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapterProduto(context, dados_ids, dados_produtos,
                dados_descricaoProdutos, dados_quantidadeProdutos);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    // Sobrescricao do metodo listar
    @Override
    public void onResume(){
        super.onResume();
        listar();
    }
}
