package com.mis.acmebe;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;


import com.google.android.material.snackbar.Snackbar;

public class VueloSingleActivity extends AppCompatActivity {

    ImageView imageViewVuelo;
    ImageButton buttonFavorito;
    Button buttonComprar, buttonOpinar;
    TextView textViewOrigen2,textViewDestino3,textViewPrecio3,textViewEscala3,textViewFechaSalida2,textViewFechaLlegada2,textViewDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelo_single);
        SharedPreferences preferences = getSharedPreferences("preferencias_usuario", Context.MODE_PRIVATE);
        String defaultValue = "No asignado";
        String origen=preferences.getString("origen", defaultValue);
        String destino=preferences.getString("destino", defaultValue);
        String precio=preferences.getString("precio", defaultValue);
        String escala=preferences.getString("escala", defaultValue);
        String salida=preferences.getString("salida", defaultValue);
        String llegada=preferences.getString("llegada", defaultValue);
        String descripcion=preferences.getString("descripcion", defaultValue);
        String imagen=preferences.getString("imagen",(defaultValue));

        textViewOrigen2 =(TextView) findViewById(R.id.textViewOrigen2);
        textViewDestino3 =(TextView) findViewById(R.id.textViewDestino3);
        textViewPrecio3=(TextView) findViewById(R.id.textViewPrecio3);
        textViewEscala3 =(TextView) findViewById(R.id.textViewEscala3);
        textViewFechaSalida2 =(TextView) findViewById(R.id.textViewFechaSalida2);
        textViewFechaLlegada2 =(TextView) findViewById(R.id.textViewFechaLlegada2);
        textViewDescripcion =(TextView) findViewById(R.id.textViewDescripcion);
        imageViewVuelo=(ImageView) findViewById(R.id.imageViewVuelo);
        buttonComprar=(Button) findViewById(R.id.buttonComprar);
        buttonOpinar=(Button) findViewById(R.id.buttonOpinar);
        buttonFavorito=(ImageButton) findViewById(R.id.buttonFavorito);

        textViewOrigen2.setText(origen);
        textViewDestino3.setText(destino);
        textViewPrecio3.setText(precio);
        textViewEscala3.setText(escala);
        textViewFechaSalida2.setText(salida);
        textViewFechaLlegada2.setText(llegada);
        textViewDescripcion.setText(descripcion);
        imageViewVuelo.setImageResource(Integer.parseInt(imagen));
    }

    public void compra(View view) {
        Snackbar.make(VueloSingleActivity.this.textViewDescripcion, "Vuelo comprado con exito", Snackbar.LENGTH_LONG).show();
    }
    public void favorito(View view) {
        Snackbar.make(VueloSingleActivity.this.textViewDescripcion, "Vuelo agregado a favoritos", Snackbar.LENGTH_LONG).show();

    }

    public void opina(View view) {
        setContentView(R.layout.activity_opinion_form);
    }
}