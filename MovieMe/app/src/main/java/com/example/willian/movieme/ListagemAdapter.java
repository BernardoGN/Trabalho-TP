package com.example.willian.movieme;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Willian on 30/03/2018.
 */

public class ListagemAdapter extends BaseAdapter {

    private final List<Listagem> listagens;
    private final Activity activity;

    public ListagemAdapter(List<Listagem> listagens, Activity activity){
        this.listagens = listagens;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listagens.size();
    }

    @Override
    public Object getItem(int position) {
        return listagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.listagem_filmes, parent, false);

        Listagem listagem = listagens.get(position);

        ImageView imggenero = view.findViewById(R.id.img_genero);
        TextView nome = view.findViewById(R.id.txt_nome);
        TextView diretor = view.findViewById(R.id.txt_diretor);
        TextView faixa = view.findViewById(R.id.txt_faixa);
        TextView ano = view.findViewById(R.id.txt_ano);

        nome.setText(listagem.getNome());
        diretor.setText(listagem.getDiretor());
        faixa.setText(listagem.getFaixa());
        ano.setText(listagem.getAno());
        imggenero.setImageResource(R.drawable.dog);

        return view;
    }
}
