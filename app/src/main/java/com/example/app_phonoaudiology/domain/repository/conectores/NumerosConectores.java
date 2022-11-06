package com.example.app_phonoaudiology.domain.repository.conectores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NumerosConectores {

    public static final ArrayList<String> combinacionUno = new ArrayList<>(Arrays.asList(
            "Necesito",
            "Vasos grandes"
    ));

    public static final ArrayList<String> combinacionDos = new ArrayList<>(Arrays.asList(
            "Necesito",
            null
    ));

    public static final ArrayList<String> combinacionTres = new ArrayList<>(Arrays.asList(
            null,
            "Vasos grandes"
    ));

    public static final ArrayList<ArrayList<String>> combinaciones = new ArrayList<>(Arrays.asList(
            combinacionUno,
            combinacionDos,
            combinacionTres
    ));

    // RETORNA UNA COMBINACIÓN AL AZAR
    public static ArrayList<String> getCombinacionRandom(String palabraClave) {
        switch (palabraClave) {
            case "En el Final":
                return combinacionDos;

            case "En el Inicio":
                return combinacionTres;

            case "En el Medio":
                return combinacionUno;

            case "Aleatorio":
                Random random = new Random();
                int cantidad =  combinaciones.size();
                return combinaciones.get(random.nextInt(cantidad));

            default:
                return null;

        }

    }

}
