package com.example.willian.movieme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/* TODO
    implementar uma validação dos campos
    deixar a interface mais amigável para o usuário
 */

public class AddFilmeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filme);

        final EditText nome = findViewById(R.id.addFilme_nome);
        final EditText genero = findViewById(R.id.addFilme_genero);
        final EditText diretor = findViewById(R.id.addFilme_diretor);
        final EditText ano = findViewById(R.id.addFilme_ano);

        Button btn = findViewById(R.id.addFilme_btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = findViewById(R.id.addFilme_radioGroup);

                // Para definir a faixa etária
                    int selecetedBtnId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioBtn = findViewById(selecetedBtnId);
                    // String que contém o texto do botão escolhido //
                    String faixaEtaria = radioBtn.getText().toString();

                Integer id = 0;

                switch(faixaEtaria) {
                    case "L":
                        id = R.drawable.livre;
                        break;
                    case "10":
                        id = R.drawable.dez;
                        break;
                    case "12":
                        id = R.drawable.doze;
                        break;
                    case "14":
                        id = R.drawable.catorze;
                        break;
                    case "16":
                        id = R.drawable.dezesseis;
                        break;
                    case "18":
                        id = R.drawable.dezoito;
                        break;
                }
                // photoId precisa ser final
                final Integer photoId = id;



                Listagem l = new Listagem(nome.getText().toString(), genero.getText().toString(), diretor.getText().toString(), photoId, ano.getText().toString());
                ListagemActivity.AddFilme(l);
                Intent intent = new Intent(AddFilmeActivity.this, ListagemActivity.class);
                startActivity(intent);
            }
        });
    }
}
