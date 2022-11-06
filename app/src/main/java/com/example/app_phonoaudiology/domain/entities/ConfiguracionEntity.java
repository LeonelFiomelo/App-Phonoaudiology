package com.example.app_phonoaudiology.domain.entities;

import android.os.Bundle;

public class ConfiguracionEntity {

    private String botonSeleccionado, categoria, subcategoria, palabraClave, ejercicio, tipoRuido;
    private boolean ruido;
    private float intensidad;

    public ConfiguracionEntity() {
        this.ruido = false;
        this.intensidad = 0;
    }

    public void setConfiguracion(Bundle bundle) {
        this.botonSeleccionado = bundle.getString("botonSeleccionado");
        this.categoria = bundle.getString("categoria");
        this.subcategoria = bundle.getString("subcategoria");
        this.ejercicio = bundle.getString("ejercicio");
        this.ruido = bundle.getBoolean("ruido");
        if (this.categoria.equals("Oraciones")) {
            this.palabraClave = bundle.getString("palabraClave");
        } else {
            this.palabraClave = null;
        }
        if (this.ruido) {
            this.tipoRuido =  bundle.getString("tipoRuido");
            this.intensidad = bundle.getFloat("intensidad");
        } else {
            this.tipoRuido = null;
            this.intensidad = 0;
        }
    }

    public void setBundle(Bundle bundle) {
        bundle.putString("botonSeleccionado", botonSeleccionado);
        bundle.putString("categoria", categoria);
        bundle.putString("subcategoria", subcategoria);
        bundle.putString("ejercicio", ejercicio);
        bundle.putBoolean("ruido", ruido);
        if (bundle.getString("palabraClave").equals("Oraciones")) {
            bundle.putString("palabraClave", palabraClave);
        } else {
            bundle.putString("palabraClave", null);
        }
        if (bundle.getBoolean("ruido")) {
            bundle.putString("tipoRuido", tipoRuido);
            bundle.putFloat("intensidad", intensidad);
        } else {
            bundle.putString("tipoRuido", null);
            bundle.putFloat("intensidad", 0);
        }
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("botonSeleccionado", botonSeleccionado);
        bundle.putString("categoria", categoria);
        bundle.putString("subcategoria", subcategoria);
        bundle.putString("ejercicio", ejercicio);
        bundle.putBoolean("ruido", ruido);
        bundle.putString("palabraClave", palabraClave);
        if (bundle.getBoolean("ruido")) {
            bundle.putString("tipoRuido", tipoRuido);
            bundle.putFloat("intensidad", intensidad);
        } else {
            bundle.putString("tipoRuido", null);
            bundle.putFloat("intensidad", 0);
        }
        return bundle;
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
    public String getPalabraClave() { return palabraClave; }
    public Boolean getRuido() { return ruido; }
    public String getTipoRuido() { return tipoRuido; }
    public Float getIntensidad() {return intensidad; }

    public void setBotonSeleccionado(String botonSeleccionado) { this.botonSeleccionado = botonSeleccionado; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setSubcategoria(String subcategoria) { this.subcategoria = subcategoria; }
    public void setEjercicio(String ejercicio) { this.ejercicio = ejercicio; }
    public void setPalabraClave(String palabraClave) { this.palabraClave = palabraClave; }
    public void setRuido(Boolean ruido) { this.ruido = ruido; }
    public void setTipoRuido(String tipoRuido) { this.tipoRuido = tipoRuido; }
    public void setIntensidad(float intensidad) {
        this.intensidad = intensidad;
    }

}
