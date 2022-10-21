package com.example.app_phonoaudiology.application.utils;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ErrorDeIntentosAlert {

    private String titulo;
    private String mensaje;
    private String botonPositivo;

    public ErrorDeIntentosAlert(String titulo, String mensaje, String botonPositivo) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.botonPositivo = botonPositivo;
    }

    public void MostrarAlerta(Context context) {
        MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(context)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton(botonPositivo, null);
        alert.show();
    }

}
