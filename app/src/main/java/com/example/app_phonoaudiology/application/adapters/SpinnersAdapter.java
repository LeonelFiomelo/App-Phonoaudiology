package com.example.app_phonoaudiology.application.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.app_phonoaudiology.domain.repository.constants.Constantes;

public class SpinnersAdapter {

    // RETORNA UN ADAPTER CATEGORIA
    public static ArrayAdapter<String> getCategoriaAdapter(Context context) {
        ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, Constantes.CATEGORIAS);
        return categoriaAdapter;
    }

    // RETORNA UN ADAPTER SUBCATEGORIA - PALABRAS
    public static ArrayAdapter<String> getSubcategoriaPalabrasAdapter(Context context) {
        ArrayAdapter<String> subcategoriaPalabrasAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, Constantes.SUBCATEGORIAS_PALABRAS);
        return subcategoriaPalabrasAdapter;
    }

    // RETORNA UN ADAPTER SUBCATEGORIAS - ORACIONES
    public static ArrayAdapter<String> getSubcategoriaOracionesAdapter(Context context) {
        ArrayAdapter<String> subcategoriaOracionesAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, Constantes.SUBCATEGORIAS_ORACIONES);
        return subcategoriaOracionesAdapter;
    }

    // RETORNA UN ADAPTER EJERCICIO
    public static ArrayAdapter<String> getEjercicioAdapter(Context context) {
        ArrayAdapter<String> ejercicioAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, Constantes.TIPOS_EJERCICIOS_PALABRA);
        return ejercicioAdapter;
    }

    // RETORNA UN ADAPTER RUIDO
    public static ArrayAdapter<String> getRuidoAdapter(Context context) {
        ArrayAdapter<String> ruidoAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, Constantes.RUIDOS);
        return ruidoAdapter;
    }

    // RETORNA UN ADAPTER CATEGORIA DEL SONIDO
    public static ArrayAdapter<String> getCategoriaSonidoAdapter(Context context) {
        ArrayAdapter<String> categoriaSonidoAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, Constantes.FILTRO_PRACTICA);
        return categoriaSonidoAdapter;
    }

}
