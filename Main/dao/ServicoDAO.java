package com.example.carwash.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.carwash.objeto.Servico;

import java.util.ArrayList;
import java.util.List;

public class ServicoDAO extends SQLiteOpenHelper {

    // Construtor da Classe DAO
    public ServicoDAO(Context context){
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
        String sql = "DROP TABLE IF EXISTS servico;";
        db.execSQL(sql);
    }

    // Metodo para inserir usuarios
    public void inserir(Servico servico){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("servico", servico.getServico());
        dados.put("descricaoservico", servico.getDescricaoServico());
        dados.put("valorservico", servico.getValorServico());

        db.insert("servico",null,dados);
    }

    public List<Servico> buscarServicos(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM servico where servico <> '.:Selecione:.';";
        Cursor c = db.rawQuery(sql, null);
        List<Servico> servicos = new ArrayList<Servico>();

        while (c.moveToNext()){
            Servico servico = new Servico();
            servico.setIdServico(Integer.valueOf(c.getString(c.getColumnIndex("id"))));
            servico.setServico(c.getString(c.getColumnIndex("servico")));
            servico.setDescricaoServico(c.getString(c.getColumnIndex("descricaoservico")));
            servico.setValorServico(Integer.valueOf(c.getString(c.getColumnIndex("valorservico"))));
            servicos.add(servico);
        }
        return servicos;
    }

    public void apagar(String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM servico WHERE id = '" + id + "';";
        db.execSQL(sql);
    }


    public void atualizar(Servico servico){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE servico SET servico = '"+servico.getServico()+
                "', descricaoservico = '"+servico.getDescricaoServico()+
                "', valorservico = '"+servico.getValorServico()+
                "' WHERE id = '"+servico.getIdServico()+"';";
        db.execSQL(sql);
    }
}
