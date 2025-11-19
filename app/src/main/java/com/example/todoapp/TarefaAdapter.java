package com.example.todoapp;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
        public View viewCorCategoria;
        public ImageView imageViewIconeCategoria;

        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTarefa = itemView.findViewById(R.id.textViewTarefa);
            botaoRemover = itemView.findViewById(R.id.botaoRemover);
            checkBoxConcluida = itemView.findViewById(R.id.checkBoxConcluida);
            viewCorCategoria = itemView.findViewById(R.id.viewCorCategoria);
            imageViewIconeCategoria = itemView.findViewById(R.id.imageViewIconeCategoria);
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

        if (tarefaAtual.getCategoria() != null) {
            Context context = holder.itemView.getContext();
            int cor = ContextCompat.getColor(context, tarefaAtual.getCategoria().getCorResId());
            holder.viewCorCategoria.setBackgroundColor(cor);

            int icone = tarefaAtual.getCategoria().getIconeResId();
            holder.imageViewIconeCategoria.setImageResource(icone);
        }

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
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), android.R.color.darker_gray));
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), android.R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return listaDeTarefas.size();
    }
}
