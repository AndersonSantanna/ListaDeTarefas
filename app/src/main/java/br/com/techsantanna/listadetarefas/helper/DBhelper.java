package br.com.techsantanna.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {
    public static int VERSION = 3;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";
    public DBhelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS "+ TABELA_TAREFAS +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT NOT NULL );";
        try{
            sqLiteDatabase.execSQL(sql);
            Log.i("InfoDB", "Sucesso ao criar");

        }catch (Exception e){
            Log.i("InfoDB", "erro ao criar");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + ";";

        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("InfoDB", "Sucesso ao atualizar");
        }catch (Exception e){
            Log.i("InfoDB", "erro ao atualizar");
        }
    }
}
