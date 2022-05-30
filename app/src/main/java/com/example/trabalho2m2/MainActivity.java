package com.example.trabalho2m2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  {

    EditText edittext_telefone, edittext_nome;
    Button cadastrar;
    Button visualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_telefone = findViewById(R.id.edittext_telefone);
        edittext_nome = findViewById(R.id.edittext_nome);
        cadastrar = findViewById(R.id.botao_add);
        visualizar = findViewById(R.id.botao_view);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stringNome = edittext_nome.toString();
                String stringTelefone = edittext_telefone.toString();

                if(stringNome.length() <= 0 || stringTelefone.length() <= 0) {
                    Toast.makeText(MainActivity.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
                } else {
                    BancoDadosHelper bancoDadosHelper = new BancoDadosHelper(MainActivity.this);
                    Contato contato = new Contato(stringNome, stringTelefone);
                    bancoDadosHelper.addContato(contato);
                    Toast.makeText(MainActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        visualizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewContatoActivity.class);
                startActivity(intent);


            }
        });

    }
}