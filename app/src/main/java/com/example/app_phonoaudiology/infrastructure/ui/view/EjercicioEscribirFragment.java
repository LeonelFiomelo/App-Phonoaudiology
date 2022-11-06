package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.utils.EjercicioUtils;
import com.example.app_phonoaudiology.databinding.FragmentEjercicioEscribirBinding;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.EjercicioEscribirViewModel;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.EjercicioOpcionesViewModel;

import java.util.List;
import java.util.Objects;

public class EjercicioEscribirFragment extends Fragment {

    private FragmentEjercicioEscribirBinding binding;
    private EjercicioEscribirViewModel viewModel;
    private Bundle reporteBundle;
    private List<SoundEntity> listaDeSonidos;

    private Toolbar toolbar;
    private TextView txtIncorrectas, txtCorrectas;
    private EditText editTxtRespuesta;
    private ImageButton btnConfiguracion, btnPlay;
    private Button btnAceptar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EjercicioEscribirViewModel.class);
        viewModel.onCreate(requireActivity().getApplication(), getContext(), getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEjercicioEscribirBinding.inflate(inflater, container, false);

        toolbar = binding.toolbarEjercicioEscribir;
        txtIncorrectas = binding.txtIncorrectasEjercicioEscribir;
        txtCorrectas = binding.txtCorrectasEjercicioEscribir;
        editTxtRespuesta = binding.editTxtRespuestaEjercicioEscribir;
        btnConfiguracion = binding.btnConfiguracionEjercicioEscribir;
        btnPlay = binding.btnPlayEjercicioEscribir;
        btnAceptar = binding.btnAceptarEjercicioEscribir;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        toolbar.setTitle(EjercicioUtils.getTitulo(viewModel.getConfiguracionEntity().getBotonSeleccionado()));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            // SE LE AGREGA UN LISTENER AL BOTON DE LA TOOLBAR PARA VOLVER AL FRAGMENT ANTERIOR
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        final Observer<Integer> correctObserver = new Observer<Integer>() {
            // SE LE COLOCA UN OBSERVER PARA ACTUALIZAR LA PUNTUACION DE LAS RESPUESTAS CORRECTAS
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtCorrectas.setText(String.valueOf(integer));
            }
        };
        viewModel.getCorrectas().observe(getViewLifecycleOwner(), correctObserver);

        final Observer<Integer> incorrectObserver = new Observer<Integer>() {
            // SE LE COLOCA UN OBSERVER PARA ACTUALIZAR LA PUNTUACION DE LAS RESPUESTAS INCORRECTAS
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtIncorrectas.setText(String.valueOf(integer));
            }
        };
        viewModel.getIncorrectas().observe(getViewLifecycleOwner(), incorrectObserver);

        final Observer<Boolean> reseteoObserver = new Observer<Boolean>() {
            // SE COLOCA UN OBSERVER A UNA VARIABLE PARA INDICAR CUANDO HAY QUE RESETEAR LA OPCION
            @Override
            public void onChanged(Boolean aBoolean) {
                viewModel.getOpcionesPorSubcategoria().observe(getViewLifecycleOwner(), sounds -> {
                    listaDeSonidos = sounds;
                    viewModel.onStart(listaDeSonidos);
                });
            }
        };
        viewModel.getReseteo().observe(getViewLifecycleOwner(), reseteoObserver);

        if (viewModel.getConfiguracionEntity().getBotonSeleccionado().equals("evaluacion")) {
            // SOLO SI EL BOTON SELECCIONADO ES EL DE EVALUACION.
            final Observer<Integer> intentosObserver = new Observer<Integer>() {
                // SE COLOCA UN OBSERVER A UNA VARIABLE PARA CONTROLAR LOS INTENTOS PERMITIDOS.
                // UNA VEZ ALCANZADOS LOS INTENTOS MAXIMOS GUARDA EL UUID DEL RESULTADO EN UN BUNDLE
                // Y NAVEGA AL SIGUIENTE FRAGMENT.
                @Override
                public void onChanged(Integer integer) {
                    if (viewModel.getChequearIntentosRestantes()) {
                        reporteBundle = new Bundle();
                        viewModel.onFinish(reporteBundle);
                        navController.navigate(R.id.ResultadoFragment, reporteBundle);
                    }
                }
            };
            viewModel.getIntentos().observe(getViewLifecycleOwner(), intentosObserver);
        }

        btnConfiguracion.setOnClickListener( v -> {
            // SE COLOCA UN LISTENER AL APRETAR EL BOTON DE CONFIGURACION
            viewModel.onTouchSettingsButton(requireView());
        });

        btnPlay.setOnClickListener( v -> {
            // SE COLOCA UN LISTENER AL APRETAR EL BOTON DE PLAY
            viewModel.onTouchPlayButton(requireContext());
        });

        btnAceptar.setOnClickListener( v -> {
            // SE COLOCA UN LISTENER AL APRETAR EL BOTON DE ACEPTAR
            if (viewModel.chequearCampos(editTxtRespuesta)) {
                viewModel.onTouchAceptarButton.guardarInformacionDelIntento(editTxtRespuesta);
                viewModel.onTouchAceptarButton.corroborarTexto(getContext(), editTxtRespuesta);
                editTxtRespuesta.setText("");
            } else {
                Toast.makeText(requireContext(), "Escriba una respuesta", Toast.LENGTH_SHORT).show();
            }
        });

    }
}