package com.example.bustop3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VizualizarViagens extends AppCompatActivity {

    ListView listView;
    Button btRetorna;
    ArrayAdapter<String> adapter;
    int idviagem;
    int idusr;
    List<String> viagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizar_viagens);

        btRetorna = (Button) findViewById(R.id.btRetornaPPrincipal);
        listView = (ListView) findViewById(R.id.ListaViagens);

        Bundle extras = getIntent().getExtras();
        idusr = extras.getInt("idusr");

        btRetorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VizualizarViagens.this, TelaPrincipal.class);
                i.putExtra("idusr", idusr);
                startActivity(i);
            }
        });
        new EnviajsonpostLogineSenha().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String a = (String) adapterView.getItemAtPosition(i);
                String [] vetor = a.split("_");
                idviagem = (Integer.parseInt(vetor[0]));
                Intent intent = new Intent(VizualizarViagens.this, ConfirmarCompra.class);
                intent.putExtra("idviagem", idviagem);
                intent.putExtra("idusr", idusr);
                startActivity(intent);
            }
        });
    }




 class EnviajsonpostLogineSenha extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            Log.d("TAG", "Entrou no doInBackground");
            try {
                Log.d("TAG", "Entrou no primeiro try");
                String url = "http://200.132.172.203/bustop/consulta_viagens.php";
                JSONObject jsonValores = new JSONObject();
                conexaouniversal mandar = new conexaouniversal();
                String mensagem = mandar.postJSONObject(url, jsonValores);

                try {
                    Log.d("TAG", "Entrou no segundo try");
                    JSONObject jsonobjc = new JSONObject(mensagem);
                    JSONArray jsonvet = jsonobjc.getJSONArray("viagem");
                    for (int i = 0; i < jsonvet.length(); i++) {
                        JSONObject jsonitem = jsonvet.getJSONObject(i);
                        String idviagem = jsonitem.optString("idviagem");
                        String cidade_partida = jsonitem.optString("cidade_partida");
                        String cidade_chegada = jsonitem.optString("cidade_chegada");
                        String hora_saida = jsonitem.optString("hora_saida");
                        String hora_chegada = jsonitem.optString("hora_chegada");

                        String viagem =  idviagem + "_ " + cidade_partida + " - " + cidade_chegada + "\n" + hora_saida + " - " + hora_chegada + "\n               Comprar Passagem";
                        viagens.add(viagem);
                    }
                    adapter = new ArrayAdapter<String>(VizualizarViagens.this, android.R.layout.simple_list_item_1, viagens);
                    listView.setAdapter(adapter);

                } catch (Exception ex) {
                    Log.d("TAG", "Erro ao ler JSON");
                    ex.printStackTrace();
                }

            } catch (Exception e) {
                Log.d("TAG", "Erro ao enviar JSON");
                e.printStackTrace();
            }
            return viagens;
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
