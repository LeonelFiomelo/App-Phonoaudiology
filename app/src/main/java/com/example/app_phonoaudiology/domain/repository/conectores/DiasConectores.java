package com.example.app_phonoaudiology.domain.repository.conectores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DiasConectores {

    public static final ArrayList<String> combinacionUno = new ArrayList<>(Arrays.asList(
            "Hoy es",
            "Y esta lloviendo"
    ));

    public static final ArrayList<String> combinacionDos = new ArrayList<>(Arrays.asList(
            "Hoy es",
            null
    ));

    public static final ArrayList<String> combinacionTres = new ArrayList<>(Arrays.asList(
            null,
            "Es hoy"
    ));

    public static final ArrayList<ArrayList<String>> combinaciones = new ArrayList<>(Arrays.asList(
            combinacionUno,
            combinacionDos,
            combinacionTres
    ));

    // RETORNA UNA COMBINACION AL AZAR
    public static ArrayList<String> getCombinacionRandom() {
        Random random = new Random();
        int cantidad = combinaciones.size();
        return combinaciones.get(random.nextInt(cantidad));
    }

}