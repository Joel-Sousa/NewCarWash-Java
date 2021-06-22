package com.example.carwash.view.Administrador.Outros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;

public class DadosServicoRealizado extends AppCompatActivity {

    ImageView imageViewVoltar;
    TextView textViewDadosVeiculoIdVeiculo,
            textViewDadosVeiculoVeiculo,
            textViewDadosVeiculoPlaca,
            textViewDadosVeiculoServico,
            textViewDadosVeiculoValorServico,
            textViewDadosVeiculoCliente,
            textViewDadosVeiculoUsuario,
            textViewDadosVeiculoHora,
            textViewDadosVeiculoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_servico_realizado);

        TextView textViewUsuario = findViewById(R.id.textViewDadosServicoRealizadoUser);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewDadosServicoRealizadoVoltar);
        textViewDadosVeiculoIdVeiculo = findViewById(R.id.textViewDadosServicoRealizadoId);
        textViewDadosVeiculoVeiculo = findViewById(R.id.textViewDadosServicoRealizadoVeiculo);
        textViewDadosVeiculoPlaca = findViewById(R.id.textViewDadosServicoRealizadoPlaca);
        textViewDadosVeiculoServico = findViewById(R.id.textViewDadosServicoRealizadoServico);
        textViewDadosVeiculoValorServico = findViewById(R.id.textViewDadosServicoRealizadoValor);
        textViewDadosVeiculoCliente = findViewById(R.id.textViewDadosServicoRealizadoCliente);
        textViewDadosVeiculoUsuario = findViewById(R.id.textViewDadosServicoRealizadoUsuario);
        textViewDadosVeiculoHora = findViewById(R.id.textViewDadosServicoRealizadoHora);
        textViewDadosVeiculoData = findViewById(R.id.textViewDadosServicoRealizadoData);

        Intent intent = getIntent();

        textViewDadosVeiculoIdVeiculo.setText(intent.getStringExtra("id"));
        textViewDadosVeiculoVeiculo.setText(intent.getStringExtra("veiculo"));
        textViewDadosVeiculoPlaca.setText(intent.getStringExtra("placa"));
        textViewDadosVeiculoServico.setText(intent.getStringExtra("servico"));
        textViewDadosVeiculoValorServico.setText(intent.getStringExtra("valorservico"));
        textViewDadosVeiculoCliente.setText(intent.getStringExtra("cliente"));
        textViewDadosVeiculoUsuario.setText(intent.getStringExtra("usuario"));
        textViewDadosVeiculoHora.setText(intent.getStringExtra("hora"));
        textViewDadosVeiculoData.setText(intent.getStringExtra("data"));

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
