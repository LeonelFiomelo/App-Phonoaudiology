package com.example.app_phonoaudiology.domain.entities;

import androidx.annotation.Nullable;

import com.example.app_phonoaudiology.domain.repository.constants.Constantes;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OpcionesEntity {

    private SoundEntity respuestaCorrecta;
    private List<SoundEntity> listaDeOpciones;

    public OpcionesEntity(List<SoundEntity> listaDeSonidosCompleta, String tipoDeEjercicio)  {
        int cantidadDeOpciones = getCantidadDeOpciones(listaDeSonidosCompleta, tipoDeEjercicio);
        this.listaDeOpciones = getListaDeOpciones(listaDeSonidosCompleta, cantidadDeOpciones);
        this.respuestaCorrecta = getOpcionCorrecta(listaDeOpciones);
    }

    public List<SoundEntity> getListaDeOpciones() {
        return listaDeOpciones;
    }
    public SoundEntity getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    private int getCantidadDeOpciones(List<SoundEntity> lista, String tipoDeEjercicio) {
        switch (tipoDeEjercicio) {
            case Constantes.J_DISCRIMINAR:
                return 2;
            case Constantes.J_IDENTIFICAR_TRES_OPCIONES:
                return 3;
            case Constantes.J_IDENTIFICAR_CINCO_OPCIONES:
                return 5;
            case Constantes.J_TODA_LA_CATEGORIA:
                return lista.size();
            default:
                return 0;
        }
    }
    private List<SoundEntity> getListaDeOpciones(List<SoundEntity> list, int optionsQuantity) {
        List<SoundEntity> newOptionsList = new ArrayList<>();
        Collections.shuffle(list);
        for (int i=0; i<optionsQuantity; i++) {
            newOptionsList.add(list.get(i));
        }
        return newOptionsList;
    }
    private SoundEntity getOpcionCorrecta(List<SoundEntity> list) {
        Random random = new Random();
        int correctOption = random.nextInt(list.size());
        return list.get(correctOption);
    }

}
