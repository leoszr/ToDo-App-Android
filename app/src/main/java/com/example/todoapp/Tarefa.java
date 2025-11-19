package com.example.todoapp;

public class Tarefa {

    private String texto;
    private boolean concluida;
    private Categoria categoria;

    public Tarefa(String texto, Categoria categoria) {
        this.texto = texto;
        this.categoria = categoria;
        this.concluida = false;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
