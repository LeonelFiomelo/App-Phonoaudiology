package com.example.app_phonoaudiology.domain.entities;

import android.os.Bundle;

public class ConfiguracionEntity {

    private String botonSeleccionado, categoria, subcategoria, ejercicio, tipoRuido;
    private Boolean ruido;
    private Float intensidad;

    public ConfiguracionEntity(Bundle bundle) {
        this.botonSeleccionado = bundle.getString("botonSeleccionado");
        this.categoria = bundle.getString("categoria");
        this.subcategoria = bundle.getString("subcategoria");
        this.ejercicio = bundle.getString("ejercicio");
        this.ruido = bundle.getBoolean("ruido");
        this.intensidad = bundle.getFloat("intensidad", .1f);
        if (this.ruido) {
            this.tipoRuido =  bundle.getString("tipoRuido");
        } else {
            this.tipoRuido = null;
        }
    }

    public String getBotonSeleccionado() { return botonSeleccionado; }
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

    public void setIntensidad(float intensidad) {
        this.intensidad = intensidad;
    }

}
