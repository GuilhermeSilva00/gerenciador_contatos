package com.example.trabalho2m2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ViewHolder> {

    List<Contato> contatos;
    Context context;
    BancoDadosHelper bancoDadosHelper;

    public ContatoAdapter(List<Contato> contatos, Context context) {
        this.contatos = contatos;
        this.context = context;
        bancoDadosHelper = new BancoDadosHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contato_item_list,parent,false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Contato contato = contatos.get(position);

        holder.textViewID.setText(Integer.toString(contato.getId()));
        holder.editText_Nome.setText(contato.getNome());
        holder.editText_Telefone.setText(contato.getTelefone());

        holder.button_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String stringNome = holder.editText_Nome.getText().toString();
                    String stringTelefone = holder.editText_Telefone.getText().toString();

                    bancoDadosHelper.updateContato(new Contato(contato.getId(), stringNome, stringTelefone));
                    notifyDataSetChanged();
                    ((Activity) context).finish();
                    context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancoDadosHelper.deleteContato(contato.getId());
                contatos.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        EditText editText_Nome;
        EditText editText_Telefone;
        Button button_editar;
        Button button_deletar;


        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Nome = itemView.findViewById(R.id.edittext_nome);
            editText_Telefone = itemView.findViewById(R.id.edittext_telefone);
            button_editar = itemView.findViewById(R.id.editar);
            button_deletar = itemView.findViewById(R.id.deletar);

        }


    }
}
