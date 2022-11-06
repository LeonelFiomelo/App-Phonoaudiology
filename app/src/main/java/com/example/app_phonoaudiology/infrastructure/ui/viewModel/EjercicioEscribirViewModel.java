package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.usecases.TouchAceptarButton;
import com.example.app_phonoaudiology.application.usecases.TouchPlayButton;
import com.example.app_phonoaudiology.application.usecases.TouchSettingsButton;
import com.example.app_phonoaudiology.application.utils.EjercicioUtils;
import com.example.app_phonoaudiology.application.utils.GeneralUtils;
import com.example.app_phonoaudiology.application.utils.EjercicioSoundsUtils;
import com.example.app_phonoaudiology.domain.entities.ConectoresEntity;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;
import com.example.app_phonoaudiology.domain.entities.PuntuacionEntity;
import com.example.app_phonoaudiology.domain.entities.ReporteEntity;
import com.example.app_phonoaudiology.domain.entities.OpcionEntity;
import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.ErrorRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EjercicioEscribirViewModel extends ViewModel {

    private MutableLiveData<Integer> correctas;
    private MutableLiveData<Integer> incorrectas;
    private MutableLiveData<Boolean> reseteo;
    private MutableLiveData<Integer> intentos;
    // REPOSITORIES
    private SoundRepository soundRepository;
    private ResultadoRepository resultadoRepository;
    private ErrorRepository errorRepository;
    // ENTITIES
    private ConfiguracionEntity configuracionEntity;
    private PuntuacionEntity puntuacionEntity;
    private ConectoresEntity conectoresEntity;
    private OpcionEntity opcionEntity;
    private ErrorEntityDB errorEntityDB;
    private ReporteEntity reporteEntity;
    // USECASES
    private TouchPlayButton touchPlayButton;
    private TouchSettingsButton touchSettingsButton;
    // OTHERS
    private ArrayList<SoundEntity> combinacionConectores;
    private int intento;
    private int errorPermitido;

    public void onCreate(Application application, Context context, Bundle bundle) {
        configuracionEntity = new ConfiguracionEntity();
        configuracionEntity.setConfiguracion(bundle);
        puntuacionEntity = new PuntuacionEntity(10, 2);
        conectoresEntity = new ConectoresEntity();
        combinacionConectores = new ArrayList<>();
        touchPlayButton = new TouchPlayButton();
        touchSettingsButton = new TouchSettingsButton();
        soundRepository = new SoundRepository(application);
        resultadoRepository = new ResultadoRepository(application);
        errorRepository = new ErrorRepository(application);
        intento = -1;
        setTodosLosConectores(context);
        getIntentos().setValue(getPuntuacionEntity().getIntentos());
        getReseteo().setValue(getPuntuacionEntity().getReseteo());
    }

    public void onStart(List<SoundEntity> listaDeSonidos) {
        opcionEntity = new OpcionEntity(listaDeSonidos);
        intento += 1;
        errorPermitido = 1;
        errorEntityDB = new ErrorEntityDB();
        if (configuracionEntity.getCategoria().equals("Oraciones")) {
            // SETEA LOS CONECTORES SOLAMENTE SI LA CATEGORIA ES ORACIONES
            setRandomConectores();
        }
    }

    public void onFinish(Bundle reporteBundle) {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        ResultadoEntityDB resultadoEntityDB = new ResultadoEntityDB(
                uuid,
                GeneralUtils.getFechaFormateada(),
                puntuacionEntity.getCorrectas(),
                puntuacionEntity.get_Intentos(),
                configuracionEntity.getCategoria(),
                configuracionEntity.getSubcategoria(),
                configuracionEntity.getEjercicio(),
                configuracionEntity.getPalabraClave(),
                configuracionEntity.getRuido(),
                configuracionEntity.getTipoRuido(),
                configuracionEntity.getIntensidad()
        );

        for (int i=0; i<puntuacionEntity.getListaDeErrores().size(); i++) {
            puntuacionEntity.getListaDeErrores().get(i).setUuidResultado(uuid);
        }

        resultadoRepository.agregarResultado(resultadoEntityDB);

        errorRepository.agregarErrores(puntuacionEntity.getListaDeErrores());

        reporteBundle.putString("uuid", uuid);

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
        EjercicioUtils.setConectores(configuracionEntity, conectoresEntity, combinacionConectores);
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
        public void guardarInformacionDelIntento(EditText editText) {
            switch (errorPermitido) {
                case (1):
                    errorEntityDB.setEstimulo(opcionEntity.getRespuestaCorrecta().getNombre_sonido());
                    errorEntityDB.setPrimeraRespuesta(editText.getText().toString());
                    errorEntityDB.setSegundaRespuesta(null);
                    errorPermitido += 1;
                    break;
                case (2):
                    errorEntityDB.setSegundaRespuesta(editText.getText().toString());
                    break;
            }
        }

        @Override
        public void corroborarTexto(Context context, EditText editText) {
            if (GeneralUtils.getCompararStrings(editText.getText().toString(), opcionEntity.getRespuestaCorrecta().getNombre_sonido()) == 0) {

                editText.startAnimation(AnimationUtils.loadAnimation(editText.getContext(), R.anim.anim_correct_answer_escribir));
                EjercicioSoundsUtils.announceAnswerSound(editText.getContext(), true);

                puntuacionEntity.sumarCorrectas();
                puntuacionEntity.restarIntentos();
                puntuacionEntity.guardarError(errorEntityDB);
                puntuacionEntity.resetear();

                correctas.setValue(puntuacionEntity.getCorrectas());
                intentos.setValue(puntuacionEntity.getIntentos());

                new Handler().postDelayed(new Runnable(){
                    public void run(){
                        //----------------------------
                        reseteo.setValue(puntuacionEntity.getReseteo());
                        //----------------------------
                    }
                }, 600);

            } else {

                editText.startAnimation(AnimationUtils.loadAnimation(editText.getContext(), R.anim.anim_wrong_answer));
                EjercicioSoundsUtils.announceAnswerSound(editText.getContext(), false);

                puntuacionEntity.sumarIncorrectas();
                puntuacionEntity.restarIntentos();
                puntuacionEntity.restarErroresPermitidos();

                incorrectas.setValue(puntuacionEntity.getIncorrectas());

                if (puntuacionEntity.getErroresPermitidos() == 0 || getChequearIntentosRestantes()) {
                    // GUARDA EL ERROR CUANDO SE LE TERMINARON LOS ERRORES PERMITIDOS O CUANDO SE
                    // SE TERMINARION LOS INTENTOS GENERALES PERMITIDOS
                    puntuacionEntity.guardarError(errorEntityDB);
                }

                if (puntuacionEntity.getErroresPermitidos() == 0) {
                    MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(context)
                            .setTitle("¡Te has equivocado 2 veces!")
                            .setMessage("La respuesta correcta era: " + opcionEntity.getRespuestaCorrecta().getNombre_sonido())
                            .setPositiveButton("Continuar", (dialog, which) -> {
                                puntuacionEntity.resetear();
                                reseteo.setValue(puntuacionEntity.getReseteo());
                                intentos.setValue(puntuacionEntity.getIntentos());
                            });
                    alert.show();
                } else {
                    intentos.setValue(puntuacionEntity.getIntentos());
                }

            }
        }
    };

    // CUANDO EL USUARIO TOCA EL BOTON DE SETTINGS
    public void onTouchSettingsButton(@NonNull View view) {
        touchSettingsButton.showSettings(view, configuracionEntity);
    }

}
