package com.example.listasimples;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonNovo;
    EditText editTextNome;
    ListView listViewLivros;
    ArrayList<String> nomeLivros;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();

        listViewLivros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
                String nome = nomeLivros.get(position);
                msg.setMessage("Confirma Exclusão: " + nome);
                msg.setNegativeButton("Não", null); //null indica que não fará nada e fecha a alerta
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluirLivro(position);
                    }
                });
                msg.show();
                return true; //cancela o evento de click simples
            }
        });

        buttonNovo .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editTextNome.getText().toString();
                nome = nome.trim(); //remove espaços antes e depois
                if(nome.equals("")){
                    Toast.makeText(MainActivity.this,
                            "Preencha o nome do livro!", Toast.LENGTH_SHORT).show();
                }
                else{
                    nomeLivros.add(nome);
                    listViewLivros.setAdapter(adapter); //atualiza o ListView (refresh na lista)
                }
                editTextNome.setText("");
            }
        });

    }

    private void excluirLivro(int position) {
        nomeLivros.remove(position);
        listViewLivros.setAdapter(adapter);
    }

    private void inicializar() {
        editTextNome = findViewById(R.id.editTextNome);
        buttonNovo = findViewById(R.id.buttonNovo);
        listViewLivros = findViewById(R.id.listViewLivros);
        nomeLivros = new ArrayList<>();
        adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1, nomeLivros);
    }
}