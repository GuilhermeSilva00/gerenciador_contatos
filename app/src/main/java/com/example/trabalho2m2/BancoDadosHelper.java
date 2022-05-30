package com.example.trabalho2m2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDadosHelper extends SQLiteOpenHelper {

    private static final int BANCODADOS_VERSAO = 1;

    private static final String BANCODADOS_NOME = "banco_contatos";

    private static final String NOME_TABELA = "CONTATOS";

    public static final String id = "id";
    public static final String nome = "nome";
    public static final String telefone = "telefone";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "create table " + NOME_TABELA + "(" + id + "INTEGER PRIMARY KEY AUTOINCREMENT, " + nome + " TEXT NOT NULL, " + telefone + " TEXT NOT NULL )";

    public BancoDadosHelper (Context context){
        super(context, BANCODADOS_NOME, null, BANCODADOS_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + NOME_TABELA);
        onCreate(sqLiteDatabase);
    }

    public void addContato(Contato contato){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BancoDadosHelper.nome, contato.getNome());
        contentValues.put(BancoDadosHelper.telefone, contato.getTelefone());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(BancoDadosHelper.NOME_TABELA, null, contentValues);


    }

    public List<Contato> getContatoList (){
        String sql = "select * from " + NOME_TABELA;
        sqLiteDatabase = this.getReadableDatabase();
        List<Contato> listaContato = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do {
            int id = Integer.parseInt(cursor.getString(0));
            String nome = cursor.getString(1);
            String telefone = cursor.getString(2);
            listaContato.add(new Contato(id, nome, telefone));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return  listaContato;

    }

    public void updateContato(Contato contato){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BancoDadosHelper.nome, contato.getNome());
        contentValues.put(BancoDadosHelper.telefone, contato.getTelefone());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(NOME_TABELA, contentValues, id + " = ? ", new String[]{
                String.valueOf(contato.getId())
        });

    }

    public void deleteContato(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(NOME_TABELA, id + " = ? ", new String[]{
                String.valueOf(id)
        });
    }

}
