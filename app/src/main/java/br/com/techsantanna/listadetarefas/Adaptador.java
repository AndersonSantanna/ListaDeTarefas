package br.com.techsantanna.listadetarefas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder>  {

    private TextView textView;
    private List<Tarefa> list;
    public Adaptador(List<Tarefa> lista) {
        this.list = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tarfa_adapter, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa tarefa = list.get(position);
        holder.tarefa.setText(tarefa.getNome());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tarefa;
        public MyViewHolder(View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.textView);
        }
    }
}
