package com.example.carwash.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.carwash.objeto.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends SQLiteOpenHelper {

    // Construtor da Classe DAO
    public UsuarioDAO(Context context){
        super(context,"db", null,1);
    }

    // Sobrecarga do metodo onCreate
    @Override
    public void onCreate(SQLiteDatabase db){

//        String sql3 = "CREATE TABLE usuario" +
//                "(id Integer primary key autoincrement, usuario TEXT, senha TEXT, perfil TEXT); ";
//        db.execSQL(sql3);

//        String sql1 = "CREATE TABLE produto " +
//                "(id Integer primary key autoincrement, produto TEXT, descricaoproduto TEXT, quantidadeproduto Integer); ";
//        db.execSQL(sql1);
//
//        String sql2 = "CREATE TABLE servico " +
//                "(id Integer primary key autoincrement, servico TEXT, descricaoservico TEXT, valorservico Integer); ";
//        db.execSQL(sql2);
//
//        String sql4 = "CREATE TABLE veiculo " +
//                "(id Integer primary key autoincrement, veiculo TEXT, placa TEXT, servico TEXT, " +
//                "valorveiculo Integer, cliente TEXT); ";
//        db.execSQL(sql4);
//
//        String sql5 = "CREATE TABLE lavagem " +
//                "(id Integer primary key autoincrement, veiculo TEXT, placa TEXT, servico TEXT, " +
//                "valorservico Integer, cliente TEXT, usuario TEXT, hora DATETIME, data TEXT); ";
//        db.execSQL(sql5);

//        String sql0 = "INSERT INTO usuario(id, usuario, senha, perfil) values(null, 'root', '0000', 'Administrador') ";
//        db.execSQL(sql0);

//        String sql7 = "INSERT INTO lavagem " +
//                "(id, veiculo, placa, servico, valorservico, cliente, usuario, hora, data) " +
//                "values (null, 'polo', 'jjj-0000', 'lavagem', 10, 'ana', 'root', '20:20', '20-20-2020')";
//        db.execSQL(sql7);
    }

    // Sobrecarga do metodo onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS usuario;";
        db.execSQL(sql);
    }

    // Metodo para inserir usuarios
    public void inserir(Usuario usuario){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("usuario", usuario.getUsuario());
        dados.put("senha", usuario.getSenha());
        dados.put("perfil", usuario.getPerfil());

        db.insert("usuario",null,dados);
    }

    public List<Usuario> buscarUsuarios(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM usuario;";
        Cursor c = db.rawQuery(sql, null);
        List<Usuario> usuarios = new ArrayList<Usuario>();

        while (c.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Integer.valueOf(c.getString(c.getColumnIndex("id"))));
            usuario.setUsuario(c.getString(c.getColumnIndex("usuario")));
            usuario.setSenha(c.getString(c.getColumnIndex("senha")));
            usuario.setPerfil(c.getString(c.getColumnIndex("perfil")));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public void apagar(String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM usuario WHERE id = '" + id + "';";
        db.execSQL(sql);
    }

    public void atualizar(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE usuario SET usuario = '"+usuario.getUsuario()+
                "', senha = '"+usuario.getSenha()+
                "', perfil = '"+usuario.getPerfil()+
                "' WHERE id = '"+usuario.getIdUsuario()+"';";
        db.execSQL(sql);
    }

}
