package com.mis.acmebe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.mis.acmebe.adapter.VueloAdapter;
import com.mis.acmebe.entity.Vuelo;

import java.util.ArrayList;

public class VuelosActivity extends AppCompatActivity {
    RecyclerView recyclerViewVuelos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelos);

    recyclerViewVuelos=findViewById(R.id.recyclerViewVuelos);
    recyclerViewVuelos.setAdapter(new VueloAdapter(Vuelo.generaVuelos(100)));
    recyclerViewVuelos.setLayoutManager(new GridLayoutManager(this,1));
    }
    public void enlaceFiltro(View view) {

        setContentView(R.layout.activity_filtro);
    }
}