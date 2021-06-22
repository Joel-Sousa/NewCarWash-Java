package com.example.carwash.view.Administrador.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;

public class DadosProduto extends AppCompatActivity {

    ImageView imageViewVoltar;
    TextView textViewDadosProdutoIdProduto,
             textViewDadosProdutoProduto,
             textViewDadosProdutoDescricaoProduto,
             textViewDadosProdutoQuantidadeProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_produto);

        TextView textViewUsuario = findViewById(R.id.textViewDadosProdutoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewDadosProdutoVoltar);
        textViewDadosProdutoIdProduto = findViewById(R.id.textViewDadosProdutoIdProduto);
        textViewDadosProdutoProduto = findViewById(R.id.textViewDadosProdutoProduto);
        textViewDadosProdutoDescricaoProduto = findViewById(R.id.textViewDadosProdutoDescricaoProduto);
        textViewDadosProdutoQuantidadeProduto = findViewById(R.id.textViewDadosProdutoQuantidadeProduto);

        Intent intent = getIntent();

        textViewDadosProdutoIdProduto.setText(intent.getStringExtra("id"));
        textViewDadosProdutoProduto.setText(intent.getStringExtra("produto"));
        textViewDadosProdutoDescricaoProduto.setText(intent.getStringExtra("descricaoproduto"));
        textViewDadosProdutoQuantidadeProduto.setText(intent.getStringExtra("quantidadeproduto"));

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
