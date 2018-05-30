package br.com.senaijandira.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;

import javax.xml.datatype.DatatypeFactory;

import br.com.senaijandira.controller.Producao;
import br.com.senaijandira.room.R;
import br.com.senaijandira.util.ImageHelper;

/**
 * Created by 17170077 on 09/05/2018.
 */

public class ProducaoAdapter extends ArrayAdapter<Producao>{

    public ProducaoAdapter(Context context){

        super(context, 0, new ArrayList<Producao>());

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);

        }

        Producao producao = getItem(position);

        TextView txtTitulo = convertView.findViewById(R.id.txt_titulo_item);
        ImageView imgProducao = convertView.findViewById(R.id.img_item);

        txtTitulo.setText(producao.getTitulo());

        if(!producao.getCaminhoImagem().equals("")){

            Picasso.with(convertView.getContext()).load("http://10.0.2.2/INF3T/API_APPFLIX/Imagens/"+producao.getCaminhoImagem()).into(imgProducao);

        }

        return convertView;

    }
}
