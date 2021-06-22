package com.example.carwash.view.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

public class AdicionarVeiculoUsuario extends AppCompatActivity {

    ImageView imageViewVoltar, imageViewSalvar;
    EditText editTextVeiculo,
            editTextPlaca,
            editTextServico,
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
        setContentView(R.layout.activity_adicionar_veiculo_usuario);

        imageViewVoltar = findViewById(R.id.imageViewAdicionarVeiculoUsuarioVoltar);
        editTextVeiculo = findViewById(R.id.editTextAdicionarVeiculoUsuarioVeiculo);
        editTextPlaca = findViewById(R.id.editTextAdicionarVeiculoUsuarioPlaca);
        //editTextServico = findViewById(R.id.editTextAdicionarVeiculoUsuarioServico);
        spinnerServicos = findViewById(R.id.spinnerAdicionarVeiculoUsuarioServico);
        //editTextValorVeiculo = findViewById(R.id.editTextAdicionarVeiculoUsuarioValor);
        textViewValorVeiculo = findViewById(R.id.textViewAdicionarVeiculoUsuarioValor);
        editTextCliente = findViewById(R.id.editTextAdicionarVeiculoUsuarioCliente);
        imageViewSalvar = findViewById(R.id.imageViewAdicionarVeiculoUsuarioSalvar);

        TextView textViewUsuario = findViewById(R.id.textViewAdicionarVeiculoUsuarioUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        spinnerServicos.getSelectedItem();

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
