package com.example.carwash.view.Administrador.servico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;

public class DadosServico extends AppCompatActivity {

    ImageView imageViewVoltar;

    TextView textViewDadosServicoIdServico,
             textViewDadosServicoServico,
             textViewDadosServicoDescricaoServico,
             textViewDadosServicoValorServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_servico);

        TextView textViewUsuario = findViewById(R.id.textViewDadosServicoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewDadosServicoVoltar);
        textViewDadosServicoIdServico = findViewById(R.id.textViewDadosServicosIdServico);
        textViewDadosServicoServico = findViewById(R.id.textViewDadosServicosServico);
        textViewDadosServicoDescricaoServico = findViewById(R.id.textViewDadosServicosDescricaoServico);
        textViewDadosServicoValorServico = findViewById(R.id.textViewDadosServicosValorServico);

        Intent intent = getIntent();

        textViewDadosServicoIdServico.setText(intent.getStringExtra("id"));
        textViewDadosServicoServico.setText(intent.getStringExtra("servico"));
        textViewDadosServicoDescricaoServico.setText(intent.getStringExtra("descricaoservico"));
        textViewDadosServicoValorServico.setText(intent.getStringExtra("valorservico"));

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
