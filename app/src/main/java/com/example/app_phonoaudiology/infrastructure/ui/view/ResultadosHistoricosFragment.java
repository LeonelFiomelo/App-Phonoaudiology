package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_phonoaudiology.application.adapters.ResultadosAdapter;
import com.example.app_phonoaudiology.databinding.FragmentResultadosHistoricosBinding;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.ResultadosHistoricosViewModel;

import java.util.List;

public class ResultadosHistoricosFragment extends Fragment {

    private FragmentResultadosHistoricosBinding binding;
    private ResultadosHistoricosViewModel viewModel;
    private NavController navController;
    private ResultadoRepository resultadoRepository;
    private List<ResultadoEntityDB> listaDeResultados;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultadosHistoricosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        resultadoRepository = new ResultadoRepository(requireActivity().getApplication());

        resultadoRepository.getResultados().observe((LifecycleOwner) requireContext(), resultados -> {
            System.out.println("CANTIDAD DE RESULTADOS: " + resultados.size());
            List<ResultadoEntityDB> lista = resultados;
            binding.rvResultados.setAdapter(
                    new ResultadosAdapter(lista)
            );
        });

        binding.tbResultadosHistorico.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

    }
}