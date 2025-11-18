package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> listaDeTarefas;

    public TarefaAdapter(List<Tarefa> listaDeTarefas) {
        this.listaDeTarefas = listaDeTarefas;
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTarefa;
        public Button botaoRemover;

        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTarefa = itemView.findViewById(R.id.textViewTarefa);
            botaoRemover = itemView.findViewById(R.id.botaoRemover);
        }
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        Tarefa tarefaAtual = listaDeTarefas.get(position);
        holder.textViewTarefa.setText(tarefaAtual.getTexto());

        holder.botaoRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicaoAtual = holder.getAdapterPosition();
                if (posicaoAtual != RecyclerView.NO_POSITION) {
                    listaDeTarefas.remove(posicaoAtual);
                    notifyItemRemoved(posicaoAtual);
                    notifyItemRangeChanged(posicaoAtual, listaDeTarefas.size());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeTarefas.size();
    }
}
