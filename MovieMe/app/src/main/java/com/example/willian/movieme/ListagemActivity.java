package com.example.willian.movieme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListagemActivity extends Activity {
    private static List<Listagem> listagens = new ArrayList<>(); // entrada de dados (implementar banco de dados)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        ListView listaDeFilmes = (ListView) findViewById(R.id.lista);
        final ListagemAdapter adapter = new ListagemAdapter(listagens, this);
        listaDeFilmes.setAdapter(adapter);

        // Código para remover filme com swipe //
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        listaDeFilmes,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {

                                    //Criar Dialog quando o usuário usar o swipe
                                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ListagemActivity.this);

                                    alertBuilder.setIcon(R.drawable.ic_launcher_foreground);
                                    alertBuilder.setTitle(getString(R.string.dialogTitle));
                                    alertBuilder.setMessage(getString(R.string.dialogMessage));

                                    alertBuilder.setPositiveButton(getString(R.string.dialogPositive), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            listagens.remove(position);
                                            adapter.notifyDataSetChanged();
                                            Toast.makeText(getBaseContext(), getString(R.string.toastFilmeRemovido), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    alertBuilder.setNegativeButton(getString(R.string.dialogNegative), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });

                                    AlertDialog dialog = alertBuilder.create();
                                    dialog.show();

                                }


                            }
                        });
        listaDeFilmes.setOnTouchListener(touchListener);

        // Quando pressionar o botão //

        Button btn = findViewById(R.id.listagem_btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListagemActivity.this, AddFilmeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }

    public List<Listagem> EntradaTeste() {

        List<Listagem> listagens = new ArrayList<>();

        Listagem filme1 = new Listagem("Jurassic Park", "Ficção", "Steven Spielberg", 10, "1993");

        Listagem filme2 = new Listagem("Munique", "Drama", "Steven Spielberg", 16, "2006");

        Listagem filme3 = new Listagem("O Terminal", "Comédia, Drama", "Steven Spielberg", 12, "2004");

        Listagem filme4 = new Listagem("Jurassic Park", "Ficção", "Steven Spielberg", 10, "1993");

        Listagem filme5 = new Listagem("Munique", "Drama", "Steven Spielberg", 16, "2006");

        Listagem filme6 = new Listagem("O Terminal", "Comédia, Drama", "Steven Spielberg", 12, "2004");

        listagens.add(filme1);
        listagens.add(filme2);
        listagens.add(filme3);
        listagens.add(filme4);
        listagens.add(filme5);
        listagens.add(filme6);

        return listagens;
    }

    public static void AddFilme(Listagem l) {
        listagens.add(l);
    }
}
