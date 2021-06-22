package com.example.carwash.view.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;

public class DadosVeiculoUsuario extends AppCompatActivity {

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
        setContentView(R.layout.activity_dados_veiculo_usuario);

        TextView textViewUsuario = findViewById(R.id.textViewDadosVeiculoUsuarioUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewDadosVeiculoUsuarioVoltar);
        textViewDadosVeiculoIdVeiculo = findViewById(R.id.textViewDadosVeiculoUsuarioIdVeiculo);
        textViewDadosVeiculoVeiculo = findViewById(R.id.textViewDadosVeiculoUsuarioVeiculo);
        textViewDadosVeiculoPlaca = findViewById(R.id.textViewDadosVeiculoUsuarioPlaca);
        textViewDadosVeiculoServico = findViewById(R.id.textViewDadosVeiculoUsuarioServico);
        textViewDadosVeiculoValorVeiculo = findViewById(R.id.textViewDadosVeiculoUsuarioValorVeiculo);
        textViewDadosVeiculoCliente = findViewById(R.id.textViewDadosVeiculoUsuarioCliente);

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
