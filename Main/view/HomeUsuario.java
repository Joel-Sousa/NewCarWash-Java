package com.example.carwash.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.view.Usuario.DarBaixaProdutos;
import com.example.carwash.R;
import com.example.carwash.view.Usuario.AdicionarVeiculoUsuario;
import com.example.carwash.view.Usuario.ListagemVeiculoUsuario;
import com.example.carwash.view.Usuario.ServicoRealizadoUsuario;

public class HomeUsuario extends AppCompatActivity {

    Button  buttonAdicionarLavagem,
            buttonListargemVeiculo,
            buttonServicosRealizados,
            buttonDarBaixaProdutos;
    ImageView imageViewSair;
    Intent  intentAdicionarLavagem,
            intentListagemVeiculo,
            intentServicosRealizados,
            intentDarBaixaProdutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);

        TextView textViewUsuario = findViewById(R.id.textViewHomeUsuarioUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        buttonAdicionarLavagem = findViewById(R.id.buttonHomeUsuarioAdicionarLavagem);
        buttonListargemVeiculo = findViewById(R.id.buttonHomeUsuarioListagemVeiculo);
        buttonServicosRealizados = findViewById(R.id.buttonHomeUsuarioServicoRealizado);
        buttonDarBaixaProdutos = findViewById(R.id.buttonHomeUsuarioDarBaixaProdutos);

        buttonAdicionarLavagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentAdicionarLavagem = new Intent(HomeUsuario.this, AdicionarVeiculoUsuario.class);
                startActivity(intentAdicionarLavagem);
            }
        });

        buttonListargemVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentListagemVeiculo = new Intent(HomeUsuario.this, ListagemVeiculoUsuario.class);
                startActivity(intentListagemVeiculo);
            }
        });

        buttonDarBaixaProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentDarBaixaProdutos = new Intent(HomeUsuario.this, DarBaixaProdutos.class);
                startActivity(intentDarBaixaProdutos);
            }
        });

        buttonServicosRealizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentServicosRealizados = new Intent(HomeUsuario.this, ServicoRealizadoUsuario.class);
                startActivity(intentServicosRealizados);
            }
        });

        imageViewSair = findViewById(R.id.imageViewHomeUsuarioSair);

        imageViewSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmacaoExclusao = new AlertDialog.Builder(HomeUsuario.this);
                confirmacaoExclusao.setTitle("Atencao!");
                confirmacaoExclusao.setMessage("Tem certeza que deseja Sair?");
                confirmacaoExclusao.setCancelable(false);
                confirmacaoExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.usuarioB = "";
                        finish();
                    }
                });
                confirmacaoExclusao.setNegativeButton("Nao", null);
                confirmacaoExclusao.create().show();
            }
        });

    }
}
