package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.adapters.OptionsAdapter;
import com.example.app_phonoaudiology.application.usecases.TouchSettingsButton;
import com.example.app_phonoaudiology.application.utils.EjercicioUtils;
import com.example.app_phonoaudiology.application.utils.GeneralUtils;
import com.example.app_phonoaudiology.application.utils.EjercicioSoundsUtils;
import com.example.app_phonoaudiology.domain.entities.ReporteEntity;
import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.ErrorRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;
import com.example.app_phonoaudiology.domain.entities.ConectoresEntity;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;
import com.example.app_phonoaudiology.domain.entities.OpcionesEntity;
import com.example.app_phonoaudiology.domain.entities.PuntuacionEntity;
import com.example.app_phonoaudiology.application.usecases.TouchOptionButton;
import com.example.app_phonoaudiology.application.usecases.TouchPlayButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EjercicioOpcionesViewModel extends ViewModel {

    private MutableLiveData<Integer> correctas;
    private MutableLiveData<Integer> incorrectas;
    private MutableLiveData<Integer> intentos;
    private MutableLiveData<Boolean> reseteo;
    // REPOSITORIES
    private SoundRepository soundRepository;
    private ResultadoRepository resultadoRepository;
    private ErrorRepository errorRepository;
    // ENTITIES
    private ConfiguracionEntity configuracionEntity;
    private PuntuacionEntity puntuacionEntity;
    private ConectoresEntity conectoresEntity;
    private OpcionesEntity opcionesEntity;
    private ErrorEntityDB errorEntityDB;
    private ReporteEntity reporteEntity;
    // USECASES
    private TouchPlayButton touchPlayButton;
    private TouchSettingsButton touchSettingsButton;
    // ADAPTERS
    private OptionsAdapter opcionesAdapter;
    // OTHERS
    private ArrayList<SoundEntity> combinacionConectores;
    private int intento;
    private int errorPermitido;
    @SuppressLint("StaticFieldLeak")
    private Context contextVM;

    public EjercicioOpcionesViewModel() {
    }

    public SoundRepository getSoundRepository() {
        return soundRepository;
    }

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
        contextVM = context;
        setTodosLosConectores(context);
        getIntentos().setValue(getPuntuacionEntity().getIntentos());
        getReseteo().setValue(getPuntuacionEntity().getReseteo());
    }

    public void onStart(List<SoundEntity> listaDeOpcionesCompleta) {
        opcionesEntity = new OpcionesEntity(listaDeOpcionesCompleta, configuracionEntity.getEjercicio());
        opcionesAdapter = new OptionsAdapter(opcionesEntity.getListaDeOpciones(), opcionesEntity.getRespuestaCorrecta(), touchOptionButton);
        intento += 1;
        errorPermitido = 1;
        errorEntityDB = new ErrorEntityDB();
        if (configuracionEntity.getCategoria().equals("Oraciones")){
            // SETEA LOS CONECTORES SOLAMENTE SI LA CATEGORIA ES ORACIONES
            setConectores();
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

    private ConfiguracionEntity getConfiguracionEntity() {
        return configuracionEntity;
    }
    public PuntuacionEntity getPuntuacionEntity() {
        return puntuacionEntity;
    }
    public OptionsAdapter getOpcionesAdapter() {
        return opcionesAdapter;
    }
    public Boolean getChequearIntentosRestantes() {
        return EjercicioUtils.getChequearIntentosRestantes(puntuacionEntity);
    }
    public LiveData<List<SoundEntity>> getOpcionesPorSubcategoria() {
        return EjercicioUtils.getOpcionesPorSubcategoria(configuracionEntity, soundRepository);
    }

    public void setInformacionReporteBundle(Bundle bundle) {
        reporteEntity = new ReporteEntity(puntuacionEntity, configuracionEntity);
        reporteEntity.setReporteBundle(bundle);
    }
    public void setTodosLosConectores(Context context) {
        EjercicioUtils.setTodosLosConectores(context, soundRepository, conectoresEntity);
    }
    public void setConectores() {
        EjercicioUtils.setConectores(configuracionEntity, conectoresEntity, combinacionConectores);
    }

    public String getTitulo() {
        return EjercicioUtils.getTitulo(configuracionEntity.getBotonSeleccionado());
    }

    public Boolean checkEvaluacion() {
        return configuracionEntity.getBotonSeleccionado().equals("evaluacion");
    }

    // CUANDO EL USUARIO TOCA EL BOTON DE PLAY
    public void onTouchPlayButton(Context context) {
        switch (configuracionEntity.getCategoria()) {
            case ("Palabra"):
                if (configuracionEntity.getTipoRuido() == null) {
                    touchPlayButton.reproducirSonido(context, opcionesEntity.getRespuestaCorrecta());
                } else {
                    touchPlayButton.reproducirSonidoConRuido(context, opcionesEntity.getRespuestaCorrecta(), EjercicioUtils.getRutaRuido(configuracionEntity.getTipoRuido()), configuracionEntity.getIntensidad());
                }
                break;
            case ("Oraciones"):
                if (configuracionEntity.getTipoRuido() == null) {
                    touchPlayButton.reproducirOraciones(context, opcionesEntity.getRespuestaCorrecta(), combinacionConectores.get(0), combinacionConectores.get(1));
                } else {
                    touchPlayButton.reproducirOracionesConRuido(context, opcionesEntity.getRespuestaCorrecta(), combinacionConectores.get(0), combinacionConectores.get(1), EjercicioUtils.getRutaRuido(configuracionEntity.getTipoRuido()), configuracionEntity.getIntensidad());
                }
                break;
            default:
                break;
        }
    }

    // CUANDO EL USUARIO TOCA EL BOTON DE UNA OPCION
    public TouchOptionButton touchOptionButton = new TouchOptionButton() {

        @Override
        public void guardarInformacionDeSeleccion(MaterialButton opcionSeleccionada, SoundEntity opcionCorrecta) {
            switch (errorPermitido) {
                case (1):
                    errorEntityDB.setEstimulo(opcionCorrecta.getNombre_sonido());
                    errorEntityDB.setPrimeraRespuesta((String) opcionSeleccionada.getText());
                    errorEntityDB.setSegundaRespuesta(null);
                    errorPermitido += 1;
                    break;
                case (2):
                    errorEntityDB.setSegundaRespuesta((String) opcionSeleccionada.getText());
                    break;
            }
        }

        @Override
        public void corroborarSeleccion(MaterialButton opcionSeleccionada, SoundEntity opcionCorrecta) {

            if (opcionSeleccionada.getText() == opcionCorrecta.getNombre_sonido()) {

                opcionSeleccionada.startAnimation(AnimationUtils.loadAnimation(opcionSeleccionada.getContext(), R.anim.anim_correct_answer_opciones));
                EjercicioSoundsUtils.announceAnswerSound(opcionSeleccionada.getContext(), true);

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

                opcionSeleccionada.startAnimation(AnimationUtils.loadAnimation(opcionSeleccionada.getContext(), R.anim.anim_wrong_answer));
                EjercicioSoundsUtils.announceAnswerSound(opcionSeleccionada.getContext(), false);

                if (puntuacionEntity.getIntentos() == 1 && !puntuacionEntity.getIntentoExtra()) {
                    puntuacionEntity.sumarIntentos();
                    puntuacionEntity.cambiarCantidadIntentos(11);
                    puntuacionEntity.activarIntentoExtra();
                }

                puntuacionEntity.sumarIncorrectas();
                puntuacionEntity.restarIntentos();
                puntuacionEntity.restarErroresPermitidos();

                incorrectas.setValue(puntuacionEntity.getIncorrectas());

                if (puntuacionEntity.getErroresPermitidos() == 0 || getChequearIntentosRestantes()) {
                    // GUARDA EL ERROR CUANDO SE LE TERMINARON LOS ERRORES PERMITIDOS O CUANDO SE
                    // SE TERMINARION LOS INTENTOS GENERALES PERMITIDOS
                    puntuacionEntity.guardarError(errorEntityDB);
                }

                if (puntuacionEntity.getErroresPermitidos() == 0 ) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(contextVM);
                    builder.setView(R.layout.alertdialog_error);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setOnShowListener( alertDialogShow -> {
                        TextView txt_respuesta = alertDialog.findViewById(R.id.txt_respuesta_alertdialogError);
                        Button btn_aceptar = alertDialog.findViewById(R.id.btn_aceptar_alertdialogError);
                        txt_respuesta.setText(opcionesEntity.getRespuestaCorrecta().getNombre_sonido());
                        btn_aceptar.setOnClickListener(v -> {
                            dialogReset();
                            alertDialog.cancel();
                        });
                    } );
                    alertDialog.setOnCancelListener( clickCancelarDialogo -> {
                        dialogReset();
                    });
                    alertDialog.show();

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

    // RESETEA LAS OPCIONES CUANDO SE EQUIVOCA 2 VECES
    public void dialogReset() {
        puntuacionEntity.resetear();
        reseteo.setValue(puntuacionEntity.getReseteo());
        intentos.setValue(puntuacionEntity.getIntentos());
    }

}
