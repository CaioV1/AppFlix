package br.com.senaijandira.room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.senaijandira.api.Remover;
import br.com.senaijandira.util.HttpConnection;

public class VisualizarActivity extends AppCompatActivity {

    RatingBar rtNota;

    TextView txtSinopse;
    TextView txtTitulo;

    ImageView imgProducao;

    String link = "";

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rtNota = (RatingBar) findViewById(R.id.rt_visualizar_avaliacao);

        txtSinopse = (TextView) findViewById(R.id.txt_visualizar_sinopse);
        txtTitulo = (TextView) findViewById(R.id.txt_titulo_visualizar);

        imgProducao = (ImageView) findViewById(R.id.img_producao);

        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... voids) {

                Intent intent = getIntent();

                String URL = "http://10.0.2.2/INF3T/API_APPFLIX/ObterUm.php?id="+intent.getIntExtra("id", 0);

                String JSON = HttpConnection.get(URL);

                return JSON;
            }

            @Override
            protected void onPostExecute(String JSON) {
                super.onPostExecute(JSON);

                if(JSON != null){

                    try {

                        JSONObject jsonObject = new JSONObject(JSON);

                        rtNota.setRating(Float.parseFloat(jsonObject.getString("nota")));
                        txtSinopse.setText(jsonObject.getString("sinopse"));
                        txtTitulo.setText(jsonObject.getString("titulo"));
                        Picasso.with(getApplicationContext()).load("http://10.0.2.2/INF3T/API_APPFLIX/Imagens/"+jsonObject.getString("imagem")).into(imgProducao);
                        link = jsonObject.getString("url");

                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                }

            }
        }.execute();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_visualizar, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = getIntent();

        id = intent.getIntExtra("id", 0);

        if(item.getItemId() == R.id.excluir){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Excluir");
            builder.setMessage("Deseja realmente excluir?");

            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    new Remover(getApplicationContext(), id).execute();

                    finish();

                }
            });

            builder.setNegativeButton("Não", null);

            builder.show();

        }

        if(item.getItemId() == R.id.editar){

            intent = new Intent(this, CadastroActivity.class);

            intent.putExtra("id", id);

            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);

    }

    public void assistirProducao(View view) {

        Uri uri = Uri.parse(link);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);

    }
}
