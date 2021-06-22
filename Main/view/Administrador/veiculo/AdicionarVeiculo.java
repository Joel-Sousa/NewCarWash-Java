package com.example.carwash.view.Administrador.veiculo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.dao.VeiculoDAO;
import com.example.carwash.objeto.Veiculo;

import java.util.ArrayList;

public class AdicionarVeiculo extends AppCompatActivity {

    ImageView imageViewVoltar, imageViewSalvar;
    EditText editTextVeiculo,
             editTextPlaca,
             editTextValorVeiculo,
             editTextCliente;
    TextView textViewValorVeiculo;
    Spinner spinnerServicos;

    SQLiteDatabase db;
    Cursor c;
    String precodb;

    String tipoServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_veiculo);

        TextView textViewUsuario = findViewById(R.id.textViewAdicionarVeiculoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewAdicionarVeiculoVoltar);
        editTextVeiculo = findViewById(R.id.editTextAdicionarVeiculoVeiculo);
        editTextPlaca = findViewById(R.id.editTextAdicionarVeiculoPlaca);
        //editTextServico = findViewById(R.id.editTextAdicionarVeiculoServico);
        spinnerServicos = findViewById(R.id.spinnerAdicionarVeiculoServico);
        textViewValorVeiculo =findViewById(R.id.textViewAdicionarVeiculoValorVeiculo);
        //editTextValorVeiculo = findViewById(R.id.editTextAdicionarVeiculoValorVeiculo);
        editTextCliente = findViewById(R.id.editTextAdicionarVeiculoCliente);
        imageViewSalvar = findViewById(R.id.imageViewAdicionarVeiculoSalvar);

        lista(spinnerServicos);

        spinnerServicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // editTextServico.setText(spinnerServicos.getSelectedItem().toString());

                precodb = spinnerServicos.getSelectedItem().toString();

                db = openOrCreateDatabase("db", Context.MODE_PRIVATE, null);
                c = db.rawQuery("SELECT * FROM servico WHERE servico = ?", new String[]{precodb});

                c.moveToFirst();

                textViewValorVeiculo.setText(c.getString(3));

                //editTextValorVeiculo.setText("10");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageViewSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextVeiculo.getText().toString().equals("") ||
                        editTextPlaca.getText().toString().equals("") ||
                            spinnerServicos.getSelectedItem().toString().equals(".:Selecione:.") ||
                                //editTextValorVeiculo.getText().toString().equals("") ||
                                    editTextCliente.getText().toString().equals(""))){
                    VeiculoDAO dao = new VeiculoDAO(getApplication());
                    Veiculo veiculo = new Veiculo();

                    veiculo.setVeiculo(editTextVeiculo.getText().toString());
                    veiculo.setPlaca(editTextPlaca.getText().toString());
                    veiculo.setServico(spinnerServicos.getSelectedItem().toString());
                    veiculo.setValorVeiculo(Integer.valueOf(textViewValorVeiculo.getText().toString()));
                    veiculo.setCliente(editTextCliente.getText().toString());

                    dao.inserir(veiculo);
                    dao.close();
                    finish();

                    Toast.makeText(getApplicationContext(), "Inserido!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha corretamente todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void lista(Spinner spinner) {
        VeiculoDAO dao = new VeiculoDAO(getApplication());
        ArrayAdapter<String> adapter_spinner = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dao.buscaServicos());
        spinnerServicos.setAdapter(adapter_spinner);
    }
}
