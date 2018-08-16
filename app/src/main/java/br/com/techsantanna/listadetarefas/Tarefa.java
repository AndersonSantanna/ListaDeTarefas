package br.com.techsantanna.listadetarefas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tarefa implements Serializable {
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
