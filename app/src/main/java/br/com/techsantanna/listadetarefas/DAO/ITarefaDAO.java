package br.com.techsantanna.listadetarefas.DAO;

import java.util.List;

import br.com.techsantanna.listadetarefas.Tarefa;

public interface ITarefaDAO {
    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean deletar(Tarefa tarefa);
    public List<Tarefa> listar();
}
