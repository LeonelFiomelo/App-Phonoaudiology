package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.databinding.*;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.ConfiguracionViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class ConfiguracionFragment extends Fragment {

    private FragmentConfiguracionBinding binding;
    private ConfiguracionViewModel viewModel;

    private Toolbar toolbar;
    private TextView txtIntensidad;
    private Spinner spinnerCategoria, spinnerSubcategoria, spinnerEjercicio, spinnerRuido,
                    spinnerPalabraClave;
    private SwitchCompat switchRuido;
    private SeekBar seekBarIntensidad;
    private Button btnComenzar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ConfiguracionViewModel.class);
        viewModel.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConfiguracionBinding.inflate(inflater, container, false);

        toolbar = binding.toolbarConfiguracion;
        txtIntensidad = binding.txtIntensidadConfiguracion;
        spinnerCategoria = binding.spinnerCategoriaConfiguracion;
        spinnerSubcategoria = binding.spinnerSubcategoriaConfiguracion;
        spinnerEjercicio = binding.spinnerEjercicioConfiguracion;
        spinnerRuido = binding.spinnerRuidoConfiguracion;
        spinnerPalabraClave = binding.spinnerPalabraClaveConfiguracion;
        switchRuido = binding.switchRuidoConfiguracion;
        seekBarIntensidad = binding.seekBarIntensidadConfiguracion;
        btnComenzar = binding.btnComenzarEjercicioConfiguracion;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        // OCULTAMOS ITEMS
        spinnerRuido.setVisibility(View.GONE);
        txtIntensidad.setVisibility(View.GONE);
        seekBarIntensidad.setVisibility(View.GONE);

        // ASIGNACION DE ADAPTERR A SPINNERS
        spinnerCategoria.setAdapter(viewModel.getAdapterCategoria(getContext()));
        spinnerSubcategoria.setAdapter(viewModel.getAdapterSubcategoriaPalabras(getContext()));
        spinnerEjercicio.setAdapter(viewModel.getAdapterEjercicio(getContext()));
        spinnerRuido.setAdapter(viewModel.getAdapterRuido(getContext()));
        spinnerPalabraClave.setAdapter(viewModel.getAdapterPalabraClave(getContext()));

        viewModel.setBotonSeleccionado(getArguments().getString("botonSeleccionado"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            // SE LE COLOCA UN LISTENER A LA BARRA DE NAVEGACION DE LA TOOLBAR
            // VUELVE AL FRAGMENT ANTERIOR
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // SE LE COLOCA UN LISTENER A LA SELECCION DE UN ITEM DEL SPINNER CATEGORIA
            // GUARDA EL ITEM SELECCIONADO
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                switch (adapterView.getItemAtPosition(i).toString()){
                    case ("Palabra"):
                        spinnerSubcategoria.setEnabled(true);
                        viewModel.clean(spinnerSubcategoria, spinnerEjercicio, spinnerPalabraClave, switchRuido, "categoria");
                        viewModel.setCategoria(adapterView.getItemAtPosition(i).toString());
                        spinnerSubcategoria.setAdapter(viewModel.getAdapterSubcategoriaPalabras(getContext()));
                        break;
                    case ("Oraciones"):
                        spinnerSubcategoria.setEnabled(true);
                        viewModel.clean(spinnerSubcategoria, spinnerEjercicio, spinnerPalabraClave, switchRuido, "categoria");
                        viewModel.setCategoria(adapterView.getItemAtPosition(i).toString());
                        spinnerSubcategoria.setAdapter(viewModel.getAdapterSubcategoriaOraciones(getContext()));
                        break;
                    default:
                        viewModel.clean(spinnerSubcategoria, spinnerEjercicio, spinnerPalabraClave, switchRuido, "categoria_0");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinnerSubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // SE LE COLOCA UN LISTENER A LA SELECCION DE UN ITEM DEL SPINNER SUBCATEGORIA
            // GUARDA EL ITEM SELECCIONADO
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i != 0) {
                    spinnerEjercicio.setEnabled(true);
                    viewModel.clean(spinnerSubcategoria, spinnerEjercicio, spinnerPalabraClave, switchRuido, "subcategoria");
                    viewModel.setSubcategoria(adapterView.getItemAtPosition(i).toString());
                } else {
                    viewModel.clean(spinnerSubcategoria, spinnerEjercicio, spinnerPalabraClave ,switchRuido, "subcategoria_0");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinnerEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // SE LE COLOCA UN LISTENER A LA SELECCION DE UN ITEM DEL SPINNER EJERCICIO
            // GUARDA EL ITEM SELECCIONADO
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                String categoria = (String) spinnerCategoria.getSelectedItem();
                if (i != 0) {

                    if (categoria.equals("Oraciones")) {
                        spinnerPalabraClave.setEnabled(true);
                    }
                    switchRuido.setEnabled(true);
                    viewModel.setEjercicio(adapterView.getItemAtPosition(i).toString());

                } else {
                    viewModel.clean(spinnerSubcategoria, spinnerEjercicio, spinnerPalabraClave, switchRuido, "ejercicio_0_palabra");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinnerPalabraClave.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i != 0) {
                    viewModel.setPalabraClave(adapterView.getItemAtPosition(i).toString());
                } else {
                    viewModel.setPalabraClave(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        switchRuido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            // SE LE COLOCA UN LISTENER AL CAMBIO DE ACTIVADO/DESACTIVADO DEL SWITCH DE RUIDO
            // GUARDA LA SELECCION
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewModel.setRuido(true);
                    spinnerRuido.setVisibility(View.VISIBLE);
                    txtIntensidad.setVisibility(View.VISIBLE);
                    seekBarIntensidad.setVisibility(View.VISIBLE);
                    binding.scrollViewConfiguracion.post(() -> binding.scrollViewConfiguracion.fullScroll(View.FOCUS_DOWN));
                } else {
                    viewModel.setRuido(false);
                    spinnerRuido.setVisibility(View.GONE);
                    txtIntensidad.setVisibility(View.GONE);
                    seekBarIntensidad.setVisibility(View.GONE);
                    binding.scrollViewConfiguracion.post(() -> binding.scrollViewConfiguracion.fullScroll(View.FOCUS_UP));
                }
            }
        });

        spinnerRuido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // SE LE COLOCA UN LISTENER A LA SELECCION DE UN ITEM DEL SPINNER RUIDO
            // GUARDA EL ITEM SELECCIONADO
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                viewModel.setTipoRuido(adapterView.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        seekBarIntensidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // SE LE COLOCA UN LISTENER AL CAMBIO DE SEEKBAR DE LA INTENSIDAD
            // GUARDA LA INTENSIDAD SELECCIONADA
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float intensidadSinFormatear = 0.1f * progress;
                BigDecimal bd = new BigDecimal(intensidadSinFormatear);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                viewModel.setIntensidad(bd.floatValue());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            // SE LE COLOCA UN LISTENER AL BOTON DE COMENZAR EJERCICIO
            // VA A NAVEGAR A UN FRAGMENT Y VA A PASAR UN BUNDLE
            @Override
            public void onClick(View v) {
                if(viewModel.validate(getContext(), spinnerCategoria, spinnerSubcategoria, spinnerEjercicio, spinnerPalabraClave)) {

                    if (viewModel.getEjercicio().equals("Escribir lo que oyó")) {
                        navController.navigate(R.id.EjercicioEscribirFragment, viewModel.getBundle());
                    } else {
                        navController.navigate(R.id.EjercicioOpcionesFragment, viewModel.getBundle());
                    }

                }
            }
        });

    }

}