package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.application.adapters.SpinnersAdapter;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;
import com.example.app_phonoaudiology.domain.repository.constants.Constantes;

public class ConfiguracionViewModel extends ViewModel {

    private ConfiguracionEntity configuracion;

    public ConfiguracionViewModel() {
    }

    public void onCreate() {
        configuracion = new ConfiguracionEntity();
    }

    public void setBotonSeleccionado(String botonSeleccionado) {
        configuracion.setBotonSeleccionado(botonSeleccionado);
    }
    public void setCategoria(String categoria) {
        configuracion.setCategoria(categoria);
    }
    public void setSubcategoria(String subcategoria) {
        configuracion.setSubcategoria(subcategoria);
    }
    public void setEjercicio(String ejercicio) {
        configuracion.setEjercicio(ejercicio);
    }
    public void setRuido(Boolean ruido) {
        configuracion.setRuido(ruido);
    }
    public void setTipoRuido(String tipoRuido) {
        configuracion.setTipoRuido(tipoRuido);
    }
    public void setIntensidad(float intensidad) {
        configuracion.setIntensidad(intensidad);
    }
    public String getEjercicio() {
        return configuracion.getEjercicio();
    }
    public float getIntensidad() {
        return configuracion.getIntensidad();
    }
    public Bundle getBundle() {
        return configuracion.getBundle();
    }

    public void clean(Spinner spinnerSubcategoria, Spinner spinnerEjercicio, SwitchCompat switchRuido, String tipoClean) {
        switch (tipoClean) {
            case ("categoria_0"):
                spinnerSubcategoria.setSelection(0);
                spinnerSubcategoria.setEnabled(false);
                spinnerEjercicio.setSelection(0);
                spinnerEjercicio.setEnabled(false);
                switchRuido.setChecked(false);
                switchRuido.setEnabled(false);
                break;
            case ("categoria"):
            case ("subcategoria_0"):
                spinnerEjercicio.setSelection(0);
                spinnerEjercicio.setEnabled(false);
                switchRuido.setChecked(false);
                switchRuido.setEnabled(false);
                break;
            case ("subcategoria"):
                break;
            case ("ejercicio_0"):
                switchRuido.setChecked(false);
                switchRuido.setEnabled(false);
        }
    }

    public ArrayAdapter<String> getAdapterCategoria(Context context) {
        // RETORNA UN ADAPTER CATEGORIA
        return SpinnersAdapter.getCategoriaAdapter(context);
    }
    public ArrayAdapter<String> getAdapterSubcategoriaPalabras(Context context) {
        // RETORNA UN ADAPTER SUBCATEGORIA - PALABRAS
        return SpinnersAdapter.getSubcategoriaPalabrasAdapter(context);
    }
    public ArrayAdapter<String> getAdapterSubcategoriaOraciones(Context context) {
        // RETORNA UN ADAPTER SUBCATEGORIA - ORACIONES
        return SpinnersAdapter.getSubcategoriaOracionesAdapter(context);
    }
    public ArrayAdapter<String> getAdapterEjercicio(Context context) {
        // RETORNA UN ADAPTER EJERCICIO
        return SpinnersAdapter.getEjercicioAdapter(context);
    }
    public ArrayAdapter<String> getAdapterRuido(Context context) {
        // RETORNA UN ADAPTER RUIDO
        return SpinnersAdapter.getRuidoAdapter(context);
    }

    public boolean validate(Context context, Spinner spinnerCategoria, Spinner spinnerSubcategoria, Spinner spinnerEjercicio) {
        // ESTE METODO VALIDA LOS CAMPOS SELECCIONADOS DE LOS SPINNERS
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
