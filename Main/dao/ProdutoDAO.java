package com.example.carwash.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.carwash.objeto.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends SQLiteOpenHelper {

    // Construtor da Classe DAO
    public ProdutoDAO(Context context){
        super(context,"db", null,1);
    }

    // Sobrecarga do metodo onCreate
    @Override
    public void onCreate(SQLiteDatabase db){
//        String sql = "CREATE TABLE produto " +
//        "(id Integer primary key autoincrement, produto TEXT, descricaoproduto TEXT, quantidadeproduto TEXT); ";
//        db.execSQL(sql);

//        String sql1 = "INSERT INTO usuario(id, usuario, senha, perfil) values(null, 'root', '0000', '1') ";
//        db.execSQL(sql1);

    }

    // Sobrecarga do metodo onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS produto;";
        db.execSQL(sql);
    }

    // Metodo para inserir usuarios
    public void inserir(Produto produto){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("produto", produto.getProduto());
        dados.put("descricaoproduto", produto.getDescricaoProduto());
        dados.put("quantidadeproduto", produto.getQuantidadeProduto());

        db.insert("produto",null,dados);
    }

    public List<Produto> buscarProdutos(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM produto where produto <> '.:Selecione:.';";
        Cursor c = db.rawQuery(sql, null);
        List<Produto> produtos = new ArrayList<Produto>();

        while (c.moveToNext()){
            Produto produto = new Produto();
            produto.setIdProduto(Integer.valueOf(c.getString(c.getColumnIndex("id"))));
            produto.setProduto(c.getString(c.getColumnIndex("produto")));
            produto.setDescricaoProduto(c.getString(c.getColumnIndex("descricaoproduto")));
            produto.setQuantidadeProduto(Integer.valueOf(c.getString(c.getColumnIndex("quantidadeproduto"))));
            produtos.add(produto);
        }
        return produtos;
    }

    public void apagar(String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM produto WHERE id = '" + id + "';";
        db.execSQL(sql);
    }

    public void atualizar(Produto produto){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE produto SET produto = '"+produto.getProduto()+
                "', descricaoproduto = '"+produto.getDescricaoProduto()+
                "', quantidadeproduto = '"+produto.getQuantidadeProduto()+
                "' WHERE id = '"+produto.getIdProduto()+"';";
        db.execSQL(sql);
    }

    public ArrayList<String> buscaProdutos(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT produto FROM produto;";
        //db.execSQL(sql);

        ArrayList<String> produtos = new ArrayList<String>();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            produtos.add(cursor.getString(cursor.getColumnIndex("produto")));
        }
        return produtos;
    }

}
