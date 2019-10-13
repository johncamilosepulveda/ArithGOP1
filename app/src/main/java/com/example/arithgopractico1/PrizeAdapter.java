package com.example.arithgopractico1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class PrizeAdapter extends BaseAdapter {


    private static LayoutInflater inflater = null;
    Context context;
    Prize[] datos;
    int[] datosImg;

    public PrizeAdapter(Context context, Prize[] datos, int[] datosImg) {
        this.context = context;
        this.datos = datos;
        this.datosImg = datosImg;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        final View vista = inflater.inflate(R.layout.row, null);

        TextView titulo = (TextView) vista.findViewById(R.id.textTitle);
        TextView descripcion = (TextView) vista.findViewById(R.id.textDescription);
        TextView valor = (TextView) vista.findViewById(R.id.textValue);

        ImageView imagen = (ImageView) vista.findViewById(R.id.imagePrice);
        final CheckBox cbCanje = (CheckBox) vista.findViewById(R.id.checkBoxExchange);


        titulo.setText(datos[i].getArticulo());
        descripcion.setText(datos[i].getDescripcion());
        valor.setText(datos[i].getValor()+" Puntos");
        imagen.setImageResource(datosImg[i]);

        cbCanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos[i].setCambiar(true);
            }
        });

        return vista;
    }
}
