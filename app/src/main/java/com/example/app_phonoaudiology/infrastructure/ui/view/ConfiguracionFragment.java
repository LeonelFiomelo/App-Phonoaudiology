package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
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
import android.widget.Spinner;
import android.widget.Switch;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.databinding.*;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.ConfiguracionViewModel;

import java.util.Objects;

public class ConfiguracionFragment extends Fragment {

    private FragmentConfiguracionBinding binding;
    private ConfiguracionViewModel viewModel;
    private NavController navController;
    private Bundle bundle;

    private String botonSeleccionado, categoriaSeleccionada, subcategoriaSeleccionada, ejercicioSeleccionado, ruidoSeleccionado;
    private boolean ruido;
    private float intensidad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ConfiguracionViewModel.class);
        bundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConfiguracionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle.clear();

        Spinner sSubcategoria = binding.spinnerSubcategoria;
        Spinner sEjercicio = binding.spinnerEjercicio;
        SwitchCompat swRuido = binding.switchRuido;

        // INTANCIAS
        botonSeleccionado = getArguments().getString("botonSeleccionado");
        navController = Navigation.findNavController(view);

        // SPINNERS
        binding.spinnerCategoria.setAdapter(viewModel.getAdapterCategoria(getContext()));
        binding.spinnerSubcategoria.setAdapter(viewModel.getAdapterSubcategoriaPalabras(getContext()));
        binding.spinnerEjercicio.setAdapter(viewModel.getAdapterEjercicio(getContext()));
        binding.spinnerRuido.setAdapter(viewModel.getAdapterRuido(getContext()));

        // CONFIGURACION TOOLBAR
        binding.tbConfiguracion.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        // SELECCIONAMOS EL SPINNER CATEGORIA
        binding.spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                switch (adapterView.getItemAtPosition(i).toString()){
                    case ("Palabra"):
                        binding.spinnerSubcategoria.setEnabled(true);
                        viewModel.clean(sSubcategoria, sEjercicio, swRuido, "categoria");
                        categoriaSeleccionada = adapterView.getItemAtPosition(i).toString();
                        binding.spinnerSubcategoria.setAdapter(viewModel.getAdapterSubcategoriaPalabras(getContext()));
                        break;
                    case ("Oraciones"):
                        binding.spinnerSubcategoria.setEnabled(true);
                        viewModel.clean(sSubcategoria, sEjercicio, swRuido, "categoria");
                        categoriaSeleccionada = adapterView.getItemAtPosition(i).toString();
                        binding.spinnerSubcategoria.setAdapter(viewModel.getAdapterSubcategoriaOraciones(getContext()));
                        break;
                    default:
                        viewModel.clean(sSubcategoria, sEjercicio, swRuido, "categoria_0");
                        break;
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
                    viewModel.clean(sSubcategoria, sEjercicio, swRuido, "subcategoria");
                    subcategoriaSeleccionada = adapterView.getItemAtPosition(i).toString();
                } else {
                    viewModel.clean(sSubcategoria, sEjercicio, swRuido, "subcategoria_0");
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
                    viewModel.clean(sSubcategoria, sEjercicio, swRuido, "ejercicio_0");
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
            public void onNothingSelected(AdapterView<?> parent) {

            }
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

                    bundle.putString("botonSeleccionado", botonSeleccionado);
                    bundle.putString("categoria", categoriaSeleccionada);
                    bundle.putString("subcategoria", subcategoriaSeleccionada);
                    bundle.putString("ejercicio", ejercicioSeleccionado);
                    bundle.putBoolean("ruido", ruido);
                    bundle.putString("tipoRuido", ruidoSeleccionado);
                    bundle.putFloat("intensidad", intensidad);

                    if (ejercicioSeleccionado.equals("Escribir lo que oyó")) {
                        navController.navigate(R.id.EjercicioEscribirFragment, bundle);
                    } else {
                        navController.navigate(R.id.EjercicioOpcionesFragment, bundle);
                    }

                }
            }
        });

    }

}