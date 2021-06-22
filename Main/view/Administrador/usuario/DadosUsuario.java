package com.example.carwash.view.Administrador.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.carwash.R;

public class DadosUsuario extends AppCompatActivity {

    TextView textViewDadosUsuarioIdUsuario,
             textViewDadosUsuarioUsuario,
             textViewDadosUsuarioSenha,
             textViewDadosUsuarioPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_usuario);

        textViewDadosUsuarioIdUsuario = findViewById(R.id.textViewDadosUsuarioId);
        textViewDadosUsuarioUsuario = findViewById(R.id.textViewDadosUsuarioUsuario);
        textViewDadosUsuarioSenha = findViewById(R.id.textViewDadosUsuarioSenha);
        textViewDadosUsuarioPerfil = findViewById(R.id.textViewDadosUsuarioPerfil);

        Intent intent = getIntent();

        textViewDadosUsuarioIdUsuario.setText(intent.getStringExtra("id"));
        textViewDadosUsuarioUsuario.setText(intent.getStringExtra("usuario"));
        textViewDadosUsuarioSenha.setText(intent.getStringExtra("senha"));
        textViewDadosUsuarioPerfil.setText(intent.getStringExtra("perfil"));
    }
}
