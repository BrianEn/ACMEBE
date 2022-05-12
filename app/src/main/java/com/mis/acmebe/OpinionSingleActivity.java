package com.mis.acmebe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class OpinionSingleActivity extends AppCompatActivity {
    TextView textViewUsuario, textViewOpinion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion_single);


        SharedPreferences preferences = getSharedPreferences("preferencias_opinion", Context.MODE_PRIVATE);
        String defaultValue = "No asignado";
        String usuario=preferences.getString("usuario", defaultValue);
        String opinion=preferences.getString("opinion", defaultValue);


        textViewUsuario =(TextView) findViewById(R.id.textViewUsuario);
        textViewOpinion =(TextView) findViewById(R.id.textViewOpinion);

        textViewUsuario.setText(usuario);
        textViewOpinion.setText(opinion);

    }
}