package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.application.usecases.TouchAceptarButton;
import com.example.app_phonoaudiology.application.usecases.TouchPlayButton;
import com.example.app_phonoaudiology.application.usecases.TouchSettingsButton;
import com.example.app_phonoaudiology.application.utils.EjercicioUtils;
import com.example.app_phonoaudiology.application.utils.ErrorDeIntentosAlert;
import com.example.app_phonoaudiology.application.utils.GeneralUtils;
import com.example.app_phonoaudiology.application.utils.EjercicioSoundsUtils;
import com.example.app_phonoaudiology.domain.entities.ConectoresEntity;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;
import com.example.app_phonoaudiology.domain.entities.ErrorEntity;
import com.example.app_phonoaudiology.domain.entities.PuntuacionEntity;
import com.example.app_phonoaudiology.domain.entities.ReporteEntity;
import com.example.app_phonoaudiology.domain.entities.OpcionEntity;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;

import java.util.ArrayList;
import java.util.List;

public class EjercicioEscribirViewModel extends ViewModel {

    private MutableLiveData<Integer> correctas;
    private MutableLiveData<Integer> incorrectas;
    private MutableLiveData<Boolean> reseteo;
    private MutableLiveData<Integer> intentos;
    // REPOSITORIES
    private SoundRepository soundRepository;
    private ResultadoRepository resultadoRepository;
    // ENTITIES
    private ConfiguracionEntity configuracionEntity;
    private PuntuacionEntity puntuacionEntity;
    private ConectoresEntity conectoresEntity;
    private OpcionEntity opcionEntity;
    private ErrorEntity errorEntity;
    private ReporteEntity reporteEntity;
    // USECASES
    private TouchPlayButton touchPlayButton;
    private TouchSettingsButton touchSettingsButton;
    // OTHERS
    private ArrayList<SoundEntity> combinacionConectores;
    private int intento;
    private int errorPermitido;

    public void vmCreate(Application application, Context context, Bundle bundle) {
        configuracionEntity = new ConfiguracionEntity(bundle);
        puntuacionEntity = new PuntuacionEntity(10, 2);
        conectoresEntity = new ConectoresEntity();
        combinacionConectores = new ArrayList<>();
        touchPlayButton = new TouchPlayButton();
        touchSettingsButton = new TouchSettingsButton();
        soundRepository = new SoundRepository(application);
        resultadoRepository = new ResultadoRepository(application);
        intento = -1;
        setTodosLosConectores(context);
        getIntentos().setValue(getPuntuacionEntity().getIntentos());
        getReseteo().setValue(getPuntuacionEntity().getReseteo());
    }

    public void vmStart(List<SoundEntity> listaDeSonidos) {
        opcionEntity = new OpcionEntity(listaDeSonidos);
        intento += 1;
        errorPermitido = 1;
        errorEntity = new ErrorEntity();
        setRandomConectores();
    }

    public void vmFinish(Bundle reporteBundle) {
        ResultadoEntityDB resultadoEntityDB = new ResultadoEntityDB(
                GeneralUtils.getFechaFormateada(),
                puntuacionEntity.getCorrectas(),
                puntuacionEntity.getIntentos(),
                configuracionEntity.getCategoria(),
                configuracionEntity.getSubcategoria(),
                configuracionEntity.getEjercicio(),
                configuracionEntity.getRuido(),
                configuracionEntity.getTipoRuido(),
                configuracionEntity.getIntensidad()
        );
//        resultadoRepository.agregarResultado(resultadoEntityDB);
        setInformacionReporteBundle(reporteBundle);
    }

    public MutableLiveData<Integer> getCorrectas() {
        if (correctas == null) {
            correctas = new MutableLiveData<>();
        }
        return correctas;
    }
    public MutableLiveData<Integer> getIncorrectas() {
        if (incorrectas == null) {
            incorrectas = new MutableLiveData<>();
        }
        return incorrectas;
    }
    public MutableLiveData<Integer> getIntentos() {
        if (intentos == null) {
            intentos = new MutableLiveData<>();
        }
        return intentos;
    }
    public MutableLiveData<Boolean> getReseteo() {
        if (reseteo == null) {
            reseteo = new MutableLiveData<>();
        }
        return reseteo;
    }

    public ConfiguracionEntity getConfiguracionEntity() {
        return configuracionEntity;
    }
    public PuntuacionEntity getPuntuacionEntity() {
        return puntuacionEntity;
    }
    public Boolean getChequearIntentosRestantes() {
        return EjercicioUtils.getChequearIntentosRestantes(puntuacionEntity);
    }
    public LiveData<List<SoundEntity>> getOpcionesPorSubcategoria() {
        return EjercicioUtils.getOpcionesPorSubcategoria(configuracionEntity, soundRepository);
    }

