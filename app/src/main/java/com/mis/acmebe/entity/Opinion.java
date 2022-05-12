package com.mis.acmebe.entity;

public class Opinion {
    private String usuario;
    private String comentario;

    public Opinion(String usuario, String comentario) {
        this.usuario = usuario;
        this.comentario = comentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Opinion opinion = (Opinion) o;

        if (usuario != null ? !usuario.equals(opinion.usuario) : opinion.usuario != null)
            return false;
        return comentario != null ? comentario.equals(opinion.comentario) : opinion.comentario == null;
    }

    @Override
    public int hashCode() {
        int result = usuario != null ? usuario.hashCode() : 0;
        result = 31 * result + (comentario != null ? comentario.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "usuario='" + usuario + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
