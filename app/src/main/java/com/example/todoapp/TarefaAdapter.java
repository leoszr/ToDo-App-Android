package com.example.todoapp;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> listaDeTarefas;
    private final Runnable verificarEstadoCallback;

    public TarefaAdapter(List<Tarefa> listaDeTarefas, Runnable verificarEstadoCallback) {
        this.listaDeTarefas = listaDeTarefas;
        this.verificarEstadoCallback = verificarEstadoCallback;
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTarefa;
        public ImageButton botaoRemover;
        public CheckBox checkBoxConcluida;

        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTarefa = itemView.findViewById(R.id.textViewTarefa);
            botaoRemover = itemView.findViewById(R.id.botaoRemover);
            checkBoxConcluida = itemView.findViewById(R.id.checkBoxConcluida);
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
        holder.checkBoxConcluida.setChecked(tarefaAtual.isConcluida());

        aplicarEfeitoDeRiscado(holder.textViewTarefa, tarefaAtual.isConcluida());

        holder.checkBoxConcluida.setOnClickListener(v -> {
            boolean isChecked = holder.checkBoxConcluida.isChecked();
            tarefaAtual.setConcluida(isChecked);
            aplicarEfeitoDeRiscado(holder.textViewTarefa, isChecked);
        });

        holder.botaoRemover.setOnClickListener(v -> {
            int posicaoAtual = holder.getAdapterPosition();
            if (posicaoAtual != RecyclerView.NO_POSITION) {
                listaDeTarefas.remove(posicaoAtual);
                notifyItemRemoved(posicaoAtual);
                notifyItemRangeChanged(posicaoAtual, listaDeTarefas.size());
                verificarEstadoCallback.run();
            }
        });
    }

    private void aplicarEfeitoDeRiscado(TextView textView, boolean concluida) {
        if (concluida) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            textView.setTextColor(0xFF888888);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            textView.setTextColor(0xFF000000);
        }
    }

    @Override
    public int getItemCount() {
        return listaDeTarefas.size();
    }
}
