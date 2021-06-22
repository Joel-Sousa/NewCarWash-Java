package com.example.carwash.view.Administrador.servico;

import androidx.appcompat.app.AppCompatActivity;

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

public class AdicionarServico extends AppCompatActivity {

    ImageView imageViewVoltar;
    EditText editTextServico, editTextDescricaoServico, editTextValorServico;
    ImageView imageViewSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_servico);

        TextView textViewUsuario = findViewById(R.id.textViewAdicionarServicoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewAdicionarServicoVoltar);
        editTextServico = findViewById(R.id.editTextAdicionarServicoServico);
        editTextDescricaoServico = findViewById(R.id.editTextAdicionarServicoDescricaoServico);
        editTextValorServico = findViewById(R.id.editTextAdicionarServicoValorServico);
        imageViewSalvar = findViewById(R.id.imageViewAdicionarServicoSalvar);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageViewSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextServico.getText().toString().equals("") ||
                        editTextDescricaoServico.getText().toString().equals("") ||
                             editTextValorServico.getText().toString().equals(""))){
                    ServicoDAO dao = new ServicoDAO(getApplication());
                    Servico servico = new Servico();

                    servico.setServico(editTextServico.getText().toString());
                    servico.setDescricaoServico(editTextDescricaoServico.getText().toString());
                    servico.setValorServico(Integer.valueOf(editTextValorServico.getText().toString()));

                    dao.inserir(servico);
                    dao.close();
                    finish();

                    Toast.makeText(getApplicationContext(), "Inserido!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
