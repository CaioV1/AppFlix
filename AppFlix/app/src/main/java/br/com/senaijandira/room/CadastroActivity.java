package br.com.senaijandira.room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import br.com.senaijandira.api.Atualizar;
import br.com.senaijandira.api.Registrar;
import br.com.senaijandira.controller.Producao;
import br.com.senaijandira.util.HttpConnection;
import br.com.senaijandira.util.ImageHelper;

public class CadastroActivity extends AppCompatActivity {

    RatingBar rt_avaliacao;
    EditText txt_sinopse,txt_link,txt_titulo;
    ImageView img_producao;
    Bitmap foto;

    Producao producao;

    int id = 0;

    int COD_GALERIA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //findview by Ids
        rt_avaliacao = findViewById(R.id.rt_avaliacao);
        txt_sinopse=findViewById(R.id.txt_sinopse);
        txt_link=findViewById(R.id.txt_link);
        txt_titulo=findViewById(R.id.txt_titulo);
        img_producao = findViewById(R.id.img_producao);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", 0);

        if(id != 0){

            new AsyncTask<Void, Void, String>(){

                @Override
                protected String doInBackground(Void... voids) {

                    String URL = "http://10.0.2.2/INF3T/API_APPFLIX/ObterUm.php?id="+id;

                    String JSON = HttpConnection.get(URL);

                    return JSON;
                }

                @Override
                protected void onPostExecute(String JSON) {
                    super.onPostExecute(JSON);

                    if(JSON != null){

                        try {

                            JSONObject jsonObject = new JSONObject(JSON);

                            txt_titulo.setText(jsonObject.getString("titulo"));
                            txt_link.setText(jsonObject.getString("url"));
                            txt_sinopse.setText(jsonObject.getString("sinopse"));
                            rt_avaliacao.setRating(Float.parseFloat(jsonObject.getString("nota")));
                            Picasso.with(getApplicationContext()).load("http://10.0.2.2/INF3T/API_APPFLIX/Imagens/"+jsonObject.getString("imagem")).into(img_producao);

                        } catch (JSONException e) {

                            e.printStackTrace();

                        }
                    }

                }
            }.execute();

        }

    }


    public void salvarFilme(View view) {

        Producao producao = new Producao();

        Intent intent = new Intent(this, MainActivity.class);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) img_producao.getDrawable();

        byte[]imagem = ImageHelper.toByteArray(bitmapDrawable.getBitmap());

        producao.setTitulo(txt_titulo.getText().toString());
        producao.setSinopse(txt_sinopse.getText().toString());
        producao.setAvaliacao(rt_avaliacao.getRating());
        producao.setLink(txt_link.getText().toString());
        producao.setImagem(imagem);

        if(id == 0){

            new Registrar(this, producao, this).execute();

        } else {

            producao.setId(id);

            new Atualizar(this, producao, this).execute();

        }

        startActivity(intent);

        //Log.d("Teste", URL);

    }


    //abrir a galeria de imagens atraves
    //da imageView
    public void abrirGaleria(View v){

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent , COD_GALERIA);
    }

    //Aqui que volta o resultado da chamada a galeria
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_GALERIA ){

            if(resultCode == Activity.RESULT_OK){
                //selecionou alguma coisa

                try {
                    //pegando a img em binario
                    InputStream inp = getContentResolver()
                            .openInputStream(data.getData());

                    //transformando o stream em bitmap
                    foto = BitmapFactory.decodeStream(inp);

                    //colocando a fotinha
                    img_producao.setImageBitmap(foto);

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }
}
