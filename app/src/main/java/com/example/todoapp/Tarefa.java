package com.example.todoapp;

public class Tarefa {

    private String texto;
    private boolean concluida;

    public Tarefa(String texto) {
        this.texto = texto;
        this.concluida = false; // Por padrão, uma nova tarefa não está concluída
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
