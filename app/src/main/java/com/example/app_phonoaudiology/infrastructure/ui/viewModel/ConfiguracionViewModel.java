package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.application.adapters.SpinnersAdapter;
import com.example.app_phonoaudiology.domain.repository.constants.Constantes;

public class ConfiguracionViewModel extends ViewModel {

    // CONSTRUCTOR
    public ConfiguracionViewModel() {
    }

    // RETORNA UN ADAPTER CATEGORIA
    public ArrayAdapter<String> getAdapterCategoria(Context context) {
        return SpinnersAdapter.getCategoriaAdapter(context);
    }
    // RETORNA UN ADAPTER SUBCATEGORIA - PALABRAS
    public ArrayAdapter<String> getAdapterSubcategoriaPalabras(Context context) {
        return SpinnersAdapter.getSubcategoriaPalabrasAdapter(context);
    }
    // RETORNA UN ADAPTER SUBCATEGORIA - ORACIONES
    public ArrayAdapter<String> getAdapterSubcategoriaOraciones(Context context) {
        return SpinnersAdapter.getSubcategoriaOracionesAdapter(context);
    }
    // RETORNA UN ADAPTER EJERCICIO
    public ArrayAdapter<String> getAdapterEjercicio(Context context) {
        return SpinnersAdapter.getEjercicioAdapter(context);
    }
    // RETORNA UN ADAPTER RUIDO
    public ArrayAdapter<String> getAdapterRuido(Context context) {
        return SpinnersAdapter.getRuidoAdapter(context);
    }

    // VALIDA LOS CAMPOS SELECCIONADOS DE LOS SPINNERS
    public boolean validate(Context context, Spinner spinnerCategoria, Spinner spinnerSubcategoria, Spinner spinnerEjercicio) {
        boolean validation = true;
        String message = "";
        if (spinnerCategoria.getSelectedItemPosition() == 0) {
            message = "Error: debe saleccionar una categoría";
            validation = false;
        }
        if (spinnerSubcategoria.getSelectedItemPosition() == 0) {
            message = "Error: debe seleccionar una subcategoría";
            validation = false;
        }
        if (spinnerEjercicio.getSelectedItemPosition() == 0) {
            message = "Error: Debe seleccionar un tipo de ejercicio";
            validation = false;
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return validation;
    }

}
