package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.dao.UsuarioDAO;
import com.example.carwash.view.HomeAdministrador;
import com.example.carwash.view.HomeUsuario;

public class MainActivity extends AppCompatActivity {

    // Declaracao das variaveis
    EditText editTextUsuario, editTextSenha;
    Button buttonLogin, buttonU;
    Intent intentAdministrador, intentUsuario;
    ImageView tc, cw;

    SQLiteDatabase db;

    int indice, id;

    Cursor cursor;

    public static String usuarioB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            db = openOrCreateDatabase("db", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS usuario (" +
                    "id Integer PRIMARY KEY AUTOINCREMENT, " +
                    "usuario TEXT, senha TEXT, " +
                    "perfil TEXT)");

            db.execSQL("CREATE TABLE IF NOT EXISTS produto " +
                    "(id Integer primary key autoincrement, produto TEXT, descricaoproduto TEXT, quantidadeproduto Integer);");

            db.execSQL("CREATE TABLE IF NOT EXISTS servico " +
                    "(id Integer primary key autoincrement, servico TEXT, descricaoservico TEXT, valorservico Integer);");

            db.execSQL("CREATE TABLE IF NOT EXISTS veiculo " +
                    "(id Integer primary key autoincrement, veiculo TEXT, placa TEXT, servico TEXT, " +
                    "valorveiculo Integer, cliente TEXT);");

            db.execSQL("CREATE TABLE IF NOT EXISTS lavagem " +
                    "(id Integer primary key autoincrement, veiculo TEXT, placa TEXT, servico TEXT, " +
                    "valorservico Integer, cliente TEXT, usuario TEXT, hora DATETIME, data TEXT); ");

            db.execSQL("INSERT INTO usuario (usuario, senha, perfil) " +
                    "SELECT * FROM (SELECT 'root', '0000', 'Administrador') AS tmp " +
                    "WHERE NOT EXISTS (SELECT usuario FROM usuario WHERE usuario = 'root') LIMIT 1;");

            db.execSQL("INSERT INTO servico (servico, descricaoservico, valorservico) " +
                    "SELECT * FROM (SELECT '.:Selecione:.', 'null', '0') AS tmp " +
                    "WHERE NOT EXISTS (SELECT servico FROM servico WHERE servico = '.:Selecione:.') LIMIT 1;");

            db.execSQL("INSERT INTO produto (produto, descricaoproduto, quantidadeproduto) " +
                    "SELECT * FROM (SELECT '.:Selecione:.', 'null', '0') AS tmp " +
                    "WHERE NOT EXISTS (SELECT produto FROM produto WHERE produto = '.:Selecione:.') LIMIT 1;");



        }catch(Exception e){

        }

        tc = findViewById(R.id.imageViewTC);
        cw = findViewById(R.id.imageViewCW);

        // Ligacao das variaveis com o xml
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonLogin = findViewById(R.id.buttonLogin);
        //buttonU = findViewById(R.id.buttonU);

        // Intent do administrador
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsuario.getText().toString();
                String senha = editTextSenha.getText().toString();

                try{



                    db = openOrCreateDatabase("db", Context.MODE_PRIVATE, null);
                    cursor = db.rawQuery("SELECT * FROM usuario WHERE usuario = ? and senha = ?",
                            new String[]{usuario, senha});

                    if(cursor.getCount() > 0){
                        cursor.moveToFirst();

                        indice = 1;

                        id = cursor.getInt(0);
                        usuarioB = cursor.getString(1);
                        String senhaB = cursor.getString(2);
                        String perfilB = cursor.getString(3);

                        if(perfilB.equals("Administrador")){
                            intentAdministrador = new Intent(MainActivity.this, HomeAdministrador.class);
//                            intentAdministrador.putExtras(params);
                            startActivity(intentAdministrador);
                        }else{
                            intentUsuario = new Intent(MainActivity.this, HomeUsuario.class);
                            startActivity(intentUsuario);
                        }

                    }else {
                        Toast.makeText(getApplicationContext(), "Nenhum Registro!", Toast.LENGTH_SHORT).show();
                        //editTextUsuario.setText("");
                        //editTextSenha.setText("");

                    }                        cursor.moveToFirst();
                }catch (Exception e){

                }
            }
        });

        // Intent do usuario
        cw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentAdministrador = new Intent(MainActivity.this, HomeAdministrador.class);
                startActivity(intentAdministrador);
            }
        });

        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUsuario = new Intent(MainActivity.this, HomeUsuario.class);
                startActivity(intentUsuario);
            }
        });
    }

}

