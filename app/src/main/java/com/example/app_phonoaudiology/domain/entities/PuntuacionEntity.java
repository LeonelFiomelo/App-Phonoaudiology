package com.example.app_phonoaudiology.domain.entities;

import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;

import java.util.ArrayList;

public class PuntuacionEntity {

    private int correctas;
    private int incorrectas;
    private int _intentos;
    private int intentos;
    private int _erroresPermitidos;
    private int erroresPermitidos;
    private Boolean reseteo;
    private ArrayList<ErrorEntityDB> listaDeErrores;

    public PuntuacionEntity(int intentos, int erroresPermitidos) {
        this.correctas = 0;
        this.incorrectas = 0;
        this._intentos = intentos;
        this.intentos = intentos;
        this._erroresPermitidos = erroresPermitidos;
        this.erroresPermitidos = erroresPermitidos;
        this.reseteo = false;
        this.listaDeErrores = new ArrayList<>();
    }

    public void sumarCorrectas() {
        correctas += 1;
    }

    public void sumarIncorrectas() {
        incorrectas += 1;
    }

    public void restarIntentos() { intentos -= 1; }

    public void restarErroresPermitidos() { _erroresPermitidos -= 1; }

    public void resetear(ErrorEntityDB error) {
        this._erroresPermitidos = erroresPermitidos;
        reseteo = !reseteo;
        listaDeErrores.add(error);
    }

    public int getCorrectas() {
        return correctas;
    }

    public int getIncorrectas() {
        return incorrectas;
    }

    public int get_Intentos() { return _intentos; }

    public int getIntentos() { return intentos; }

    public int getErroresPermitidos() { return _erroresPermitidos; }

    public Boolean getReseteo() { return reseteo; }

    public ArrayList<ErrorEntityDB> getListaDeErrores() {
        return listaDeErrores;
    }

}
