package com.example.carwash.view.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.dao.ProdutoDAO;

public class DarBaixaProdutos extends AppCompatActivity {

    ImageView imageViewVoltar, imageViewSalvar;
    Spinner spinnerProduto;
    Button buttonDiminuir, buttonAlmentar;
    TextView textViewQuantidade;

    TextView textViewQtd;

    SQLiteDatabase db;
    Cursor c;

    String produtodb;
    int idProduto;
    int novaQuantidade;
    int quantidadedb;

    int quantidade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_baixa_produto);

        TextView textViewUsuario = findViewById(R.id.textViewDarBaixaProdutoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        //textViewId = findViewById(R.id.textView1);
        //textViewDes = findViewById(R.id.textView2);
        textViewQtd = findViewById(R.id.textViewDarBaixaProdutoQuantidadeDb);

        imageViewVoltar = findViewById(R.id.imageViewDarBaixaProdutoVoltar);
        spinnerProduto = findViewById(R.id.spinnerDarBaixaProdutoProduto);
        textViewQuantidade = findViewById(R.id.textViewDarBaixaProdutoQuantidade);
        buttonDiminuir = findViewById(R.id.buttonDarBaixaProdutoDiminuir);
        buttonAlmentar = findViewById(R.id.buttonDarBaixaProdutoAlmentar);
        imageViewSalvar = findViewById(R.id.imageViewDarBaixaProdutoSalvar);

        lista(spinnerProduto);

        textViewQuantidade.setText("0");

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinnerProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                produtodb = spinnerProduto.getSelectedItem().toString();

                db = openOrCreateDatabase("db", Context.MODE_PRIVATE, null);
                c = db.rawQuery("SELECT * FROM produto WHERE produto = ?", new String[]{produtodb});

                c.moveToFirst();

//                textViewId.setText(c.getString(0));
//                textViewDes.setText(c.getString(2));
                textViewQtd.setText(c.getString(3));
                quantidadedb = c.getInt(3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        imageViewSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ok!", Toast.LENGTH_SHORT).show();
                try{
                    novaQuantidade = quantidadedb - quantidade;
                    idProduto = c.getInt(0);
                                        db.execSQL("update produto set quantidadeproduto = '" + novaQuantidade +
                            "' where id = " + idProduto);
                    textViewQuantidade.setText("0");
                    finish();
                }catch (Exception e){

                }

            }
        });

        buttonDiminuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantidade<=0){
                    Toast.makeText(getApplicationContext(), "Digite um numero maior que Zero!", Toast.LENGTH_SHORT).show();
                }else{
                    quantidade--;
                    textViewQuantidade.setText(String.valueOf(quantidade));
                }
                //Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
            }
        });

        buttonAlmentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(quantidadedb<=quantidade){
                        Toast.makeText(getApplicationContext(), "Limite exedido!", Toast.LENGTH_SHORT).show();
                    }else{
                        quantidade++;
                        textViewQuantidade.setText(String.valueOf(quantidade));
                    }
            }
        });
    }

    private void lista(Spinner spinner) {
        ProdutoDAO dao = new ProdutoDAO(getApplication());
        ArrayAdapter<String> adapter_spinner = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dao.buscaProdutos());
        spinnerProduto.setAdapter(adapter_spinner);
        Log.d("tete", spinnerProduto.toString());
    }
}

