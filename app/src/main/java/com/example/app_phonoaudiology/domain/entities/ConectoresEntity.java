package com.example.app_phonoaudiology.domain.entities;

import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.util.HashMap;
import java.util.Map;

public class ConectoresEntity {

    private Map<String, SoundEntity> map;

    public ConectoresEntity() {
        map = new HashMap<>();
    }

    public Map<String, SoundEntity> getListaDeConectores() {
        return map;
    }

    public void putConector(String nombre, SoundEntity conector) {
        map.put(nombre, conector);
    }

}
