package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.databinding.FragmentResultadoBinding;

public class ResultadoFragment extends Fragment {

    private FragmentResultadoBinding binding;
    private NavController navController;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultadoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // INSTANCIAMOS LA NAVEGACION
        navController = Navigation.findNavController(view);

        // RELLENAMOS LOS TEXVIEW
        bundle = getArguments();
        binding.tvPuntuacionOutput.setText(bundle.getInt("puntuacion") + "/" + bundle.getInt("intentos"));
        binding.tvFechaOutput.setText(bundle.getString("fecha"));
        binding.tvEjercicioOutput.setText(bundle.getString("ejercicio"));
        binding.tvCategoriaOutput.setText(bundle.getString("categoria"));
        binding.tvRuidoOutput.setText(bundle.getString("ruido"));
        binding.tvIntensidadOutput.setText(String.valueOf(bundle.getFloat("intensidad")));

        // COLOCAMOS EL LISTENER AL BOTON DE FINALIZAR
        binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack(R.id.HomeFragment, false);
            }
        });

    }
}