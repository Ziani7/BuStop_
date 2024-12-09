package com.example.bustop3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Iterator;

public class CadNovoUsuario extends AppCompatActivity {

    Button btretorna, btregistra;
    EditText edtlogin, edtsenha, edtcidade;
    Usuario usrtemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_novo_usuario);
        btretorna = (Button) findViewById(R.id.btregretornar);
        btregistra = (Button) findViewById(R.id.btregregistrar);
        edtlogin=(EditText) findViewById(R.id.edtreglogin);
        edtsenha=(EditText) findViewById(R.id.edtregsenha);
        edtcidade=(EditText) findViewById(R.id.edtregcidade);

        btregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrtemp = new com.example.bustop3.Usuario(edtlogin.getText().toString(),edtsenha.getText().toString(),edtcidade.getText().toString());
                new Enviajsonpost().execute();
            }
        });
        btretorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CadNovoUsuario.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    class Enviajsonpost extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "http://200.132.172.203/bustop/cadastra_usuario.php";
                JSONObject jsonValores = new JSONObject();
                jsonValores.put("nome", usrtemp.getLogin().toString());
                jsonValores.put("senha", usrtemp.getSenha().toString());
                jsonValores.put("cidade", usrtemp.getCidade().toString());
                conexaouniversal mandar = new conexaouniversal();
                String mensagem=mandar.postJSONObject(url,jsonValores);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }


    }

}