    public void setInformacionReporteBundle(Bundle bundle) {
//        reporteEntity = new ReporteEntity(puntuacionEntity, configuracionEntity);
        reporteEntity.setReporteBundle(bundle);
    }
    public void setTodosLosConectores(Context context) {
        EjercicioUtils.setTodosLosConectores(context, soundRepository, conectoresEntity);
    }
    public void setRandomConectores() {
        EjercicioUtils.setRandomConectores(configuracionEntity, conectoresEntity, combinacionConectores);
    }
    public Boolean chequearCampos(EditText editText) {
        if (editText.getEditableText().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // CUANDO EL USUARIO TOCA EL BOTON DE PLAY
    public void onTouchPlayButton (Context context) {
        switch (configuracionEntity.getCategoria()) {
            case ("Palabra"):
                if (configuracionEntity.getTipoRuido() == null) {
                    touchPlayButton.reproducirSonido(context, opcionEntity.getRespuestaCorrecta());
                } else {
                    touchPlayButton.reproducirSonidoConRuido(context, opcionEntity.getRespuestaCorrecta(), EjercicioUtils.getRutaRuido(configuracionEntity.getTipoRuido()), configuracionEntity.getIntensidad());
                }
                break;
            case ("Oraciones"):
                if (configuracionEntity.getTipoRuido() == null) {
                    touchPlayButton.reproducirOraciones(context, opcionEntity.getRespuestaCorrecta(), combinacionConectores.get(0), combinacionConectores.get(1));
                } else {
                    touchPlayButton.reproducirOracionesConRuido(context, opcionEntity.getRespuestaCorrecta(), combinacionConectores.get(0), combinacionConectores.get(1), EjercicioUtils.getRutaRuido(configuracionEntity.getTipoRuido()), configuracionEntity.getIntensidad());
                }
                break;
            default:
                break;
        }
    }

    // CUANDO EL USUARIO TOCA EL BOTON DE ACEPTAR
    public TouchAceptarButton onTouchAceptarButton = new TouchAceptarButton() {

        @Override
        public void guardarInformacionDelIntento(EditText editText, SoundEntity opcionCorrecta) {
            switch (errorPermitido) {
                case (1):
//                    errorEntity.setEstimulo(opcionCorrecta.getNombre_sonido());
//                    errorEntity.setPrimeraRespuesta((String) editText.getText().toString());
//                    errorEntity.setSegundaRespuesta(null);
//                    errorPermitido += 1;
                    break;
                case (2):
//                    errorEntity.setSegundaRespuesta((String) editText.getText().toString());
                    break;
            }
        }

        @Override
        public void corroborarTexto(EditText editText) {
            if (GeneralUtils.getCompararStrings(editText.getText().toString(), opcionEntity.getRespuestaCorrecta().getNombre_sonido()) == 0) {
                puntuacionEntity.sumarCorrectas();
                puntuacionEntity.restarIntentos();
//                puntuacionEntity.resetear(errorEntity);
                correctas.setValue(puntuacionEntity.getCorrectas());
                intentos.setValue(puntuacionEntity.getIntentos());
                reseteo.setValue(puntuacionEntity.getReseteo());
                EjercicioSoundsUtils.announceAnswerSound(editText.getContext(), true);
            } else {
                puntuacionEntity.sumarIncorrectas();
                puntuacionEntity.restarIntentos();
                puntuacionEntity.restarErroresPermitidos();
                incorrectas.setValue(puntuacionEntity.getIncorrectas());
                intentos.setValue(puntuacionEntity.getIntentos());
                EjercicioSoundsUtils.announceAnswerSound(editText.getContext(), false);
                if (puntuacionEntity.getErroresPermitidos() <= 0) {
                    EjercicioSoundsUtils.announceAnswerSound(editText.getContext(), false);
                    ErrorDeIntentosAlert errorDeIntentosAlert = new ErrorDeIntentosAlert(
                            "¡Te has equivocado más de " + 2 + " veces!",
                            "La respuesta correcta era: " + opcionEntity.getRespuestaCorrecta().getNombre_sonido(),
                            "Continuar"
                    );
                    errorDeIntentosAlert.MostrarAlerta(editText.getContext());
//                    puntuacionEntity.resetear(errorEntity);
                    reseteo.setValue(puntuacionEntity.getReseteo());
                }
            }
        }
    };

    // CUANDO EL USUARIO TOCA EL BOTON DE SETTINGS
    public void onTouchSettingsButton(@NonNull View view) {
        touchSettingsButton.showSettings(view, configuracionEntity);
    }

}