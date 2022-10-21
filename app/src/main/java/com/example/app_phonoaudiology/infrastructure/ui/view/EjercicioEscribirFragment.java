package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private NavController navController;
    private Bundle reporteBundle;
    private List<SoundEntity> listaDeSonidos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EjercicioEscribirViewModel.class);
        viewModel.vmCreate(requireActivity().getApplication(), getContext(), getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEjercicioEscribirBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // INSTANCIAMOS LA NAVEGACION
        navController = Navigation.findNavController(view);

        // CONFIGURACION TOOLBAR
        binding.tbEjercicioEscribir.setTitle(EjercicioUtils.getTitulo(viewModel.getConfiguracionEntity().getBotonSeleccionado()));
        binding.tbEjercicioEscribir.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        // COLOCAMOS EL OBSERVER A LAS RESPUESTAS CORRECTAS
        final Observer<Integer> correctObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                binding.tvCorrectas.setText(String.valueOf(integer));
            }
        };
        viewModel.getCorrectas().observe(getViewLifecycleOwner(), correctObserver);

        // COLOCAMOS EL OBSERVER A LAS RESPUESTAS INCORRECTAS
        final Observer<Integer> incorrectObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                binding.tvIncorrectas.setText(String.valueOf(integer));
            }
        };
        viewModel.getIncorrectas().observe(getViewLifecycleOwner(), incorrectObserver);

        // COLOCAMOS EL OBSERVER PARA RESETEAR LA RESPUESTA CORRECTA
        final Observer<Boolean> reseteoObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                viewModel.getOpcionesPorSubcategoria().observe((LifecycleOwner) requireContext(), sounds -> {
                    listaDeSonidos = sounds;
                    viewModel.vmStart(listaDeSonidos);
                });
            }
        };
        viewModel.getReseteo().observe(getViewLifecycleOwner(), reseteoObserver);

        // COLOCAMOS EL OBSERVER A LOS INTENTOS SOLAMENTE SI EL BOTON SELECCIONADO ES EL DE EVALUACION
        if (viewModel.getConfiguracionEntity().getBotonSeleccionado().equals("evaluacion")) {
            final Observer<Integer> intentosObserver = new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    if (viewModel.getChequearIntentosRestantes()) {
                        reporteBundle = new Bundle();
                        viewModel.vmFinish(reporteBundle);
                        navController.navigate(R.id.ResultadoFragment, reporteBundle);
                    }
                }
            };
            viewModel.getIntentos().observe(getViewLifecycleOwner(), intentosObserver);
        }

        // COLOCAMOS EL LISTENER AL BOTTON SETTINGS
        binding.btnSetting.setOnClickListener( v -> {
            viewModel.onTouchSettingsButton(requireView());
        });

        // COLOCAMOS EL LISTENER AL BOTON PLAY
        binding.btnPlay.setOnClickListener( v -> {
            viewModel.onTouchPlayButton(requireContext());
        });

        // COLOCAMOS EL LISTENER AL BOTON ACEPTAR
        binding.btnAceptar.setOnClickListener( v -> {
            if (viewModel.chequearCampos(binding.etCompletar)) {
                viewModel.onTouchAceptarButton.corroborarTexto(binding.etCompletar);
            }
        });

    }
}