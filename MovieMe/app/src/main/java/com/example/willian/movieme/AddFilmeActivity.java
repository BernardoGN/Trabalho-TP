package com.example.willian.movieme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddFilmeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filme);

        final EditText nome = findViewById(R.id.addFilme_nome);
        final EditText genero = findViewById(R.id.addFilme_genero);
        final EditText diretor = findViewById(R.id.addFilme_diretor);
        final EditText ano = findViewById(R.id.addFilme_ano);

        RadioGroup radioGroup = findViewById(R.id.addFilme_radioGroup);
        int selecetedBtnId = radioGroup.getCheckedRadioButtonId();
        final RadioButton selectedBtn = findViewById(selecetedBtnId);

        Button btn = findViewById(R.id.addFilme_btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listagem l = new Listagem(nome.getText().toString(), genero.getText().toString(), diretor.getText().toString(), 0, ano.getText().toString());
                ListagemActivity.AddFilme(l);
                Intent intent = new Intent(AddFilmeActivity.this, ListagemActivity.class);
                startActivity(intent);
            }
        });

    }
}
