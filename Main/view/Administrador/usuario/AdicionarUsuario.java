package com.example.carwash.view.Administrador.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.dao.UsuarioDAO;
import com.example.carwash.objeto.Usuario;

public class AdicionarUsuario extends AppCompatActivity {

    ImageView imageViewVoltar;
    EditText editTextUsuario, editTextSenha;
    RadioButton radioButtonUsuario,radioButtonAdministrador;
    ImageView imageViewSalvar;
    String perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_usuario);

        TextView textViewUsuario = findViewById(R.id.textViewAdicionarUsuarioUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewAdicionarUsuarioVoltar);
        editTextUsuario = findViewById(R.id.editTextAdicionarUsuarioUsuario);
        editTextSenha = findViewById(R.id.editTextAdicionarUsuarioSenha);
        radioButtonUsuario = findViewById(R.id.radioButtonAdicionarUsuarioPerfilUsuario);
        radioButtonAdministrador = findViewById(R.id.radioButtonAdicionarUsuarioPerfilAdministrador);
        imageViewSalvar = findViewById(R.id.imageViewAdicionarUsuarioSalvar);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageViewSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextUsuario.getText().toString().equals("") ||
                        editTextSenha.getText().toString().equals(""))){

                    if(radioButtonUsuario.isChecked()) perfil = "Usuario";
                    if(radioButtonAdministrador.isChecked()) perfil = "Administrador";

                    UsuarioDAO dao = new UsuarioDAO(getApplication());
                    Usuario usuario = new Usuario();

                    usuario.setUsuario(editTextUsuario.getText().toString());
                    usuario.setSenha(editTextSenha.getText().toString());
                    usuario.setPerfil(perfil);

                    dao.inserir(usuario);
                    dao.close();
                    finish();

                    Toast.makeText(getApplicationContext(), "Inserido!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
