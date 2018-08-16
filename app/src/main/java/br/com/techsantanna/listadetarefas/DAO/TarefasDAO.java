package br.com.techsantanna.listadetarefas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.techsantanna.listadetarefas.Tarefa;
import br.com.techsantanna.listadetarefas.helper.DBhelper;

public class TarefasDAO implements ITarefaDAO{
    private SQLiteDatabase escreve, ler;

    public TarefasDAO(Context context) {
        DBhelper db = new DBhelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNome());

        try {
            escreve.insert(DBhelper.TABELA_TAREFAS,null, cv );
            Log.i("ImfoDB", "Tarefa Salva");
        }catch (Exception e){
            Log.i("InfoDB", "Erro ao salvar tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNome());

        try {
            String[] args = {tarefa.getId().toString()};
            ler.update(DBhelper.TABELA_TAREFAS, cv, "id = ?",args  );
            Log.i("ImfoDB", "Tarefa atualixada");
        }catch (Exception e){
            Log.i("InfoDB", "Erro ao atualizae tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.delete(DBhelper.TABELA_TAREFAS,"id = ?", args);
            Log.i("ImfoDB", "Tarefa excluifda");
        }catch (Exception e){
            Log.i("InfoDB", "Erro ao excluir tarefa" + e.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> list = new ArrayList<>();
        String sql = "SELECT * FROM " + DBhelper.TABELA_TAREFAS + ";";
        Cursor c = ler.rawQuery(sql, null);
        while (c.moveToNext()){
            Tarefa tarefa = new Tarefa();
            tarefa.setId(c.getLong(c.getColumnIndex("id")));
            tarefa.setNome(c.getString(c.getColumnIndex("nome")));
            list.add(tarefa);
        }
        return list;
    }
}
