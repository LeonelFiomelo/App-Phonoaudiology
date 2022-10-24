package com.example.app_phonoaudiology.domain.entities;

public class SonidoEntity {

    private String nombre;
    private String categoria;
    private String ruta;

    public SonidoEntity() {
        this.nombre = null;
        this.categoria = null;
        this.ruta = null;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setRuta(String ruta) { this.ruta = ruta;}
    public String getRuta() { return ruta; }

}
