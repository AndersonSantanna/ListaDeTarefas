package br.com.techsantanna.listadetarefas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

import br.com.techsantanna.listadetarefas.DAO.TarefasDAO;

public class AddActivity extends AppCompatActivity {
    private EditText editText, desc;
    private Tarefa tarefaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText = findViewById(R.id.editText);
        //recuperando
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefa");
        //configuraçao de caixa de texto
        if(tarefaAtual != null){
            editText.setText(tarefaAtual.getNome());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salvar:
                Tarefa tarefa = new Tarefa();
                TarefasDAO tarefasDAO = new TarefasDAO(getApplicationContext());
                if (tarefaAtual != null){//edição
                    if (!editText.getText().toString().isEmpty()){
                        tarefa.setNome(editText.getText().toString());
                        tarefa.setId(tarefaAtual.getId());
                        if (tarefasDAO.atualizar(tarefa)){
                            Toast.makeText(getApplicationContext(), "Atualizado", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else if (!editText.getText().toString().isEmpty() || !desc.getText().toString().isEmpty()){
                    tarefa.setNome(editText.getText().toString());
                    if (tarefasDAO.salvar(tarefa)){
                        Toast.makeText(getApplicationContext(),"Salvo", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Erro ao salvar", Toast.LENGTH_SHORT).show();

                    }

                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
