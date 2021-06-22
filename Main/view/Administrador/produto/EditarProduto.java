package com.example.carwash.view.Administrador.produto;

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
import com.example.carwash.dao.ProdutoDAO;
import com.example.carwash.objeto.Produto;

public class EditarProduto extends AppCompatActivity {

    ImageView imageViewVoltar;
    TextView textViewId;
    EditText editTextProduto, editTextDescricaoProduto, editTextQuantidadeProduto;
    ImageView imageViewEditar, imageViewExcluir;
    String apagarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        TextView textViewUsuario = findViewById(R.id.textViewEditarProdutoUsuario);
        textViewUsuario.setText(MainActivity.usuarioB);

        imageViewVoltar = findViewById(R.id.imageViewEditarProdutoVoltar);
        textViewId = findViewById(R.id.textViewEditarProdutoIdProduto);
        editTextProduto = findViewById(R.id.editTextEditarProdutoProduto);
        editTextDescricaoProduto = findViewById(R.id.editTextEditarProdutoDescricaoProduto);
        editTextQuantidadeProduto = findViewById(R.id.editTextEditarProdutoQuantidadeProduto);
        imageViewEditar = findViewById(R.id.imageViewEditarProdutoEditar);
        imageViewExcluir = findViewById(R.id.imageViewEditarProdutoExcluir);

        Intent intent = getIntent();

        textViewId.setText(intent.getStringExtra("id"));
        editTextProduto.setText(intent.getStringExtra("produto"));
        editTextDescricaoProduto.setText(intent.getStringExtra("descricaoproduto"));
        editTextQuantidadeProduto.setText(intent.getStringExtra("quantidadeproduto"));

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
                ProdutoDAO dao = new ProdutoDAO(getApplication());
                Produto produto = new Produto();

                produto.setIdProduto(Integer.valueOf(textViewId.getText().toString()));
                produto.setProduto(editTextProduto.getText().toString());
                produto.setDescricaoProduto(editTextDescricaoProduto.getText().toString());
                produto.setQuantidadeProduto(Integer.valueOf(editTextQuantidadeProduto.getText().toString()));

                dao.atualizar(produto);
                dao.close();
                Toast.makeText(getApplicationContext(), "Editado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        imageViewExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirmacaoExclusao = new AlertDialog.Builder(EditarProduto.this);
                confirmacaoExclusao.setTitle("Atencao!");
                confirmacaoExclusao.setMessage("Tem certeza que deseja apagar Esse Produto?");
                confirmacaoExclusao.setCancelable(false);
                confirmacaoExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProdutoDAO dao = new ProdutoDAO(getApplication());
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
