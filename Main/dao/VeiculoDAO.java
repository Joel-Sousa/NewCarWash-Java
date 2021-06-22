package com.example.carwash.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.carwash.objeto.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO extends SQLiteOpenHelper {



    // Construtor da Classe DAO
    public VeiculoDAO(Context context){
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
        String sql = "DROP TABLE IF EXISTS veiculo;";
        db.execSQL(sql);
    }

    // Metodo para inserir usuarios
    public void inserir(Veiculo veiculo){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("veiculo", veiculo.getVeiculo());
        dados.put("placa", veiculo.getPlaca());
        dados.put("servico", veiculo.getServico());
        dados.put("valorveiculo", veiculo.getValorVeiculo());
        dados.put("cliente", veiculo.getCliente());

        db.insert("veiculo",null,dados);
    }

    public List<Veiculo> buscarVeiculos(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM veiculo;";
        Cursor c = db.rawQuery(sql, null);
        List<Veiculo> veiculos = new ArrayList<Veiculo>();

        while (c.moveToNext()){
            Veiculo veiculo = new Veiculo();
            veiculo.setIdVeiculo(Integer.valueOf(c.getString(c.getColumnIndex("id"))));
            veiculo.setVeiculo(c.getString(c.getColumnIndex("veiculo")));
            veiculo.setPlaca(c.getString(c.getColumnIndex("placa")));
            veiculo.setServico(c.getString(c.getColumnIndex("servico")));
            veiculo.setValorVeiculo(Integer.valueOf(c.getString(c.getColumnIndex("valorveiculo"))));
            veiculo.setCliente(c.getString(c.getColumnIndex("cliente")));
            veiculos.add(veiculo);
        }
        return veiculos;
    }

    public void apagar(String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM veiculo WHERE id = '" + id + "';";
        db.execSQL(sql);
    }

    public void atualizar(Veiculo veiculo){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE veiculo SET veiculo = '"+veiculo.getVeiculo()+
                "', placa = '"+veiculo.getPlaca()+
                "', servico = '"+veiculo.getServico()+
                "', valorveiculo = '"+veiculo.getValorVeiculo()+
                "', cliente = '"+veiculo.getCliente()+
                "' WHERE id = '"+veiculo.getIdVeiculo()+"';";
        db.execSQL(sql);
    }

    public ArrayList<String> buscaServicos(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT servico FROM servico;";
        //db.execSQL(sql);

        ArrayList<String> servicos = new ArrayList<String>();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            servicos.add(cursor.getString(cursor.getColumnIndex("servico")));
        }
        return servicos;
    }
}
