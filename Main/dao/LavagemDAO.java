package com.example.carwash.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.carwash.objeto.Lavagem;

import java.util.ArrayList;
import java.util.List;

public class LavagemDAO extends SQLiteOpenHelper {

    // Construtor da Classe DAO
    public LavagemDAO(Context context){
        super(context,"db", null,1);
    }

    // Sobrecarga do metodo onCreate
    @Override
    public void onCreate(SQLiteDatabase db){
//        String sql = "CREATE TABLE produ''to " +
//        "(id Integer primary key autoincrement, produto TEXT, descricaoproduto TEXT, quantidadeproduto TEXT); ";
//        db.execSQL(sql);

//        String sql1 = "INSERT INTO usuario(id, usuario, senha, perfil) values(null, 'root', '0000', '1') ";
//        db.execSQL(sql1);

    }

    // Sobrecarga do metodo onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS lavagem;";
        db.execSQL(sql);
    }

    // Metodo para inserir usuarios
    public void inserir(Lavagem lavagem){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("veiculo", lavagem.getVeiculo());
        dados.put("placa", lavagem.getPlaca());
        dados.put("servico", lavagem.getServico());
        dados.put("valorservico", lavagem.getValorServico());
        dados.put("cliente", lavagem.getCliente());
        dados.put("usuario", lavagem.getUsuario());
        dados.put("hora", lavagem.getHora());
        dados.put("data", lavagem.getData());

        db.insert("lavagem",null,dados);
    }

    public List<Lavagem> buscarLavagem(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM lavagem;";
        Cursor c = db.rawQuery(sql, null);
        List<Lavagem> lavagems = new ArrayList<Lavagem>();

        while (c.moveToNext()){
            Lavagem lavagem = new Lavagem();
            lavagem.setIdLavagem(Integer.valueOf(c.getString(c.getColumnIndex("id"))));
            lavagem.setVeiculo(c.getString(c.getColumnIndex("veiculo")));
            lavagem.setPlaca(c.getString(c.getColumnIndex("placa")));
            lavagem.setServico(c.getString(c.getColumnIndex("servico")));
            lavagem.setValorServico(Integer.valueOf(c.getString(c.getColumnIndex("valorservico"))));
            lavagem.setCliente(c.getString(c.getColumnIndex("cliente")));
            lavagem.setUsuario(c.getString(c.getColumnIndex("usuario")));
            lavagem.setHora(c.getString(c.getColumnIndex("hora")));
            lavagem.setData(c.getString(c.getColumnIndex("data")));
            lavagems.add(lavagem);
        }
        return lavagems;
    }

    public void apagar(String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM lavagem WHERE id = '" + id + "';";
        db.execSQL(sql);
    }
//
//    public void atualizar(Veiculo veiculo){
//        SQLiteDatabase db = getWritableDatabase();
//
//        String sql = "UPDATE veiculo SET veiculo = '"+veiculo.getVeiculo()+
//                "', placa = '"+veiculo.getPlaca()+
//                "', servico = '"+veiculo.getServico()+
//                "', valorveiculo = '"+veiculo.getValorVeiculo()+
//                "', cliente = '"+veiculo.getCliente()+
//                "' WHERE id = '"+veiculo.getIdVeiculo()+"';";
//        db.execSQL(sql);
//    }

//    public ArrayList<String> buscaLavagems(){
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT servico FROM servico;";
//        //db.execSQL(sql);
//
//        ArrayList<String> servicos = new ArrayList<String>();
//        Cursor cursor = db.rawQuery(sql, null);
//        while(cursor.moveToNext()){
//            servicos.add(cursor.getString(cursor.getColumnIndex("servico")));
//        }
//        return servicos;
//    }
}
