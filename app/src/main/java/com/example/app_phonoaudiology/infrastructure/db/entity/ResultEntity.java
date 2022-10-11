package com.example.app_phonoaudiology.infrastructure.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "result_table")
public class ResultEntity {

    // COLUMNAS
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "tipo_ejercicio")
    private String tipo_ejercicio;
    @ColumnInfo(name = "categoria")
    private String categoria;
    @ColumnInfo(name = "ruidio")
    private String ruido;
    @ColumnInfo(name = "intensidad")
    private String intensidad;
    @ColumnInfo(name = "errores")
    private String errores;
    @ColumnInfo(name = "aciertos")
    private String aciertos;
    @ColumnInfo(name = "resultado")
    private String resultado;

    // CONSTRUCTOR
    public ResultEntity(String fecha, String tipo_ejercicio, String categoria, String ruido, String intensidad, String errores, String aciertos, String resultado) {
        this.fecha = fecha;
        this.tipo_ejercicio = tipo_ejercicio;
        this.categoria = categoria;
        this.ruido = ruido;
        this.intensidad = intensidad;
        this.errores = errores;
        this.resultado = resultado;
        this.aciertos = aciertos;
    }

    // METODOS
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getTipo_ejercicio() {
        return tipo_ejercicio;
    }
    public void setTipo_ejercicio(String tipo_ejercicio) {
        this.tipo_ejercicio = tipo_ejercicio;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getRuido() {
        return ruido;
    }
    public void setRuido(String ruido) {
        this.ruido = ruido;
    }
    public String getIntensidad() {
        return intensidad;
    }
    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }
    public String getErrores() {
        return errores;
    }
    public void setErrores(String errores) {
        this.errores = errores;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public String getAciertos() {
        return aciertos;
    }
    public void setAciertos(String aciertos) {
        this.aciertos = aciertos;
    }

}
