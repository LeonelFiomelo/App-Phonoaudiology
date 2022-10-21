package com.example.app_phonoaudiology.infrastructure.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.app_phonoaudiology.domain.entities.ErrorEntity;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "resultado_table")
public class ResultadoEntityDB {

    @PrimaryKey(autoGenerate = true)
    private int resultadoId;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "correctas")
    private int correctas;
    @ColumnInfo(name = "intentos")
    private int intentos;
    @ColumnInfo(name = "categoria")
    private String categoria;
    @ColumnInfo(name = "subcategoria")
    private String subcategoria;
    @ColumnInfo(name = "ejercicio")
    private String ejercicio;
    @ColumnInfo(name = "ruido")
    private Boolean ruido;
    @ColumnInfo(name = "tipoRuido")
    private String tipoRuido;
    @ColumnInfo(name = "intensidad")
    private float intensidad;

    public ResultadoEntityDB(String fecha, int correctas, int intentos, String categoria, String subcategoria, String ejercicio, Boolean ruido, String tipoRuido, float intensidad) {
        this.fecha = fecha;
        this.correctas = correctas;
        this.intentos = intentos;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.ejercicio = ejercicio;
        this.ruido = ruido;
        this.tipoRuido = tipoRuido;
        this.intensidad = intensidad;
    }

    public int getResultadoId() {
        return resultadoId;
    }
    public String getFecha() {
        return fecha;
    }
    public int getCorrectas() {
        return correctas;
    }
    public int getIntentos() {
        return intentos;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getSubcategoria() { return subcategoria; }
    public String getEjercicio() {
        return ejercicio;
    }
    public Boolean getRuido() {
        return ruido;
    }
    public String getTipoRuido() { return tipoRuido; }
    public float getIntensidad() {
        return intensidad;
    }

    public void setResultadoId(int resultadoId) {
        this.resultadoId = resultadoId;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setSubcategoria(String subcategoria) { this.subcategoria = subcategoria; }
    public void setEjercicio(String tipo_ejercicio) {
        this.ejercicio = tipo_ejercicio;
    }
    public void setRuido(Boolean ruido) {
        this.ruido = ruido;
    }
    public void setTipoRuido(String tipoRuido) { this.tipoRuido = tipoRuido; }
    public void setIntensidad(float intensidad) {
        this.intensidad = intensidad;
    }

}
