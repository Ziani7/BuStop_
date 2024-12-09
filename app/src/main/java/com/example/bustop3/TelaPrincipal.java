package com.example.bustop3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPrincipal extends AppCompatActivity {

    Button btComprarPassagem, btVizualizarViagens, btRetorna;
    int idusr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        btComprarPassagem = (Button) findViewById(R.id.btComprarPassagem);
        btVizualizarViagens = (Button) findViewById(R.id.btVizualizarViagens);
        btRetorna = (Button) findViewById(R.id.btRetornaPLogin);

        Bundle extras = getIntent().getExtras();
        idusr = extras.getInt("idusr");

        btComprarPassagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TelaPrincipal.this, ComprarPassagem.class);
                startActivity(i);
            }
        });

        btVizualizarViagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TelaPrincipal.this, VizualizarViagens.class);
                i.putExtra("idusr", idusr);
                startActivity(i);
            }
        });

        btRetorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TelaPrincipal.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}