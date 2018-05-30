package br.com.senaijandira.room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Blob;
import java.util.ArrayList;

import br.com.senaijandira.adapter.ProducaoAdapter;
import br.com.senaijandira.controller.Producao;
import br.com.senaijandira.util.HttpConnection;
import br.com.senaijandira.util.ImageHelper;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView listView;

    ProducaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (GridView) findViewById(R.id.list_view);

        adapter = new ProducaoAdapter(this);

        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            startActivity(new Intent(view.getContext(), CadastroActivity.class));

            }
        });

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... voids) {

                String URL = "http://10.0.2.2/INF3T/API_APPFLIX/Obter.php";

                String JSON = HttpConnection.get(URL);

                return JSON;
            }

            @Override
            protected void onPostExecute(String JSON) {
                super.onPostExecute(JSON);

                adapter.addAll(new ArrayList<Producao>());

                ArrayList<Producao> listaProducoes = new ArrayList<>();

                if(JSON != null){

                    try {

                        JSONArray jsonArray = new JSONArray(JSON);

                        for(int i = 0; i < jsonArray.length(); i++){

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Producao producao = new Producao();

                            producao.setId(jsonObject.getInt("id_producao"));
                            producao.setTitulo(jsonObject.getString("titulo"));
                            producao.setSinopse(jsonObject.getString("sinopse"));
                            producao.setCaminhoImagem(jsonObject.getString("imagem"));
                            producao.setAvaliacao(jsonObject.getLong("nota"));
                            producao.setLink(jsonObject.getString("url"));

                            listaProducoes.add(producao);

                        }

                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                    adapter.clear();

                    adapter.addAll(listaProducoes);

                }

            }
        }.execute();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Producao item = adapter.getItem(position);

        Intent intent = new Intent(this, VisualizarActivity.class);

        //Toast.makeText(this, "Você clicou " + item.getId(), Toast.LENGTH_LONG).show();

        intent.putExtra("id", item.getId());

        startActivity(intent);

    }
}
