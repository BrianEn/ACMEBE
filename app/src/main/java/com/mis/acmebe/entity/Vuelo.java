package com.mis.acmebe.entity;

public class Vuelo {
    private String origen;
    private String destino;
    private Long precio;
    private Integer escalas;
    private String fechallegada;
    private String fechasalida;
    private Boolean favorito;
    private String imagen;
    private String descripcion;

    public Vuelo(String origen, String destino, Long precio, Integer escalas, String fechallegada, String fechasalida, Boolean favorito, String imagen, String descripcion) {
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.escalas = escalas;
        this.fechallegada = fechallegada;
        this.fechasalida = fechasalida;
        this.favorito = favorito;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }
    public Vuelo(){}

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

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Integer getEscalas() {
        return escalas;
    }

    public void setEscalas(Integer escalas) {
        this.escalas = escalas;
    }

    public String getFechallegada() {
        return fechallegada;
    }

    public void setFechallegada(String fechallegada) {
        this.fechallegada = fechallegada;
    }

    public String getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(String fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vuelo vuelo = (Vuelo) o;

        if (origen != null ? !origen.equals(vuelo.origen) : vuelo.origen != null) return false;
        if (destino != null ? !destino.equals(vuelo.destino) : vuelo.destino != null) return false;
        if (precio != null ? !precio.equals(vuelo.precio) : vuelo.precio != null) return false;
        if (escalas != null ? !escalas.equals(vuelo.escalas) : vuelo.escalas != null) return false;
        if (fechallegada != null ? !fechallegada.equals(vuelo.fechallegada) : vuelo.fechallegada != null)
            return false;
        if (fechasalida != null ? !fechasalida.equals(vuelo.fechasalida) : vuelo.fechasalida != null)
            return false;
        if (favorito != null ? !favorito.equals(vuelo.favorito) : vuelo.favorito != null) return false;
        if (imagen != null ? !imagen.equals(vuelo.imagen) : vuelo.imagen != null)
            return false;
        return descripcion != null ? descripcion.equals(vuelo.descripcion) : vuelo.descripcion == null;
    }

    @Override
    public int hashCode() {
        int result = origen != null ? origen.hashCode() : 0;
        result = 31 * result + (destino != null ? destino.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (escalas != null ? escalas.hashCode() : 0);
        result = 31 * result + (fechallegada != null ? fechallegada.hashCode() : 0);
        result = 31 * result + (fechasalida != null ? fechasalida.hashCode() : 0);
        result = 31 * result + (favorito != null ? favorito.hashCode() : 0);
        result = 31 * result + (imagen != null ? imagen.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", precio='" + precio + '\'' +
                ", escalas=" + escalas +
                ", fechallegada='" + fechallegada + '\'' +
                ", fechasalida='" + fechasalida + '\'' +
                ", favorito=" + favorito +
                ", recursoImageView='" + imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
