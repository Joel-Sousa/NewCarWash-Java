package com.example.carwash.view.Administrador.servico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.dao.ServicoDAO;
import com.example.carwash.objeto.Servico;

public class EditarServico extends AppCompatActivity {

    ImageView imageViewVoltar;
    TextView textViewId;
    EditText editTextServico, editTextDescricaoServico, editTextValorServico;
    ImageView imageViewEditar, imageViewExcluir;
    String apagarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servico);

        TextView textViewUsuario = findViewById(R.id.textViewEditarServicoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewEditarServicoVoltar);
        textViewId = findViewById(R.id.textViewEditarServicoIdServico);
        editTextServico = findViewById(R.id.editTextEditarServicoServico);
        editTextDescricaoServico = findViewById(R.id.editTextEditarServicoDescricaoServico);
        editTextValorServico = findViewById(R.id.editTextEditarServicoValorServico);
        imageViewEditar = findViewById(R.id.imageViewEditarServicoEditar);
        imageViewExcluir = findViewById(R.id.imageViewEditarServicoExcluir);

        Intent intent = getIntent();

        textViewId.setText(intent.getStringExtra("id"));
        editTextServico.setText(intent.getStringExtra("servico"));
        editTextDescricaoServico.setText(intent.getStringExtra("descricaoservico"));
        editTextValorServico.setText(intent.getStringExtra("valorservico"));

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
                ServicoDAO dao = new ServicoDAO(getApplication());
                Servico servico = new Servico();

                servico.setIdServico(Integer.valueOf(textViewId.getText().toString()));
                servico.setServico(editTextServico.getText().toString());
                servico.setDescricaoServico(editTextDescricaoServico.getText().toString());
                servico.setValorServico(Integer.valueOf(editTextValorServico.getText().toString()));
                dao.atualizar(servico);
                dao.close();
                Toast.makeText(getApplicationContext(), "Editado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        imageViewExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirmacaoExclusao = new AlertDialog.Builder(EditarServico.this);
                confirmacaoExclusao.setTitle("Atencao!");
                confirmacaoExclusao.setMessage("Tem certeza que deseja apagar Esse Servico?");
                confirmacaoExclusao.setCancelable(false);
                confirmacaoExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ServicoDAO dao = new ServicoDAO(getApplication());
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

