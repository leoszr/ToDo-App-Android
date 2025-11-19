package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Tarefa> listaDeTarefas;
    private TarefaAdapter tarefaAdapter;
    private RecyclerView recyclerViewTarefas;
    private EditText editTextNovaTarefa;
    private Button botaoAdicionarTarefa;
    private TextView textViewEstadoVazio;
    private Spinner spinnerCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTarefas = findViewById(R.id.recyclerViewTarefas);
        editTextNovaTarefa = findViewById(R.id.editTextNovaTarefa);
        botaoAdicionarTarefa = findViewById(R.id.botaoAdicionarTarefa);
        textViewEstadoVazio = findViewById(R.id.textViewEstadoVazio);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);

        listaDeTarefas = new ArrayList<>();
        tarefaAdapter = new TarefaAdapter(listaDeTarefas, this::verificarEstadoDaLista);

        recyclerViewTarefas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTarefas.setAdapter(tarefaAdapter);

        setupSpinner();

        botaoAdicionarTarefa.setOnClickListener(v -> adicionarNovaTarefa());

        verificarEstadoDaLista();
    }

    private void setupSpinner() {
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Categoria.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(new CategoriaSpinnerAdapter(this, Categoria.values()));
    }

    private void adicionarNovaTarefa() {
        String textoDaTarefa = editTextNovaTarefa.getText().toString().trim();
        Categoria categoriaSelecionada = (Categoria) spinnerCategoria.getSelectedItem();

        if (!textoDaTarefa.isEmpty()) {
            Tarefa novaTarefa = new Tarefa(textoDaTarefa, categoriaSelecionada);
            listaDeTarefas.add(novaTarefa);
            tarefaAdapter.notifyItemInserted(listaDeTarefas.size() - 1);
            editTextNovaTarefa.setText("");
            recyclerViewTarefas.scrollToPosition(listaDeTarefas.size() - 1);
            verificarEstadoDaLista();
        } else {
            Toast.makeText(this, "Por favor, digite uma tarefa.", Toast.LENGTH_SHORT).show();
        }
    }

    private void verificarEstadoDaLista() {
        if (listaDeTarefas.isEmpty()) {
            recyclerViewTarefas.setVisibility(View.GONE);
            textViewEstadoVazio.setVisibility(View.VISIBLE);
        } else {
            recyclerViewTarefas.setVisibility(View.VISIBLE);
            textViewEstadoVazio.setVisibility(View.GONE);
        }
    }

    private static class CategoriaSpinnerAdapter extends ArrayAdapter<Categoria> {
        private final LayoutInflater inflater;

        public CategoriaSpinnerAdapter(Context context, Categoria[] categorias) {
            super(context, android.R.layout.simple_spinner_item, categorias);
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return createView(position, convertView, parent, android.R.layout.simple_spinner_item);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return createView(position, convertView, parent, android.R.layout.simple_spinner_dropdown_item);
        }

        private View createView(int position, View convertView, ViewGroup parent, int layoutResource) {
            View view = inflater.inflate(layoutResource, parent, false);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            Categoria categoria = getItem(position);
            if (categoria != null) {
                textView.setText(getContext().getString(categoria.getNomeResId()));
            }
            return view;
        }
    }
}
