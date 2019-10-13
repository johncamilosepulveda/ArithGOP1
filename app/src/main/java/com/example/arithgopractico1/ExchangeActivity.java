package com.example.arithgopractico1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arithgopractico1.Persistencia.Entity.Score;

public class ExchangeActivity extends AppCompatActivity {


    private TextView txt_puntaje;
    private Button butCanjear;
    ListView listaPrices;

    private Score puntaje;
    private QuestionActivity operaciones;

    Prize[] datos = {
            new Prize("Lapicero Icesi", "Lapicero con estampado de Icesi disponible en colores negro, verde y azul", 20, 1, false),
            new Prize("Cuaderno Icesi", "Cuaderno con estampado de Icesi, argollado y con 80 hojas", 30, 2, false),
            new Prize("Libreta Icesi", "Libreta con estampado de Icesi, cocida y con 100 hojas", 40,3, false),
            new Prize("Camiseta Icesi", "Camiseta con bordado de Icesi disponible en tallas S, M, L y XL", 80,4,false),
            new Prize("Saco Icesi", "Saco con estampado de Icesi disponible en tallas S, M, L y XL", 100, 5, false)
    };

    int[] datosImg = {R.drawable.lapicero_1, R.drawable.cuaderno_2, R.drawable.libreta_3, R.drawable.camiseta_4, R.drawable.saco_5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        operaciones = new QuestionActivity();
        puntaje = new Score(operaciones.getPuntaje().getScore());

        txt_puntaje = (TextView) findViewById(R.id.puntaje);
        butCanjear = (Button) findViewById(R.id.butCanjear);
        listaPrices = (ListView) findViewById(R.id.ListPrices);
        listaPrices.setAdapter(new PrizeAdapter(this, datos, datosImg));


        txt_puntaje.setText(puntaje.getScore()+" ");

        butCanjear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int total = 0;
                    for (int i = 0; datos.length > i; i++){
                        if(datos[i].isCambiar()){
                            total += datos[i].getValor();
                        }
                    }

                if(total < puntaje.getScore()) {
                    Toast.makeText(ExchangeActivity.this, "Usted acaba de canjear los articulos seleccionados", Toast.LENGTH_LONG).show();
                    txt_puntaje.setText(puntaje.getScore()-total+" ");
                }else{
                    Toast.makeText(ExchangeActivity.this, "No tiene los puntos suficientes para realizar el canje", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
