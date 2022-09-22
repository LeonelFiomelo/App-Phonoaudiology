package com.example.app_phonoaudiology;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.app_phonoaudiology.common.Constantes;
import com.example.app_phonoaudiology.databinding.ActivityConfiguracionBinding;
import com.example.app_phonoaudiology.databinding.ActivityHomeBinding;

public class ConfiguracionActivity extends Fragment {

    private float intensidad;
    private String categoriaSeleccionada = "", subcategoriaSeleccionada = "", ejercicioSeleccionado = "";
    ArrayAdapter<String> adapterCategoria, adapterSubcategoria, adapterEjercicio, adapterRuido;

    public ConfiguracionActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_configuracion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // SCROLLVIEW
        ScrollView svConfiguracion = view.findViewById(R.id.scrollView_configuracion);

        // TEXVIEW
        TextView tvIntensidadDelRuido = view.findViewById(R.id.tv_intensidadDelRuido);

        // SEEKBAR
        SeekBar seekBarRuido = view.findViewById(R.id.seekBar_ruido);

        // SWITCH
        SwitchCompat switchRuido = view.findViewById(R.id.switch_Ruido);

        // SPINNERS
        Spinner spinnerCategoria = view.findViewById(R.id.spinner_categoria);
        Spinner spinnerSubcategoria = view.findViewById(R.id.spinner_subcategoria);
        Spinner spinnerEjercicio = view.findViewById(R.id.spinner_ejercicio);
        Spinner spinnerRuido = view.findViewById(R.id.spinner_ruido);

        // ADAPTERS
        adapterCategoria = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Constantes.CATEGORIAS);
        adapterSubcategoria = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Constantes.SUBCATEGORIAS_PALABRAS);
        adapterEjercicio = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Constantes.TIPOS_EJERCICIOS_PALABRA);
        adapterRuido =  new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Constantes.RUIDOS);

        // INCLUIMOS LOS ADAPTEERS EN LOS SPINNERS
        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerSubcategoria.setAdapter(adapterSubcategoria);
        spinnerEjercicio.setAdapter(adapterEjercicio);
        spinnerRuido.setAdapter(adapterRuido);

        // DESACTIVAMOS LOS SPINNERS
        spinnerSubcategoria.setEnabled(false);
        spinnerEjercicio.setEnabled(false);

        // SELECCIONAMOS EL SPINNER CATEGORIA
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i != 0) {
                    spinnerSubcategoria.setEnabled(true);
                    categoriaSeleccionada = adapterView.getItemAtPosition(i).toString();
                } else {
                    spinnerSubcategoria.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // SELECCIONAMOS EL SPINNER SUBCATEGORIA
        spinnerSubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i != 0) {
                    spinnerEjercicio.setEnabled(true);
                    subcategoriaSeleccionada = adapterView.getItemAtPosition(i).toString();
                } else {
                    spinnerEjercicio.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // SELECCIONAMOS EL SPINNER EJERCICIO
        spinnerEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i != 0) {
                    ejercicioSeleccionado = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // SELECCIONAMOS EL SWITCH DE RUIDO
        switchRuido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spinnerRuido.setVisibility(View.VISIBLE);
                    tvIntensidadDelRuido.setVisibility(View.VISIBLE);
                    seekBarRuido.setVisibility(View.VISIBLE);
                    svConfiguracion.post(() -> svConfiguracion.fullScroll(View.FOCUS_DOWN));
                } else {
                    spinnerRuido.setVisibility(View.GONE);
                    tvIntensidadDelRuido.setVisibility(View.GONE);
                    seekBarRuido.setVisibility(View.GONE);
                    svConfiguracion.post(() -> svConfiguracion.fullScroll(View.FOCUS_UP));
                }
            }
        });

        // SELECCIONAMOS EL SPINNER RUIDO
        spinnerRuido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // MODIFICAMOS INTENSIDAD DE LA SEEKBAR
        seekBarRuido.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intensidad = (float) 0.1 * progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }

}