package com.example.willian.movieme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogarActivity extends Activity {

    private static final String TAG = "CustomAuthActivity";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    //private String s; //para converter de long para string
    private String nome;
    private String genero;
    private String diretor;
    private long photoId;
    private String ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Logar

        final EditText usuario = findViewById(R.id.lbl_usuario);
        final EditText senha = findViewById(R.id.lbl_senha);
        Button btn_Logar = findViewById(R.id.btn_login);
        Button btn_cad = findViewById(R.id.btn_cad);

        //quando clicar no botão para entrar
        btn_Logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(usuario.getText().toString(), senha.getText().toString())
                        .addOnCompleteListener(LogarActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //adiciona o nome do usuário na classe Login
                                    String[] user  = usuario.getText().toString().split("@");
                                    Login.setUsuario(user[0]);
                                    Login.setEmail(usuario.getText().toString());
                                    //carrega a lista de filmes na tela de listagem
                                    mDatabase.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            Login.setQuantidadeFilmes(dataSnapshot.child("Usuarios").child(Login.getUsuario()).getChildrenCount());//pega a quantidade de filmes que o usuario tem
                                            if (Login.getQuantidadeFilmes() >= 1) {// se o usuario tiver um filme registrado no banco de dados, vai recuperar os dados
                                                for (long i = 1; i <= Login.getQuantidadeFilmes(); i++) {
                                                    //s = String.valueOf(i);

                                                    Toast.makeText(LogarActivity.this, nome, Toast.LENGTH_SHORT).show();

                                                    nome = (String) dataSnapshot.child("Usuarios").child(Login.getUsuario()).child(String.valueOf(i)).child("Nome").getValue();
                                                    genero = (String) dataSnapshot.child("Usuarios").child(Login.getUsuario()).child(String.valueOf(i)).child("Genero").getValue();
                                                    diretor = (String) dataSnapshot.child("Usuarios").child(Login.getUsuario()).child(String.valueOf(i)).child("Diretor").getValue();
                                                    ano = (String) dataSnapshot.child("Usuarios").child(Login.getUsuario()).child(String.valueOf(i)).child("Ano").getValue();
                                                    //photoId = (long) dataSnapshot.child("Usuarios").child(Login.getUsuario()).child(String.valueOf(i)).child("photoId").getValue();
                                                    photoId = 2131034138;

                                                    Listagem l = new Listagem(nome, genero, diretor, photoId, ano);
                                                    ListagemActivity.AddFilme(l);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            Log.e("TAG", databaseError.getMessage());
                                        }
                                    });

                                    // cadastro sucesso
                                    Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(LogarActivity.this, "Login realizado.", Toast.LENGTH_SHORT).show();
                                    //se estiver tudo certo vai pra tela de filmes
                                    Intent intent = new Intent(LogarActivity.this, ListagemActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    // em caso de falha, mostra um toast
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(LogarActivity.this, "Falha na autenticação.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
        btn_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentt = new Intent(LogarActivity.this, CadastrarActivity.class);
                startActivity(intentt);
            }
        });
    }
}
