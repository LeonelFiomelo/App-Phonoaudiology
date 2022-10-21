package com.example.app_phonoaudiology.domain.entities;

import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OpcionEntity {

    private SoundEntity respuestaCorrecta;

    public OpcionEntity(List<SoundEntity> listaDeSonidos) {
        this.respuestaCorrecta = getSonidoCorrecto(getListaDeSonidos(listaDeSonidos));
    }

    public SoundEntity getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    private List<SoundEntity> getListaDeSonidos(List<SoundEntity> listaDeSonidos) {
        Collections.shuffle(listaDeSonidos);
        return listaDeSonidos;
    }

    private SoundEntity getSonidoCorrecto(List<SoundEntity> listaDeSonidos) {
        Random random = new Random();
        int correctOption = random.nextInt(listaDeSonidos.size());
        return listaDeSonidos.get(correctOption);
    }

}
