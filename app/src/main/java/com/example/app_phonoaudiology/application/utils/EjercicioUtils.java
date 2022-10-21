package com.example.app_phonoaudiology.application.utils;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.app_phonoaudiology.domain.entities.ConectoresEntity;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;
import com.example.app_phonoaudiology.domain.entities.ErrorEntity;
import com.example.app_phonoaudiology.domain.entities.PuntuacionEntity;
import com.example.app_phonoaudiology.domain.entities.ReporteEntity;
import com.example.app_phonoaudiology.domain.repository.conectores.DiasConectores;
import com.example.app_phonoaudiology.domain.repository.conectores.NumerosConectores;
import com.example.app_phonoaudiology.domain.repository.constants.Constantes;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;

import java.util.ArrayList;
import java.util.List;

public class EjercicioUtils {

    public static void setTodosLosConectores(Context context, SoundRepository soundRepository, ConectoresEntity conectoresEntity) {
        soundRepository.getHoyEsSound().observe((LifecycleOwner) context, sound -> {
            conectoresEntity.putConector("Hoy es", sound);
        });
        soundRepository.getEsHoySound().observe((LifecycleOwner) context, sound -> {
            conectoresEntity.putConector("Es hoy", sound);
        });
        soundRepository.getyLLoviendoSound().observe((LifecycleOwner) context, sound -> {
            conectoresEntity.putConector("Y esta lloviendo", sound);
        });
        soundRepository.getNecesitoSound().observe((LifecycleOwner) context, sound -> {
            conectoresEntity.putConector("Necesito", sound);
        });
        soundRepository.getVasosGrandes().observe((LifecycleOwner) context, sound -> {
            conectoresEntity.putConector("Vasos grandes", sound);
        });
    }

    public static void setRandomConectores(ConfiguracionEntity configuracionEntity, ConectoresEntity conectoresEntity, ArrayList<SoundEntity> combinacionConectores) {
        switch (configuracionEntity.getSubcategoria()) {
            case ("Días de la Semana"):
                SoundEntity conectorInicialDias = null;
                SoundEntity conectorFinalDias = null;
                ArrayList<String> combinacionRandomDias = DiasConectores.getCombinacionRandom();
                if (combinacionRandomDias.get(0) != null && combinacionRandomDias.get(1) != null) {
                    conectorInicialDias = conectoresEntity.getListaDeConectores().get(combinacionRandomDias.get(0));
                    conectorFinalDias = conectoresEntity.getListaDeConectores().get(combinacionRandomDias.get(1));
                }
                if (combinacionRandomDias.get(0) != null && combinacionRandomDias.get(1) == null) {
                    conectorInicialDias = conectoresEntity.getListaDeConectores().get(combinacionRandomDias.get(0));
                    conectorFinalDias = null;
                }
                if (combinacionRandomDias.get(0) == null && combinacionRandomDias.get(1) != null) {
                    conectorInicialDias = null;
                    conectorFinalDias = conectoresEntity.getListaDeConectores().get(combinacionRandomDias.get(1));
                }
                combinacionConectores.add(0, conectorInicialDias);
                combinacionConectores.add(1, conectorFinalDias);
                break;
            case ("Números"):
                SoundEntity conectorInicialNumeros = null;
                SoundEntity conectorFinalNumeros = null;
                ArrayList<String> combinacionRandomNumeros = NumerosConectores.getCombinacionRandom();
                if (combinacionRandomNumeros.get(0) != null && combinacionRandomNumeros.get(1) != null) {
                    conectorInicialNumeros = conectoresEntity.getListaDeConectores().get(combinacionRandomNumeros.get(0));
                    conectorFinalNumeros= conectoresEntity.getListaDeConectores().get(combinacionRandomNumeros.get(1));
                }
                if (combinacionRandomNumeros.get(0) != null && combinacionRandomNumeros.get(1) == null) {
                    conectorInicialNumeros = conectoresEntity.getListaDeConectores().get(combinacionRandomNumeros.get(0));
                    conectorFinalNumeros = null;
                }
                if (combinacionRandomNumeros.get(0) == null && combinacionRandomNumeros.get(1) != null) {
                    conectorInicialNumeros = null;
                    conectorFinalNumeros = conectoresEntity.getListaDeConectores().get(combinacionRandomNumeros.get(1));
                }
                combinacionConectores.add(0, conectorInicialNumeros);
                combinacionConectores.add(1, conectorFinalNumeros);
                break;
            default:
                break;
        }
    }

    public static String getRutaRuido(String tipoRuido) {
        switch (tipoRuido) {
            case ("Multitud de personas"):
                return "ruido_personas.mp3";
            case ("Recreo de niños"):
                return "ruido_recreo.mp3";
            case ("Sirena ambulancia"):
                return "ruido_ambulancia.mp3";
            case ("Tráfico intenso"):
                return "ruido_trafico.mp3";
            default:
                return null;
        }
    }

    public static LiveData<List<SoundEntity>> getOpcionesPorSubcategoria(ConfiguracionEntity configuracionEntity, SoundRepository soundRepository) {
        switch (configuracionEntity.getSubcategoria()) {
            case (Constantes.DIAS_SEMANA):
                return soundRepository.getDiasSounds();
            case (Constantes.NUMEROS):
                return soundRepository.getNumerosSounds();
            case (Constantes.COLORES):
                return soundRepository.getColoresSounds();
            case (Constantes.MESES):
                return soundRepository.getMesesSounds();
            default:
                return null;
        }
    }

    public static Boolean getChequearIntentosRestantes(PuntuacionEntity puntuacionEntity) {
        if (puntuacionEntity.getIntentos() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String[][] getMatrizTransformada(List<ErrorEntity> listaDeErrores) {
        String[][] matriz = new String[listaDeErrores.size()][3];

        for (int i=0; i<matriz.length; i++) {
            matriz[i][0] = listaDeErrores.get(i).getEstimulo();
            matriz[i][1] = listaDeErrores.get(i).getPrimeraRespuesta();
            matriz[i][2] = listaDeErrores.get(i).getSegundaRespuesta();
        }

        return matriz;
    }

    public static String getTitulo(String botonSeleccionado) {
        switch (botonSeleccionado) {
            case ("entrenamiento"):
                return "Entrenamiento";
            case ("evaluacion"):
                return "Evaluación";
            default:
                return null;
        }
    }

}
