package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.adapters.ResultadosAdapter;
import com.example.app_phonoaudiology.databinding.FragmentResultadosHistoricosBinding;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.ResultadosHistoricosViewModel;

import java.util.List;
import java.util.Objects;

public class ResultadosHistoricosFragment extends Fragment {

    private FragmentResultadosHistoricosBinding binding;
    private ResultadosHistoricosViewModel viewModel;

    private Toolbar toolbar;
    private RecyclerView recyclerViewResultados;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ResultadosHistoricosViewModel.class);
        viewModel.onCreate(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultadosHistoricosBinding.inflate(inflater, container, false);

        toolbar = binding.toolbarResultadosHistorico;
        recyclerViewResultados = binding.recyclerViewResultadosHistoricos;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        viewModel.getResultado().observe(getViewLifecycleOwner(), resultados -> {
            // SE OBTIENEN TODOS LOS RESULTADOS DE LA BASE DE DATOS
            viewModel.onStart(resultados, navController);
            viewModel.setSwipe(getActivity(), getView(), recyclerViewResultados, resultados);
            recyclerViewResultados.setAdapter(viewModel.getResultadosAdapter());
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            // SE COLOCA UN LISTENER AL BOTON DE LA TOOLBAR PARA VOLVER AL FRAGMENT ANTERIOR
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

    }
}