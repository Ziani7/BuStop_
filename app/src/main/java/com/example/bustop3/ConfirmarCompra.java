package com.example.bustop3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConfirmarCompra extends AppCompatActivity {

    TextView txtInfoViagem;
    int idviagem;
    int idusr;
    String viagem;
    Button btConfirmarCompra, btCancelarCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_compra);
        txtInfoViagem = (TextView) findViewById(R.id.txtInfoViagem);
        btConfirmarCompra = (Button) findViewById(R.id.btConfirmarCompra);
        btCancelarCompra = (Button) findViewById(R.id.btCancelarCompra);

        btCancelarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ConfirmarCompra.this, VizualizarViagens.class);
                startActivity(i);
            }
        });

        btConfirmarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new EnviajsonpostConfirmar().execute();
                Intent intent = new Intent(ConfirmarCompra.this, VizualizarViagens.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        idviagem = extras.getInt("idviagem");
        idusr = extras.getInt("idusr");
        Log.d("TAG", idviagem + " " + idusr);


        new ConfirmarCompra.EnviajsonpostCompra().execute();
    }

    class EnviajsonpostCompra extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "http://200.132.172.203/bustop/consulta_compra.php";
                JSONObject jsonValores = new JSONObject();
                Log.d("TAG2", idviagem+"");
                jsonValores.put("idviagem", idviagem);
                conexaouniversal mandar = new conexaouniversal();
                String mensagem = mandar.postJSONObject(url, jsonValores);

                try {
                    JSONObject jsonobjc = new JSONObject(mensagem);
                    JSONArray jsonvet = jsonobjc.getJSONArray("compra");
                    JSONObject jsonitem = jsonvet.getJSONObject(0);
                    String idviagem = jsonitem.optString("idviagem");
                    String cidade_partida = jsonitem.optString("cidade_partida");
                    String cidade_chegada = jsonitem.optString("cidade_chegada");
                    String hora_saida = jsonitem.optString("hora_saida");
                    String hora_chegada = jsonitem.optString("hora_chegada");

                    viagem = cidade_partida + " - " + cidade_chegada + "\n" + hora_saida + " - " + hora_chegada;
                    Log.d("TAG", viagem);
                    txtInfoViagem.setText(viagem);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    class EnviajsonpostConfirmar extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "http://200.132.172.203/bustop/confirma_compra.php";
                JSONObject jsonValores = new JSONObject();
                jsonValores.put("idusr", idusr);
                jsonValores.put("idviagem", idviagem);
                conexaouniversal mandar = new conexaouniversal();
                String mensagem = mandar.postJSONObject(url, jsonValores);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}