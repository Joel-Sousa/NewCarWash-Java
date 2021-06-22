package com.example.carwash.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.view.Administrador.Outros.FazerRelatorio;
import com.example.carwash.view.Administrador.Outros.ServicoRealizado;
import com.example.carwash.view.Administrador.produto.AdicionarProduto;
import com.example.carwash.view.Administrador.produto.ListagemProduto;
import com.example.carwash.view.Administrador.servico.AdicionarServico;
import com.example.carwash.view.Administrador.servico.ListagemServico;
import com.example.carwash.view.Administrador.usuario.AdicionarUsuario;
import com.example.carwash.view.Administrador.usuario.ListagemUsuario;
import com.example.carwash.view.Administrador.veiculo.AdicionarVeiculo;
import com.example.carwash.view.Administrador.veiculo.ListagemVeiculo;

public class HomeAdministrador extends AppCompatActivity {

    ImageView imageViewSair;

    Button  buttonAdicionarVeiculo,
            buttonAdicionarProduto,
            buttonAdicionarServico,
            buttonAdicionarUsuario,
            buttonFazerRelatorio,
            buttonListagemVeiculo,
            buttonListagemProduto,
            buttonListagemServico,
            buttonListagemUsuario,
            buttonServicoRealizado,
            buttonSair;

    Intent  intentAdicionarVeiculo,
            intentAdicionarProduto,
            intentAdicionarUsuario,
            intentAdicionarServico,
            intentFazerRelatorio,
            intentListagemVeiculo,
            intentListagemProduto,
            intentListagemServico,
            intentListagemUsuario,
            intentServicoRealizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_administrador);

        TextView textViewUsuario = findViewById(R.id.textViewHomeAdministradorUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        // Buttons
        imageViewSair = findViewById(R.id.imageViewHomeAdministradorSair);
        buttonAdicionarUsuario = findViewById(R.id.buttonHomeAdministradorAdicionarUsuario);
        buttonAdicionarProduto = findViewById(R.id.buttonHomeAdministradorAdicionarProduto);
        buttonAdicionarVeiculo = findViewById(R.id.buttonHomeAdministradorAdicionarVeiculo);
        buttonAdicionarServico = findViewById(R.id.buttonHomeAdministradorAdicionarServico);
        buttonListagemUsuario = findViewById(R.id.buttonHomeAdministradorListagemUsuario);
        buttonListagemProduto = findViewById(R.id.buttonHomeAdministradorListagemProduto);
        buttonListagemVeiculo = findViewById(R.id.buttonHomeAdministradorListagemVeiculo);
        buttonListagemServico = findViewById(R.id.buttonHomeAdministradorListagemServico);

        buttonServicoRealizado = findViewById(R.id.buttonHomeAdministradorServicoRealizado);
        buttonFazerRelatorio = findViewById(R.id.buttonHomeAdministradorFazerRelatorio);

        imageViewSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirmacaoExclusao = new AlertDialog.Builder(HomeAdministrador.this);
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

        buttonAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentAdicionarProduto = new Intent(HomeAdministrador.this, AdicionarProduto.class);
                startActivity(intentAdicionarProduto);
            }
        });

        buttonAdicionarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentAdicionarUsuario = new Intent(HomeAdministrador.this, AdicionarUsuario.class);
                startActivity(intentAdicionarUsuario);
            }
        });

        buttonAdicionarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentAdicionarVeiculo = new Intent(HomeAdministrador.this, AdicionarVeiculo.class);
                startActivity(intentAdicionarVeiculo);
            }
        });

        buttonAdicionarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentAdicionarServico = new Intent(HomeAdministrador.this, AdicionarServico.class);
                startActivity(intentAdicionarServico);
            }
        });
        // Listagem
        buttonListagemProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentListagemProduto = new Intent(HomeAdministrador.this, ListagemProduto.class);
                startActivity(intentListagemProduto);
            }
        });

        buttonListagemUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentListagemUsuario = new Intent(HomeAdministrador.this, ListagemUsuario.class);
                startActivity(intentListagemUsuario);
            }
        });

        buttonListagemVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentListagemVeiculo = new Intent(HomeAdministrador.this, ListagemVeiculo.class);
                startActivity(intentListagemVeiculo);
            }
        });

        buttonListagemServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentListagemServico = new Intent(HomeAdministrador.this, ListagemServico.class);
                startActivity(intentListagemServico);
            }
        });

        buttonServicoRealizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentServicoRealizado = new Intent(HomeAdministrador.this, ServicoRealizado.class);
                startActivity(intentServicoRealizado);
            }
        });

        buttonFazerRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFazerRelatorio = new Intent(HomeAdministrador.this, FazerRelatorio.class);
                startActivity(intentFazerRelatorio);
            }
        });

    }


}
