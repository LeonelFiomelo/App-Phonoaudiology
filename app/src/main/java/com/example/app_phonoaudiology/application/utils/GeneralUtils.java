package com.example.app_phonoaudiology.application.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GeneralUtils {

    public static String getFechaFormateada() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = simpleDateFormat.format(date);
        return stringDate;
    }

    public static int getCompararStrings(String texto1, String texto2) {
        Collator comparador = Collator.getInstance();
        comparador.setStrength(Collator.PRIMARY);
        int resultado = comparador.compare(texto1, texto2);
        return resultado;
    }

}
