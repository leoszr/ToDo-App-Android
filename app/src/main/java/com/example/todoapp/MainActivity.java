package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTarefas = findViewById(R.id.recyclerViewTarefas);
        editTextNovaTarefa = findViewById(R.id.editTextNovaTarefa);
        botaoAdicionarTarefa = findViewById(R.id.botaoAdicionarTarefa);
        textViewEstadoVazio = findViewById(R.id.textViewEstadoVazio);

        listaDeTarefas = new ArrayList<>();
        tarefaAdapter = new TarefaAdapter(listaDeTarefas, this::verificarEstadoDaLista);

        recyclerViewTarefas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTarefas.setAdapter(tarefaAdapter);

        botaoAdicionarTarefa.setOnClickListener(v -> adicionarNovaTarefa());

        verificarEstadoDaLista();
    }

    private void adicionarNovaTarefa() {
        String textoDaTarefa = editTextNovaTarefa.getText().toString().trim();

        if (!textoDaTarefa.isEmpty()) {
            Tarefa novaTarefa = new Tarefa(textoDaTarefa);
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
}
