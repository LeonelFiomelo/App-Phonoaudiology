package com.example.app_phonoaudiology.domain.entities;

import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;

import java.util.ArrayList;
import java.util.List;

public class PuntuacionEntity {

    private int correctas;
    private int incorrectas;
    private int _intentos;
    private int intentos;
    private int _erroresPermitidos;
    private int erroresPermitidos;
    private Boolean reseteo;
    private Boolean intentoExtra;
    private List<ErrorEntityDB> listaDeErrores;

    public PuntuacionEntity(int intentos, int erroresPermitidos) {
        this.correctas = 0;
        this.incorrectas = 0;
        this._intentos = intentos;
        this.intentos = intentos;
        this._erroresPermitidos = erroresPermitidos;
        this.erroresPermitidos = erroresPermitidos;
        this.reseteo = false;
        this.intentoExtra = false;
        this.listaDeErrores = new ArrayList<>();
    }

    public void sumarCorrectas() {
        correctas += 1;
    }

    public void sumarIncorrectas() {
        incorrectas += 1;
    }

    public void restarIntentos() { intentos -= 1; }

    public void sumarIntentos() { intentos += 1; }

    public void cambiarCantidadIntentos(int nuevosIntentos) { _intentos = nuevosIntentos; }

    public void restarErroresPermitidos() { _erroresPermitidos -= 1; }

    public void resetear() {
        this._erroresPermitidos = erroresPermitidos;
        reseteo = !reseteo;
    }

    public void activarIntentoExtra() { intentoExtra = true; }

    public void guardarError(ErrorEntityDB error) {
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

    public Boolean getIntentoExtra() { return intentoExtra; }

    public List<ErrorEntityDB> getListaDeErrores() {
        return listaDeErrores;
    }

}
