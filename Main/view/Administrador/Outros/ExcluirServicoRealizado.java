package com.example.carwash.view.Administrador.Outros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.carwash.R;
import com.example.carwash.dao.LavagemDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExcluirServicoRealizado extends AppCompatActivity {

    TextView textView0,
            textViewE1,
            textViewE2,
            textViewE3,
            textViewE4,
            textViewE5,
            textViewE6,
            textViewE7,
            textViewE8,
            textViewE9;
    //textViewE1

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_servico_realizado);

        SimpleDateFormat formatoDataAtual = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        String dataFormatada = formatoDataAtual.format(data);

        SimpleDateFormat formatoHoraAtual = new SimpleDateFormat("HH:mm");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        String horaFormatada = formatoHoraAtual.format(hora);

//        textViewE1 = findViewById(R.id.textViewE1);
//        textViewE2 = findViewById(R.id.textViewE2);
//        textViewE3 = findViewById(R.id.textViewE3);
//        textViewE4 = findViewById(R.id.textViewE4);
//        textViewE5 = findViewById(R.id.textViewE5);
//        textViewE6 = findViewById(R.id.textViewE6);
//        textViewE7 = findViewById(R.id.textViewE7);
//        textViewE8 = findViewById(R.id.textViewE8);
//        textViewE9 = findViewById(R.id.textViewE9);


        AlertDialog.Builder confirmacaoExclusao = new AlertDialog.Builder(ExcluirServicoRealizado.this);
        confirmacaoExclusao.setTitle("Atencao!");
        confirmacaoExclusao.setMessage("Tem certeza que deseja apagar ?");
        confirmacaoExclusao.setCancelable(false);
        confirmacaoExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();

//        textViewE1.setText(intent.getStringExtra("id"));
                String apagarId = intent.getStringExtra("id");

                LavagemDAO dao = new LavagemDAO(getApplication());
                dao.apagar(apagarId);
                finish();
            }
        });
        confirmacaoExclusao.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        confirmacaoExclusao.create().show();



    }
}
