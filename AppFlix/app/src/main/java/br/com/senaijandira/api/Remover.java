package br.com.senaijandira.api;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.senaijandira.adapter.ProducaoAdapter;
import br.com.senaijandira.controller.Producao;
import br.com.senaijandira.util.HttpConnection;

/**
 * Created by 17170077 on 09/05/2018.
 */

public class Remover extends AsyncTask<Void, Void, String> {

    private Context context;
    private int id;

    public Remover(Context context, int id){

        this.context = context;
        this.id = id;

    }

    @Override
    protected String doInBackground(Void... voids) {

        String URL = "http://10.0.2.2/INF3T/API_APPFLIX/remover.php?id=" + id;

        String JSON = HttpConnection.get(URL);

        return JSON;

    }

    @Override
    protected void onPostExecute(String JSON) {
        super.onPostExecute(JSON);

        try {

            JSONObject jsonObject = new JSONObject(JSON);

            if(jsonObject.getBoolean("sucesso")){

                Toast.makeText(context, "Filme removido com sucesso.", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(context, "Erro ao remover o filme. " +
                        "Acesse o suporte caso o erro persista.", Toast.LENGTH_LONG).show();

            }

        } catch (JSONException e) {

            e.printStackTrace();

        }

    }
}
