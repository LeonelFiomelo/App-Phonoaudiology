package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.utils.EjercicioUtils;
import com.example.app_phonoaudiology.application.utils.EjercicioAlertsUtils;
import com.example.app_phonoaudiology.databinding.FragmentEjercicioOpcionesBinding;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.EjercicioOpcionesViewModel;

import java.util.List;
import java.util.Objects;

public class EjercicioOpcionesFragment extends Fragment {

    private FragmentEjercicioOpcionesBinding binding;
    private EjercicioOpcionesViewModel viewModel;
    private Bundle reporteBundle;

    private Toolbar toolbar;
    private TextView txtIncorrectas, txtCorrectas;
    private ImageButton btnConfiguracion, btnPlay;
    private RecyclerView recyclerViewOpciones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EjercicioOpcionesViewModel.class);
        viewModel.onCreate(requireActivity().getApplication(), getContext(), getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEjercicioOpcionesBinding.inflate(inflater, container, false);

        toolbar = binding.toolbarEjercicioOpciones;
        txtIncorrectas = binding.txtIncorrectasEjercicioOpciones;
        txtCorrectas = binding.txtCorrectasEjercicioOpciones;
        btnConfiguracion = binding.btnConfiguracionEjercicioOpciones;
        btnPlay = binding.btnPlayEjercicioOpciones;
        recyclerViewOpciones = binding.recyclerViewEjercicioOpciones;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        toolbar.setTitle(viewModel.getTitulo());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            // SE COLOCA UN LISTENER AL BOTON DE LA TOOLBAR PARA VOLVER AL FRAGMENT ANTERIOR
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        final Observer<Integer> correctObserver = new Observer<Integer>() {
            // SE COLOCA UN OBSERVER PARA ACTUALIZAR LA PUNTUACION DE LAS OPCIONES CORRECTAS
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtCorrectas.setText(String.valueOf(integer));
            }
        };
        viewModel.getCorrectas().observe(getViewLifecycleOwner(), correctObserver);

        final Observer<Integer> incorrectObserver = new Observer<Integer>() {
            // SE COLOCA UN OBSERVER PARA ACTUALIZAR LA PUNTUACION DE LAS OPCIONES INCORRECTAS
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtIncorrectas.setText(String.valueOf(integer));
            }
        };
        viewModel.getIncorrectas().observe(getViewLifecycleOwner(), incorrectObserver);

        final Observer<Boolean> reseteoObserver = new Observer<Boolean>() {
            // SE COLOCA UN OBSERVER A UNA VARIABLE PARA INDICAR CUANDO HAY QUE RESETEAR LAS OPCIONES
            @Override
            public void onChanged(Boolean aBoolean) {
                viewModel.getOpcionesPorSubcategoria().observe(getViewLifecycleOwner(), sounds -> {
                    viewModel.onStart(sounds);
                    recyclerViewOpciones.setAdapter(viewModel.getOpcionesAdapter());
                });
            }
        };
        viewModel.getReseteo().observe(getViewLifecycleOwner(), reseteoObserver);

        if (viewModel.checkEvaluacion()) {
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

        btnPlay.setOnClickListener( v -> {
            // SE LE COLOCA UN LISTENER AL APRETAR EL BOTON DE PLAY
            viewModel.onTouchPlayButton(requireContext());
        });

        btnConfiguracion.setOnClickListener( v -> {
            // SE LE COLOCA UN LISTENER AL APRETAR EL BOTON DE CONFIGURACION
            viewModel.onTouchSettingsButton(requireView());
        });

    }
}