package com.example.app_phonoaudiology.domain.entities;

import androidx.annotation.Nullable;

public class ErrorEntity {

    private String estimulo;
    private String primeraRespuesta;
    private String segundaRespuesta;

    public ErrorEntity( ) {
        estimulo = "";
        primeraRespuesta = "";
        segundaRespuesta = "";
    }

    public String getEstimulo() {
        return estimulo;
    }

    public String getPrimeraRespuesta() {
        return primeraRespuesta;
    }

    public String getSegundaRespuesta() {
        return segundaRespuesta;
    }

    public void setEstimulo(String estimulo) { this.estimulo = estimulo; }

    public void setPrimeraRespuesta(String primeraRespuesta) { this.primeraRespuesta = primeraRespuesta; }

    public void setSegundaRespuesta(String segundaRespuesta) { this.segundaRespuesta = segundaRespuesta; }

}
