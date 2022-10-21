package com.example.app_phonoaudiology.domain.entities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReporteEntity {

    private int puntuacion;
    private int intentos;
    private String fecha;
    private String ejercicio;
    private String categoria;
    private String ruido;
    private Float intensidad;
    private SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");

    public ReporteEntity(PuntuacionEntity puntuacionEntity, ConfiguracionEntity configuracionEntity) {
        this.puntuacion = puntuacionEntity.getCorrectas();
        this.intentos = puntuacionEntity.get_Intentos();
        this.fecha = fechaFormato.format(new Date());
        this.ejercicio = configuracionEntity.getEjercicio();
        this.categoria = configuracionEntity.getCategoria();
        this.ruido = configuracionEntity.getTipoRuido();
        this.intensidad = configuracionEntity.getIntensidad();
    }

    public void setReporteBundle(Bundle bundle) {
        bundle.putInt("puntuacion", puntuacion);
        bundle.putInt("intentos", intentos);
        bundle.putString("fecha", fecha);
        bundle.putString("ejercicio", ejercicio);
        bundle.putString("categoria", categoria);
        bundle.putString("ruido", ruido);
        bundle.putFloat("intensidad", intensidad);
    }

}
