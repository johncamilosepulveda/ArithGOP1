package com.example.arithgopractico1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arithgopractico1.Persistencia.Entity.Score;

public class QuestionActivity extends AppCompatActivity {

    private Score puntaje;
    private TextView txtpuntaje;
    private  TextView txtoperacion;
    private EditText resultado;
    private Button btnresultado;
    private Button btnpregunta;

    private int operadorA;
    private int operadorB;
    private char operando;

    public QuestionActivity(){
        puntaje = new Score(0);

    }

    public Score getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Score puntaje){
        this.puntaje = puntaje;
    }

    public void generarOperadoresAleatorios(){

        operadorA = (int) (Math.random() * 150) + 1;
        operadorB = (int) (Math.random() * 150) + 1;
    }

    public void generarOperandoAleatorio(){

        int caracteres[] = {42,43,45,47};

        operando = (char)caracteres[(int) (Math.random()*4)];

    }

    public int operacion(){

        int resultado = 0;

        switch(operando){
            case '+':
                resultado = operadorA+operadorB;
                break;
            case '-':
                resultado = operadorA-operadorB;
                break;
            case '*':
                resultado = operadorA*operadorB;
                break;
            case '/':
                resultado = operadorA/operadorB;
                break;
        }

        return resultado;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        txtpuntaje = findViewById(R.id.txt_puntaje);
        txtpuntaje.setText("0");
        resultado = findViewById(R.id.Etxt_resultado);
        btnpregunta = findViewById(R.id.btn_pregunta);
        btnresultado = findViewById(R.id.btn_resultado);
        txtoperacion = findViewById(R.id.txtV_operacion);

        btnpregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                generarOperadoresAleatorios();
                generarOperandoAleatorio();
                txtoperacion.setText(operadorA+" "+operando+" "+operadorB);

            }
        });

        btnresultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int resul = operacion();
                String usu = resultado.getText().toString();
                int resulusu = Integer.parseInt(usu);
                int actual = puntaje.getScore();

                if(resul == resulusu){
                    Toast.makeText(QuestionActivity.this, "Resultado Correcto", Toast.LENGTH_LONG).show();
                    actual++;
                    txtpuntaje.setText(actual+"");

                    puntaje.setScore(actual);
                }else{
                    Toast.makeText(QuestionActivity.this, "Resultado Incorrecto", Toast.LENGTH_LONG).show();
                    actual--;
                    txtpuntaje.setText(actual+"");
                    puntaje.setScore(actual);
                }

            }
        });


    }
}
