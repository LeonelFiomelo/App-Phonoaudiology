package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Application;
import android.content.Context;

import android.os.Bundle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.application.adapters.OptionsAdapter;
import com.example.app_phonoaudiology.domain.repository.conectores.DiasConectores;
import com.example.app_phonoaudiology.domain.repository.conectores.NumerosConectores;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;
import com.example.app_phonoaudiology.domain.entities.ConectoresEntity;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;
import com.example.app_phonoaudiology.domain.entities.OptionAnswerEntity;
import com.example.app_phonoaudiology.domain.entities.PuntuacionEntity;
import com.example.app_phonoaudiology.application.usecases.TouchOptionButton;
import com.example.app_phonoaudiology.application.usecases.TouchPlayButton;
import com.example.app_phonoaudiology.domain.repository.constants.Constantes;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EjercicioOpcionesViewModel extends ViewModel {

    private SoundRepository soundRepository;
    private ConfiguracionEntity configuracionEntity;
    private OptionAnswerEntity opciones;
    private PuntuacionEntity puntuacionEntity;
    private ConectoresEntity conectoresEntity;
    private ArrayList<SoundEntity> combinacionConectores;
    private MutableLiveData<Integer> correctas;
    private MutableLiveData<Integer> incorrectas;
    private MutableLiveData<Integer> intentos;
    private MutableLiveData<Boolean> reseteo;
    private TouchPlayButton touchPlayButton;
    private List<SoundEntity> listaDeOpciones;
    private OptionsAdapter optionsAdapter;

    // CONSTRUCTOR
    public EjercicioOpcionesViewModel() {
    }

    // INICIALIZAMOS LA VARIABLES
    public void onCreate() {
        puntuacionEntity = new PuntuacionEntity();
        conectoresEntity = new ConectoresEntity();
        combinacionConectores = new ArrayList<>();
        touchPlayButton = new TouchPlayButton();
    }

    // INICIALIZAMOS EL VIEWMODEL CON LOS DATOS DEL RECICLERVIEW
    public void onStart(List<SoundEntity> listaDeOpciones) {
        this.listaDeOpciones = listaDeOpciones;
        int cantidadDeOpciones;
        List<SoundEntity> newListaDeOpciones;
        SoundEntity opcionCorrecta;
        cantidadDeOpciones = getOptionsQuantity(listaDeOpciones, configuracionEntity.getEjercicio());
        newListaDeOpciones = getOptionsList(listaDeOpciones, cantidadDeOpciones);
        opcionCorrecta = setCorrectOption(newListaDeOpciones);
        opciones = new OptionAnswerEntity(newListaDeOpciones, opcionCorrecta);
        correctas.setValue(puntuacionEntity.getCorrectas());
        incorrectas.setValue(puntuacionEntity.getIncorrectas());
        optionsAdapter = new OptionsAdapter(getListaDeOpciones(), getOpcionCorrecta(), touchOptionButton);
        getRandomConectores();
    }

    public PuntuacionEntity getPuntuacionModel() {
        return puntuacionEntity;
    }

    // SETEAMOS LOS VALORES EN LA CONFIGURACION PASADOS POR BUNDLE
    public void setConfiguracion(Bundle bundle) {
        configuracionEntity = new ConfiguracionEntity(
                bundle.getString("categoria"),
                bundle.getString("subcategoria"),
                bundle.getString("ejercicio"), bundle.getBoolean("ruido"),
                bundle.getString("ruido"),
                bundle.getFloat("intensidad", .1f)
        );
    }

    // INICIAMOS LA BASE DE DATOS A TRAVES DEL REPOSITORY
    public void startDatabase(Application application) {
        soundRepository = new SoundRepository(application);
    }

    // SETEAMOS TODOS LOS CONECTORES
    public void setTodosLosConectores(Context context) {
        soundRepository.getHoyEsSound().observe((LifecycleOwner) context, sound -> {
            setConector("Hoy es", sound);
        });
        soundRepository.getEsHoySound().observe((LifecycleOwner) context, sound -> {
            setConector("Es hoy", sound);
        });
        soundRepository.getyLLoviendoSound().observe((LifecycleOwner) context, sound -> {
            setConector("Y esta lloviendo", sound);
        });
        soundRepository.getNecesitoSound().observe((LifecycleOwner) context, sound -> {
            setConector("Necesito", sound);
        });
        soundRepository.getVasosGrandes().observe((LifecycleOwner) context, sound -> {
            setConector("Vasos grandes", sound);
        });
    }

    // RETORNAMOS LA LISTA DE OPCIONES
    public List<SoundEntity> getListaDeOpciones() {
        return opciones.getOptionsList();
    }

    // RETORNAMOS LA OPCION CORRECTA
    public SoundEntity getOpcionCorrecta() {
        return opciones.getRespuestaCorrecta();
    }

    // RETORNA LA CANTIDAD DE OPCIONES QUE SE VAN A MOSTRAR DEPENDIENDO DEL TIPO DE EJERCICIO SELECCIONADO
    public int getOptionsQuantity(List<SoundEntity> list, String tipoDeEjercicio) {
        switch (tipoDeEjercicio) {
            case Constantes.J_DISCRIMINAR:
                return 2;
            case Constantes.J_IDENTIFICAR_TRES_OPCIONES:
                return 3;
            case Constantes.J_IDENTIFICAR_CINCO_OPCIONES:
                return 5;
            case Constantes.J_TODA_LA_CATEGORIA:
                return list.size();
            default:
                return 0;
        }
    }

    // MEZCLA LA LISTA DE SONIDOS, SELECCIONA UNA CIERTA CANTIDAD Y RETORNA LA NUEVA LISTA
    public List<SoundEntity> getOptionsList(List<SoundEntity> list, int optionsQuantity) {
        List<SoundEntity> newOptionsList = new ArrayList<>();
        Collections.shuffle(list);
        for (int i=0; i<optionsQuantity; i++) {
            newOptionsList.add(list.get(i));
        }
        return newOptionsList;
    }

    // SELECCIONA ALEATORIAMENTE UNA OPCION CORRECTA
    public SoundEntity setCorrectOption(List<SoundEntity> list) {
        Random random = new Random();
        int correctOption = random.nextInt(list.size());
        for (int i=0; i<list.size(); i++) {
            System.out.println(list.get(i).getNombre_sonido());
        }
        System.out.println("La opcion correcta es: " + list.get(correctOption).getNombre_sonido());
        return list.get(correctOption);
    }

    // SETEA UN CONECTOR
    public void setConector(String nombre, SoundEntity conector) {
        conectoresEntity.putConector(nombre, conector);
    }

    // RESPUESTAS CORRECTAS
    public MutableLiveData<Integer> getCorrectas() {
        if (correctas == null) {
            correctas = new MutableLiveData<>();
        }
        return correctas;
    }

    // RESPUESTAS INCORRECTAS
    public MutableLiveData<Integer> getIncorrectas() {
        if (incorrectas == null) {
            incorrectas = new MutableLiveData<>();
        }
        return incorrectas;
    }

    // RESETEOS
    public MutableLiveData<Boolean> getReseteo() {
        if (reseteo == null) {
            reseteo = new MutableLiveData<>();
        }
        return reseteo;
    }

    // OBTENEMOS LA LISTA DE SONIDOS DEPENDIENDO DE LA SUBCATEGORIA SELECCIONADA
    public LiveData<List<SoundEntity>> obtenerOpciones() {
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

    // OBTENEMOS LA RUTA DEL RUIDO SELECCIONADO
    public String getRutaRuido() {
        switch (configuracionEntity.getTipoRuido()) {
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

    // OBTENEMOS UN CONECTOR RANDOM
    public void getRandomConectores() {
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

    // CUANDO EL USUARIO TOCA EL BOTON DE PLAY
    public void onTouchPlayButton(Context context) {
        switch (configuracionEntity.getCategoria()) {
            case ("Palabra"):
                if (configuracionEntity.getTipoRuido() == null) {
                    touchPlayButton.reproducirSonido(context, opciones.getRespuestaCorrecta());
                } else {
                    touchPlayButton.reproducirSonidoConRuido(context, opciones.getRespuestaCorrecta(), getRutaRuido());
                }
                break;
            case ("Oraciones"):
                if (configuracionEntity.getTipoRuido() == null) {
                    touchPlayButton.reproducirOraciones(context, opciones.getRespuestaCorrecta(), combinacionConectores.get(0), combinacionConectores.get(1));
                } else {
                    touchPlayButton.reproducirOracionesConRuido(context, opciones.getRespuestaCorrecta(), combinacionConectores.get(0), combinacionConectores.get(1), getRutaRuido());
                }
                break;
            default:
                break;
        }
    }

    // CUANDO EL USUARIO TOCA EL BOTON DE UNA OPCION
    public TouchOptionButton touchOptionButton = new TouchOptionButton() {
        @Override
        public void corroborarSeleccion(MaterialButton opcionSeleccionada, SoundEntity opcionCorrecta) {
            if (opcionSeleccionada.getText() == opcionCorrecta.getNombre_sonido()) {
                puntuacionEntity.sumarCorrectas();
                puntuacionEntity.sumarIntentos();
                puntuacionEntity.resetear();
                correctas.setValue(puntuacionEntity.getCorrectas());
                reseteo.setValue(puntuacionEntity.getReseteo());
            } else if (opcionSeleccionada.getText() != opcionCorrecta.getNombre_sonido()) {
                puntuacionEntity.sumarIncorrectas();
                puntuacionEntity.sumarIntentos();
                incorrectas.setValue(puntuacionEntity.getIncorrectas());
            }
        }
    };

    // GET RECICLERVIEW ADAPTER
    public OptionsAdapter getOptionsAdapter() {
        return optionsAdapter;
    }

}
