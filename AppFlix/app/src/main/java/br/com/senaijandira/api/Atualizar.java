package br.com.senaijandira.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import br.com.senaijandira.controller.Producao;
import br.com.senaijandira.util.HttpConnection;

/**
 * Created by 17170077 on 09/05/2018.
 */

public class Atualizar extends AsyncTask<Void, Void, String> {

    private Activity activity;
    private Producao producao;
    private Context context;
    private ProgressDialog progress;

    public Atualizar(Activity activity, Producao producao, Context context){

        this.activity = activity;
        this.producao = producao;
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progress = ProgressDialog.show(context, "Progresso", "Atualizando...", false, false);

    }

    @Override
    protected String doInBackground(Void... voids) {

        String imagem64 = Base64.encodeToString(producao.getImagem(), Base64.DEFAULT);

        HashMap<String, String> valores = new HashMap<>();

        valores.put("titulo", producao.getTitulo());
        valores.put("sinopse", producao.getSinopse());
        valores.put("nota", String.valueOf(producao.getAvaliacao()));
        valores.put("url", producao.getLink());
        valores.put("imagem", imagem64);
        valores.put("id", String.valueOf(producao.getId()));

        String URL = "http://10.0.2.2/INF3T/API_APPFLIX/Atualizar.php";

        String JSON = HttpConnection.post(URL, valores);

        Log.d("!!!!!!!!!!!!!!!!!!!!!!!", JSON);

        return JSON;

    }

    @Override
    protected void onPostExecute(String JSON) {
        super.onPostExecute(JSON);

        if(JSON != null){

            try {

                JSONObject jsonObject = new JSONObject(JSON);

                if(jsonObject.getBoolean("sucesso")){

                    Toast.makeText(activity.getApplicationContext(), "Filme atualizado com sucesso.", Toast.LENGTH_SHORT).show();

                    activity.finish();

                } else {

                    Toast.makeText(activity.getApplicationContext(), "Erro ao atualizar o filme. " +
                            "Verifique os dados digitados. Acesse o suporte caso o erro persista.", Toast.LENGTH_LONG).show();

                }

            } catch (JSONException e) {

                e.printStackTrace();

            }

        }

        progress.dismiss();

    }
}
