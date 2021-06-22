package com.example.carwash.view.Administrador.Outros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;

public class FazerRelatorio extends AppCompatActivity {

    ImageView imageViewVoltar;
    TextView textViewServicosRealizados, textViewTotalArecadado;

    SQLiteDatabase db;
    Cursor c, c1;
    String totalArecadadodb, servicoRealizadodb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_relatorio);

        TextView textViewUsuario = findViewById(R.id.textViewFazerRelatorioUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewFazerRelatorioVoltar);
        textViewServicosRealizados = findViewById(R.id.textViewFazerRelatorioServicosRealizados);
        textViewTotalArecadado = findViewById(R.id.textViewFazerRelatorioTotalArecadado);

        db = openOrCreateDatabase("db", Context.MODE_PRIVATE, null);

        c = db.rawQuery("SELECT count(*) contagem FROM lavagem ", new String[]{});
        c.moveToFirst();
        servicoRealizadodb = c.getString(c.getColumnIndex("contagem"));

        c1 = db.rawQuery("SELECT sum(valorservico) valores FROM lavagem ", new String[]{});
        c1.moveToFirst();
        totalArecadadodb = c1.getString(c1.getColumnIndex("valores"));

        textViewServicosRealizados.setText(servicoRealizadodb);
        textViewTotalArecadado.setText(totalArecadadodb);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
