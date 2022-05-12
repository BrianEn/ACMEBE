package com.mis.acmebe.entity;

import com.mis.acmebe.MainActivity;
import com.mis.acmebe.MapActivity;
import com.mis.acmebe.R;
import com.mis.acmebe.VuelosActivity;
import com.mis.acmebe.VuelosFavoritosActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Enlace {
    private String descripcion;
    private int recursoImageView;
    private Class clase;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRecursoImageView() {
        return recursoImageView;
    }

    public void setRecursoImageView(int recursoImageView) {
        this.recursoImageView = recursoImageView;
    }

    public Class getClase() {
        return clase;
    }

    public void setClase(Class clase) {
        this.clase = clase;
    }

    public Enlace(String descripcion, int recursoImageView, Class clase) {
        this.descripcion = descripcion;
        this.recursoImageView = recursoImageView;
        this.clase = clase;
    }
    public static List<Enlace> generaEnlace(){
        List<Enlace> lista=new ArrayList<>();
        lista.add(new Enlace("Viajes disponibles", R.drawable.menuvuelo, VuelosActivity.class));
        lista.add(new Enlace("Viajes seleccionados",R.drawable.menuseleccion, VuelosFavoritosActivity.class));
        lista.add(new Enlace("Conoce tu ubicación",R.mipmap.ic_location, MapActivity.class));
        return lista;
    }
}
