package com.example.bustop3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ComprarPassagem extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    int idusr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_passagem);

        listView = (ListView) findViewById(R.id.ListaPassagens);

        Bundle extras = getIntent().getExtras();
        idusr = extras.getInt("idusr");
        Log.d("idTelaVizualizar", idusr+"");

        new EnviajsonpostConsultarPassagens().execute();
    }

        class EnviajsonpostConsultarPassagens extends AsyncTask<String, Void, List<String>> {

            @Override
            protected List<String> doInBackground(String... params) {

                List<String> passagens = new ArrayList<>();
                try {
                    Log.d("TAG", "Entrou no primeiro try");
                    String url = "http://200.132.172.203/bustop/consulta_passagens.php";
                    JSONObject jsonValores = new JSONObject();
                    jsonValores.put("idusr", idusr);
                    conexaouniversal mandar = new conexaouniversal();
                    String mensagem = mandar.postJSONObject(url, jsonValores);

                    try {
                        JSONObject jsonobjc = new JSONObject(mensagem);
                        JSONArray jsonvet = jsonobjc.getJSONArray("passagem");
                        for (int i = 0; i < jsonvet.length(); i++) {
                            JSONObject jsonitem = jsonvet.getJSONObject(i);
                            String nomeUsuario = jsonitem.optString("nome");
                            String cidade_partida = jsonitem.optString("cidade_partida");
                            String cidade_chegada = jsonitem.optString("cidade_chegada");
                            String hora_saida = jsonitem.optString("hora_saida");
                            String hora_chegada = jsonitem.optString("hora_chegada");
                            String passagem = nomeUsuario + "\n " + cidade_partida + " - " + cidade_chegada + "\n" + hora_saida + " - " + hora_chegada;
                            passagens.add(passagem);
                        }
                        adapter = new ArrayAdapter<String>(ComprarPassagem.this, android.R.layout.simple_list_item_1, passagens);
                        listView.setAdapter(adapter);

                    } catch (Exception ex) {
                        Log.d("TAG", "Erro ao ler JSON");
                        ex.printStackTrace();
                    }

                } catch (Exception e) {
                    Log.d("TAG", "Erro ao enviar JSON");
                    e.printStackTrace();
                }
                return passagens;
            }

            @Override
            protected void onPostExecute(List<String> result) {
                super.onPostExecute(result);
                if (result != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
