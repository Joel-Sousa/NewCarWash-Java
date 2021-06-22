package com.example.carwash.view.Administrador.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.dao.ProdutoDAO;
import com.example.carwash.objeto.Produto;

public class AdicionarProduto extends AppCompatActivity {

    ImageView imageViewVoltar;
    EditText editTextProduto, editTextDescricaoProduto, editTextQuantidadeProduto;
    ImageView imageViewSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produto);

        TextView textViewUsuario = findViewById(R.id.textViewAdicionarProdutoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewAdicionarProdutoVoltar);
        editTextProduto = findViewById(R.id.editTextAdicionarProdutoProduto);
        editTextDescricaoProduto = findViewById(R.id.editTextAdicionarProdutoDescricaoProduto);
        editTextQuantidadeProduto = findViewById(R.id.editTextAdicionarProdutoQuantidadeProduto);
        imageViewSalvar = findViewById(R.id.imageViewAdicionarProdutoSalvar);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageViewSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextProduto.getText().toString().equals("") ||
                        editTextDescricaoProduto.getText().toString().equals("") ||
                            editTextQuantidadeProduto.getText().toString().equals(""))){
                    ProdutoDAO dao = new ProdutoDAO(getApplication());
                    Produto produto = new Produto();

                    produto.setProduto(editTextProduto.getText().toString());
                    produto.setDescricaoProduto(editTextDescricaoProduto.getText().toString());
                    produto.setQuantidadeProduto(Integer.valueOf(editTextQuantidadeProduto.getText().toString()));

                    dao.inserir(produto);
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
