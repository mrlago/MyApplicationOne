package com.example.mrlago.myapplicationone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mrlago on 27/07/2016.
 */
public class BaseController {

    private SQLiteDatabase db;
    private DatabaseUtil banco;

    public BaseController(Context context){
        banco = new DatabaseUtil(context);
    }

    public String insereDado(String nome, String telefone){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DatabaseUtil.NOME, nome);
        valores.put(DatabaseUtil.TELEFONE, telefone);

        resultado = db.insert(DatabaseUtil.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else {
            return "Registro Inserido com sucesso";
        }

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID, banco.TELEFONE,banco.NOME};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME,banco.TELEFONE};
        String where = DatabaseUtil.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String nome, String telefone){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = DatabaseUtil.ID + "=" + id;

        valores = new ContentValues();
        valores.put(DatabaseUtil.NOME, nome);
        valores.put(DatabaseUtil.TELEFONE, telefone);

        db.update(DatabaseUtil.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = DatabaseUtil.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(DatabaseUtil.TABELA,where,null);
        db.close();
    }
}