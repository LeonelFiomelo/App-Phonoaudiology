package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_phonoaudiology.application.controllers.SoundController;
import com.example.app_phonoaudiology.databinding.FragmentEjercicioOpcionesBinding;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.application.adapters.OptionsAdapter;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.EjercicioOpcionesViewModel;

import java.util.List;

public class EjercicioOpcionesFragment extends Fragment {

    private FragmentEjercicioOpcionesBinding binding;
    private EjercicioOpcionesViewModel viewModel;
    private Bundle bundle;
    private List<SoundEntity> listaDeOpciones;
    private SoundController soundController;
    private OptionsAdapter optionsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EjercicioOpcionesViewModel.class);
        // INICIALIZAMOS LAS VARIABLES
        viewModel.onCreate();
        // SETEAMOS LOS VALORES PASADOS POR BUNDLE EN LA CONFIGURACION
        bundle = getArguments();
        assert bundle != null;
        viewModel.setConfiguracion(bundle);
        // INICIALIZAMOS LA BASE DE DATOS A TRAVES DEL REPOSITORY
        viewModel.startDatabase(requireActivity().getApplication());
        // SETEAMOS LOS CONECTORES
        viewModel.setTodosLosConectores(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEjercicioOpcionesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // CONFIGURAMOS EL RECYCLERVIEW
        binding.recyclerViewEjercicio.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // ACTIVAMOS EL OBSERVER PARA QUE CARGE EL RECICLERVIEW
        viewModel.getReseteo().setValue(viewModel.getPuntuacionModel().getReseteo());

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

        // OBTENEMOS LA OPCIONES PARA EL RECICLERVIEW
        final Observer<Boolean> reseteoObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                viewModel.obtenerOpciones().observe((LifecycleOwner) requireContext(), sounds -> {
                    listaDeOpciones = sounds;
                    viewModel.onStart(listaDeOpciones);
                    binding.recyclerViewEjercicio.setAdapter(viewModel.getOptionsAdapter());
                });
            }
        };
        viewModel.getReseteo().observe(getViewLifecycleOwner(), reseteoObserver);

        // AGREGAMOS UN LISTENER AL BOTON PLAY
        binding.btnPlay.setOnClickListener( v -> {
            soundController = new SoundController();
            viewModel.onTouchPlayButton(requireContext());
        });

    }
}