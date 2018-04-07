package com.example.willian.movieme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.w3c.dom.Text;

/* TODO
    deixar a interface mais amigável para o usuário
    colocar a activity na frente, como uma dialog (desejável)
 */

public class AddFilmeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filme);

        // Recupera os componentes da activity //
        final EditText nome = findViewById(R.id.addFilme_nome);
        final EditText genero = findViewById(R.id.addFilme_genero);
        final EditText diretor = findViewById(R.id.addFilme_diretor);
        final EditText ano = findViewById(R.id.addFilme_ano);
        final RadioGroup radioGroup = findViewById(R.id.addFilme_radioGroup);
        Button btn = findViewById(R.id.addFilme_btnAdd);

        // Quando clicar no botão //

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verifica se os campos obrigatórios estão preenchidos //

                if (TextUtils.isEmpty(nome.getText())){
                    Toast.makeText(AddFilmeActivity.this, "Nome do filme é obrigatório", Toast.LENGTH_SHORT).show();
                    nome.setError("Nome do filme é obrigatório");
                }else if (TextUtils.isEmpty(genero.getText())){
                    Toast.makeText(AddFilmeActivity.this, "Gênero do filme é obrigatório", Toast.LENGTH_SHORT).show();
                    genero.setError("Gênero do filme é obrigatório");
                }else if (TextUtils.isEmpty(ano.getText())){
                    Toast.makeText(AddFilmeActivity.this, "Ano de lançamento do filme é obrigatório", Toast.LENGTH_SHORT).show();
                    ano.setError("Ano do filme é obrigatório");

                // Diferente para RadioGroup, não está funcionando //
                }else if (radioGroup.getCheckedRadioButtonId() == -1){ // Nenhum RadioButton selecionado
                    Toast.makeText(AddFilmeActivity.this, "Classificação indicativa do filme é obrigatória", Toast.LENGTH_SHORT).show();
                }else {


                    // Para definir a faixa etária
                    int selecetedBtnId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioBtn = findViewById(selecetedBtnId);
                    // String que contém o texto do botão escolhido //
                    String faixaEtaria = radioBtn.getText().toString();

                    Integer id = 0;

                    switch (faixaEtaria) {
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


                    // Adiciona filme na lista //
                    Listagem l = new Listagem(nome.getText().toString(), genero.getText().toString(), diretor.getText().toString(), photoId, ano.getText().toString());
                    ListagemActivity.AddFilme(l);
                    // Reset os campos //
                    nome.setText("");
                    genero.setText("");
                    diretor.setText("");
                    ano.setText("");
                    radioGroup.clearCheck();
                    // Volta para a activity principal //
                    Intent intent = new Intent(AddFilmeActivity.this, ListagemActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
