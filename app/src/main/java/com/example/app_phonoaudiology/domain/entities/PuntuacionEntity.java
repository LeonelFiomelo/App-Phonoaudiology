package com.example.app_phonoaudiology.domain.entities;

public class PuntuacionEntity {

    private int correctas;
    private int incorrectas;
    private int intentos;
    private Boolean reseteo;

    public PuntuacionEntity() {
        this.correctas = 0;
        this.incorrectas = 0;
        this.intentos = 0;
        this.reseteo = false;
    }

    public void sumarCorrectas() {
        correctas += 1;
    }

    public void sumarIncorrectas() {
        incorrectas += 1;
    }

    public void sumarIntentos() { intentos += 1; }

    public void resetear() { reseteo = !reseteo; }

    public int getCorrectas() {
        return correctas;
    }

    public int getIncorrectas() {
        return incorrectas;
    }

    public int getIntentos() { return intentos; }

    public Boolean getReseteo() { return reseteo; }

}
