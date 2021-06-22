package com.example.carwash.view.Administrador.veiculo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.view.HomeAdministrador;

import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CheckVeiculo extends AppCompatActivity {

    TextView textView0,
    textViewT1,
    textViewT2,
    textViewT3,
    textViewT4,
    textViewT5,
    textViewT6,
    textViewT7,
    textViewT8;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_veiculo);

        SimpleDateFormat formatoDataAtual = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        String dataFormatada = formatoDataAtual.format(data);

        SimpleDateFormat formatoHoraAtual = new SimpleDateFormat("HH:mm");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        String horaFormatada = formatoHoraAtual.format(hora);

            textViewT1 = findViewById(R.id.textViewT1);
            textViewT2 = findViewById(R.id.textViewT2);
            textViewT3 = findViewById(R.id.textViewT3);
            textViewT4 = findViewById(R.id.textViewT4);
            textViewT5 = findViewById(R.id.textViewT5);
            textViewT6 = findViewById(R.id.textViewT6);
            textViewT7 = findViewById(R.id.textViewT7);
            textViewT8 = findViewById(R.id.textViewT8);

        Intent intent = getIntent();

        String id = "", veiculo = "", placa = "", servico = "", valorservico = "", cliente = "",
                usuario = "", horaAtual = "", dataAtual = "";

        id = intent.getStringExtra("id");
        veiculo = intent.getStringExtra("veiculo");
        placa = intent.getStringExtra("placa");
        servico =intent.getStringExtra("servico");
        valorservico = intent.getStringExtra("valorservico");
        cliente = intent.getStringExtra("cliente");
        usuario = MainActivity.usuarioB;
        horaAtual = horaFormatada;
        dataAtual = dataFormatada;

        textViewT1.setText(id);

        try {

            db = openOrCreateDatabase("db", Context.MODE_PRIVATE, null);
            db.execSQL("INSERT INTO lavagem " +
                    "(id, veiculo, placa, servico, valorservico, cliente, usuario, hora, data) " +
                    "values(null, ?, ?, ?, ?, ?, ?, ?, ?)",
                    new String[]{veiculo, placa, servico, valorservico, cliente, usuario, horaAtual, dataAtual});

            db.execSQL("DELETE FROM veiculo WHERE id = ?;", new String[]{id});

        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "Concluido!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
