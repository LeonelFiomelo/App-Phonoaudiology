package com.example.app_phonoaudiology.domain.entities;

public class ConfiguracionEntity {

    private String categoria, subcategoria, ejercicio, tipoRuido;
    private Boolean ruido;
    private Float intensidad;

    public ConfiguracionEntity(String categoria, String subcategoria, String ejercicio, Boolean ruido, String tipoRuido, Float intensidad) {
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.ejercicio = ejercicio;
        this.ruido = ruido;
        this.tipoRuido =  tipoRuido;
        this.intensidad = intensidad;
    }

    public String getCategoria() {
        return categoria;
    }
    public String getSubcategoria() {
        return subcategoria;
    }
    public String getEjercicio() {
        return ejercicio;
    }
    public Boolean getRuido() { return ruido; }
    public String getTipoRuido() { return tipoRuido; }
    public Float getIntensidad() {return intensidad; }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }
    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }
    public void setRuido(Boolean ruido) {
        this.ruido = ruido;
    }
    public void setTipoRuido(String tipoRuido) {
        this.tipoRuido = tipoRuido;
    }
    public void setIntensidad(Float intensidad) {
        this.intensidad = intensidad;
    }

}
