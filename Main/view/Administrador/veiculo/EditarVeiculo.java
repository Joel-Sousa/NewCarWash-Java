package com.example.carwash.view.Administrador.veiculo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.dao.VeiculoDAO;
import com.example.carwash.objeto.Veiculo;

public class EditarVeiculo extends AppCompatActivity {

    ImageView imageViewVoltar;
    TextView textViewId, textViewServico, textViewValor;
    EditText editTextVeiculo,
             editTextPlaca,


             editTextCliente;
    ImageView imageViewEditar, imageViewExcluir;
    String apagarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_veiculo);

        TextView textViewUsuario = findViewById(R.id.textViewEditarVeiculoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewEditarVeiculoVoltar);
        textViewId = findViewById(R.id.textViewEditarVeiculoIdVeiculo);
        editTextVeiculo = findViewById(R.id.editTextEditarVeiculoVeiculo);
        editTextPlaca = findViewById(R.id.editTextEditarVeiculoPlaca);
        textViewServico = findViewById(R.id.textViewEditarVeiculoServico);
        textViewValor = findViewById(R.id.textViewEditarVeiculoValor);
        editTextCliente = findViewById(R.id.editTextEditarVeiculoCliente);
        imageViewEditar = findViewById(R.id.imageViewEditarVeiculoEditar);
        imageViewExcluir = findViewById(R.id.imageViewEditarVeiculoExcluir);

        Intent intent = getIntent();

        textViewId.setText(intent.getStringExtra("id"));
        editTextVeiculo.setText(intent.getStringExtra("veiculo"));
        editTextPlaca.setText(intent.getStringExtra("placa"));
        textViewServico.setText(intent.getStringExtra("servico"));
        textViewValor.setText(intent.getStringExtra("valorveiculo"));
        editTextCliente.setText(intent.getStringExtra("cliente"));

        apagarId = intent.getStringExtra("id");

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageViewEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VeiculoDAO dao = new VeiculoDAO(getApplication());
                Veiculo veiculo = new Veiculo();

                veiculo.setIdVeiculo(Integer.valueOf(textViewId.getText().toString()));
                veiculo.setVeiculo(editTextVeiculo.getText().toString());
                veiculo.setPlaca(editTextPlaca.getText().toString());
                veiculo.setServico(textViewServico.getText().toString());
                veiculo.setValorVeiculo(Integer.valueOf(textViewValor.getText().toString()));
                veiculo.setCliente(editTextCliente.getText().toString());

                dao.atualizar(veiculo);
                dao.close();
                Toast.makeText(getApplicationContext(), "Editado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        imageViewExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirmacaoExclusao = new AlertDialog.Builder(EditarVeiculo.this);
                confirmacaoExclusao.setTitle("Atencao!");
                confirmacaoExclusao.setMessage("Tem certeza que deseja apagar Esse Veiculo?");
                confirmacaoExclusao.setCancelable(false);
                confirmacaoExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        VeiculoDAO dao = new VeiculoDAO(getApplication());
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
