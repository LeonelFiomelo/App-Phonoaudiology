package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.databinding.*;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.ConfiguracionViewModel;

public class ConfiguracionFragment extends Fragment {

    private FragmentConfiguracionBinding binding;
    private ConfiguracionViewModel viewModel;
    private NavController navController;
    private Bundle bundle;

    private String categoriaSeleccionada, subcategoriaSeleccionada, ejercicioSeleccionado, ruidoSeleccionado;
    private boolean ruido;
    private float intensidad;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConfiguracionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // INTANCIAS
        viewModel = new ViewModelProvider(this).get(ConfiguracionViewModel.class);
        navController = Navigation.findNavController(view);

        // SPINNERS
        binding.spinnerCategoria.setAdapter(viewModel.getAdapterCategoria(getContext()));
        binding.spinnerSubcategoria.setAdapter(viewModel.getAdapterSubcategoriaPalabras(getContext()));
        binding.spinnerEjercicio.setAdapter(viewModel.getAdapterEjercicio(getContext()));
        binding.spinnerRuido.setAdapter(viewModel.getAdapterRuido(getContext()));

        // SELECCIONAMOS EL SPINNER CATEGORIA
        binding.spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i != 0) {
                    binding.spinnerSubcategoria.setEnabled(true);
                    categoriaSeleccionada = adapterView.getItemAtPosition(i).toString();
                    if (categoriaSeleccionada.equals("Oraciones")) {
                        binding.spinnerSubcategoria.setAdapter(viewModel.getAdapterSubcategoriaOraciones(getContext()));
                    }
                } else {
                    binding.spinnerSubcategoria.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // SELECCIONAMOS EL SPINNER SUBCATEGORIA
        binding.spinnerSubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i != 0) {
                    binding.spinnerEjercicio.setEnabled(true);
                    subcategoriaSeleccionada = adapterView.getItemAtPosition(i).toString();
                } else {
                    binding.spinnerEjercicio.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // SELECCIONAMOS EL SPINNER EJERCICIO
        binding.spinnerEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i != 0) {
                    binding.switchRuido.setEnabled(true);
                    ejercicioSeleccionado = adapterView.getItemAtPosition(i).toString();
                } else {
                    binding.switchRuido.setChecked(false);
                    binding.switchRuido.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // SELECCIONAMOS EL SWITCH DE RUIDO
        binding.switchRuido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ruido = true;
                    binding.spinnerRuido.setVisibility(View.VISIBLE);
                    binding.tvIntensidadDelRuido.setVisibility(View.VISIBLE);
                    binding.seekBarRuido.setVisibility(View.VISIBLE);
                    binding.scrollViewConfiguracion.post(() -> binding.scrollViewConfiguracion.fullScroll(View.FOCUS_DOWN));
                } else {
                    ruido = false;
                    binding.spinnerRuido.setVisibility(View.GONE);
                    binding.tvIntensidadDelRuido.setVisibility(View.GONE);
                    binding.seekBarRuido.setVisibility(View.GONE);
                    binding.scrollViewConfiguracion.post(() -> binding.scrollViewConfiguracion.fullScroll(View.FOCUS_UP));
                }
            }
        });

        // SELECCIONAMOS EL SPINNER RUIDO
        binding.spinnerRuido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                ruidoSeleccionado = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // MODIFICAMOS INTENSIDAD DE LA SEEKBAR
        binding.seekBarRuido.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intensidad = (float) 0.1 * progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // APRETAMOS EL BOTON COMENZAR EJERCICIO
        binding.btnComenzarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.validate(getContext(), binding.spinnerCategoria, binding.spinnerSubcategoria, binding.spinnerEjercicio)) {
                    bundle = new Bundle();
                    bundle.putString("categoria", categoriaSeleccionada);
                    bundle.putString("subcategoria", subcategoriaSeleccionada);
                    bundle.putString("ejercicio", ejercicioSeleccionado);
                    bundle.putString("ruido", ruidoSeleccionado);
                    navController.navigate(R.id.EjercicioOpcionesFragment, bundle);
                }
            }
        });

    }

}