package com.example.carwash.view.Administrador.usuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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

public class EditarUsuario extends AppCompatActivity {

    ImageView imageViewVoltar;
    TextView textViewId;
    EditText editTextUsuario, editTextSenha;
    ImageView imageViewEditar, imageViewExcluir;
    RadioButton radioButtonUsuario, radioButtonAdministrador;
    String perfil, apagarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        TextView textViewUsuario = findViewById(R.id.textViewEditarUsuarioUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewEditarUsuarioVoltar);
        textViewId = findViewById(R.id.textViewEditarUsuarioId);
        editTextUsuario = findViewById(R.id.editTextEditarUsuarioUsuario);
        editTextSenha = findViewById(R.id.editTextEditarUsuarioSenha);
        radioButtonUsuario = findViewById(R.id.radioButtonEditarUsuarioUsuario);
        radioButtonAdministrador = findViewById(R.id.radioButtonEditarUsuarioAdministrador);
        imageViewEditar = findViewById(R.id.imageViewEditarUsuarioEditar);
        imageViewExcluir = findViewById(R.id.imageViewEditarUsuarioExcluir);

        Intent intent = getIntent();

        textViewId.setText(intent.getStringExtra("id"));
        editTextUsuario.setText(intent.getStringExtra("usuario"));
        editTextSenha.setText(intent.getStringExtra("senha"));

        String radioBT = intent.getStringExtra("perfil");

        apagarId = intent.getStringExtra("id");

            if(radioBT.equals("Usuario")) radioButtonUsuario.toggle();
            if(radioBT.equals("Administrador")) radioButtonAdministrador.toggle();

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageViewEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonUsuario.isChecked()) perfil = "Usuario";
                if (radioButtonAdministrador.isChecked()) perfil = "Administrador";

                UsuarioDAO dao = new UsuarioDAO(getApplication());
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(Integer.valueOf(textViewId.getText().toString()));
                usuario.setUsuario(editTextUsuario.getText().toString());
                usuario.setSenha(editTextSenha.getText().toString());
                usuario.setPerfil(perfil);
                dao.atualizar(usuario);
                dao.close();
                Toast.makeText(getApplicationContext(), "Editado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        imageViewExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder confirmacaoExclusao = new AlertDialog.Builder(EditarUsuario.this);
                confirmacaoExclusao.setTitle("Atencao!");
                confirmacaoExclusao.setMessage("Tem certeza que deseja apagar ?");
                confirmacaoExclusao.setCancelable(false);
                confirmacaoExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UsuarioDAO dao = new UsuarioDAO(getApplication());
                        dao.apagar(apagarId);
                        Toast.makeText(getApplicationContext(), "Excluido!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                confirmacaoExclusao.setNegativeButton("Nao", null);
                confirmacaoExclusao.create().show();


            }
        });
    }
}
