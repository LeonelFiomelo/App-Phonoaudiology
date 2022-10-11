package com.example.app_phonoaudiology.domain.entities;

import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.util.List;

public class OptionAnswerEntity {

    private SoundEntity respuestaCorrecta;
    private List<SoundEntity> listaDeOpciones;

    public OptionAnswerEntity(List<SoundEntity> listaDeOpciones, SoundEntity respuestaCorrecta)  {
        this.listaDeOpciones = listaDeOpciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public List<SoundEntity> getOptionsList() {
        return listaDeOpciones;
    }

    public void setListaDeOpciones(List<SoundEntity> listaDeOpciones) {
        this.listaDeOpciones = listaDeOpciones;
    }

    public SoundEntity getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(SoundEntity respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
}
