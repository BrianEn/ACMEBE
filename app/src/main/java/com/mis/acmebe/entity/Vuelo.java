package com.mis.acmebe.entity;

import com.mis.acmebe.R;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private String origen;
    private String destino;
    private String precio;
    private String escala;
    private String llegada;
    private String salida;
    private Boolean online;
    private int recursoImageView;

    public Vuelo(String origen, String destino, String precio, String escala, String llegada, String salida, Boolean online, int recursoImageView) {
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.escala = escala;
        this.llegada = llegada;
        this.salida = salida;
        this.online = online;
        this.recursoImageView = recursoImageView;
    }

    public String getLlegada() {
        return llegada;
    }

    public void setLlegada(String llegada) {
        this.llegada = llegada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public int getRecursoImageView() {
        return recursoImageView;
    }

    public void setRecursoImageView(int recursoImageView) {
        this.recursoImageView = recursoImageView;
    }

    public static List<Vuelo> generaVuelos(int max){
        List<Vuelo> lista= new ArrayList<>(max);
        for(int i=0;i<max;i++){
            lista.add(new Vuelo("Seville","London","300","1","01/01/2022","02/03/2022",i==0, R.drawable.menuseleccion));
        }
        return lista;
    }
}
