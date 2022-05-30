package com.example.trabalho2m2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ViewContatoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contato);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        BancoDadosHelper bancoDadosHelper = new BancoDadosHelper(this);
        List<Contato> contatos = bancoDadosHelper.getContatoList();

        if(contatos.size() > 0){

        } else {
            Toast.makeText(this, "Não há contatos cadastrados", Toast.LENGTH_SHORT).show();
        }

    }

}