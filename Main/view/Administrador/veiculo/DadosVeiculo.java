package com.example.carwash.view.Administrador.veiculo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;

public class DadosVeiculo extends AppCompatActivity {

    ImageView imageViewVoltar;
    TextView textViewDadosVeiculoIdVeiculo,
             textViewDadosVeiculoVeiculo,
             textViewDadosVeiculoPlaca,
             textViewDadosVeiculoServico,
             textViewDadosVeiculoValorVeiculo,
             textViewDadosVeiculoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_veiculo);

        TextView textViewUsuario = findViewById(R.id.textViewDadosVeiculoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewDadosVeiculoVoltar);
        textViewDadosVeiculoIdVeiculo = findViewById(R.id.textViewDadosVeiculoIdVeiculo);
        textViewDadosVeiculoVeiculo = findViewById(R.id.textViewDadosVeiculoVeiculo);
        textViewDadosVeiculoPlaca = findViewById(R.id.textViewDadosVeiculoPlaca);
        textViewDadosVeiculoServico = findViewById(R.id.textViewDadosVeiculoServico);
        textViewDadosVeiculoValorVeiculo = findViewById(R.id.textViewDadosVeiculoValorVeiculo);
        textViewDadosVeiculoCliente = findViewById(R.id.textViewDadosVeiculoCliente);

        Intent intent = getIntent();

        textViewDadosVeiculoIdVeiculo.setText(intent.getStringExtra("id"));
        textViewDadosVeiculoVeiculo.setText(intent.getStringExtra("veiculo"));
        textViewDadosVeiculoPlaca.setText(intent.getStringExtra("placa"));
        textViewDadosVeiculoServico.setText(intent.getStringExtra("servico"));
        textViewDadosVeiculoValorVeiculo.setText(intent.getStringExtra("valorveiculo"));
        textViewDadosVeiculoCliente.setText(intent.getStringExtra("cliente"));

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
