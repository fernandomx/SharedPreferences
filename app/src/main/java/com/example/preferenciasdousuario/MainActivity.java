package com.example.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia"; //valor que não muda, static -> sempre será o mesmo


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.editNome);
        textResultado = findViewById(R.id.textResultado);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Classe utilizada para salvar dados direto no dispositivo do usuário
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0); // MODE 0 Modo privado, somente o aplicativo vai salvar e ler o arquivo.
                SharedPreferences.Editor editor =  preferences.edit();  //retorna um objeto para edição
                String nome = editNome.getText().toString();
                Toast aviso = Toast.makeText(getApplicationContext(),"Preencha o nome", Toast.LENGTH_LONG);

                //Validar o nome, verificando se está vazio
                if (nome.equals("")){
                    //Toast.makeText(getApplicationContext(),"Preencha o nome", Toast.LENGTH_LONG).show();
                    aviso.show();
                }else {
                    editor.putString("nome",nome);
                    editor.commit();
                    textResultado.setText("Olá, " + nome);
                }
            }
        });

        //Recupera dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

        //valida se temos o nome em preferencias
        if (preferences.contains("nome")){

            String nome = preferences.getString("nome","usuário não definido");
            textResultado.setText("Olá," + nome);

        } else {
            textResultado.setText("Olá, usuário não definido");
        }


    }
}
