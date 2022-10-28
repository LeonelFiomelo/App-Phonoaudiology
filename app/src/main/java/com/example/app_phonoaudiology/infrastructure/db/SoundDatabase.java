package com.example.app_phonoaudiology.infrastructure.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.app_phonoaudiology.infrastructure.db.dao.ErrorDao;
import com.example.app_phonoaudiology.infrastructure.db.dao.ResultadoDao;
import com.example.app_phonoaudiology.infrastructure.db.dao.SoundDao;
import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.domain.repository.constants.Conectores;
import com.example.app_phonoaudiology.domain.repository.constants.Constantes;
import com.example.app_phonoaudiology.domain.repository.constants.Numeros;
import com.example.app_phonoaudiology.domain.repository.constants.Rutas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SoundEntity.class, ResultadoEntityDB.class, ErrorEntityDB.class}, version = 19)
public abstract class SoundDatabase extends RoomDatabase {

    // DAOS
    public abstract SoundDao soundDao();
    public abstract ResultadoDao resultadoDao();
    public abstract ErrorDao errorDao();

    // NOMBRE DE LA BASE DE DATOS
    private static final String DATABASE_NAME = "AppPhonoaudiology_db";

    // INTANCIA DE LA BASE DE DATOS
    private static SoundDatabase INSTANCE;

    // DECLARAMOS EN CUANTOS HILOS DE TRABAJO SE VAN A EJECUTAR LAS OPERACIONES
    private static final int THREADS = 2;
    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    // CREAMOS LA BASE DE DATOS
    public static SoundDatabase getInstanceSoundDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (SoundDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SoundDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }

    // PROPOBLAR BASE DE DATOS CON CALLBACK
    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dbExecutor.execute(() -> {
                SoundDao daoSound = INSTANCE.soundDao();
                // Colores
                daoSound.agregarSonido(new SoundEntity("Negro", Constantes.COLORES, "negro.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Azul", Constantes.COLORES, "azul.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Blanco", Constantes.COLORES, "blanco.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Marrón", Constantes.COLORES, "marron.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Amarillo", Constantes.COLORES, "amarillo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Rojo", Constantes.COLORES, "rojo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Rosa", Constantes.COLORES, "rosa.mp3", Constantes.NO_PERSONALIZADO,false));
                daoSound.agregarSonido(new SoundEntity("Verde", Constantes.COLORES, "verde.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Violeta", Constantes.COLORES, "violeta.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Naranja", Constantes.COLORES, "naranja.mp3", Constantes.NO_PERSONALIZADO, false));
                // DIAS MUJER
                daoSound.agregarSonido(new SoundEntity("Lunes", Constantes.DIAS_SEMANA, "female/days/f_lunes.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Martes", Constantes.DIAS_SEMANA, "female/days/f_martes.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Miércoles", Constantes.DIAS_SEMANA, "female/days/f_miercoles.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Jueves", Constantes.DIAS_SEMANA, "female/days/f_jueves.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Viernes", Constantes.DIAS_SEMANA, "female/days/f_viernes.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Sábado", Constantes.DIAS_SEMANA, "female/days/f_sabado.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Domingo", Constantes.DIAS_SEMANA, "female/days/f_domingo.mp3", Constantes.NO_PERSONALIZADO, false ));
                // Meses
                daoSound.agregarSonido(new SoundEntity("Enero", Constantes.MESES, "enero.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Febrero", Constantes.MESES, "febrero.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Marzo", Constantes.MESES, "marzo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Abril", Constantes.MESES, "abril.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Mayo", Constantes.MESES, "mayo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Junio", Constantes.MESES, "junio.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Julio", Constantes.MESES, "julio.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Agosto", Constantes.MESES, "agosto.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Septiembre", Constantes.MESES, "septiembre.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Octubre", Constantes.MESES, "octubre.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Noviembre", Constantes.MESES, "noviembre.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Diciembre", Constantes.MESES, "diciembre.mp3", Constantes.NO_PERSONALIZADO, false));
                // Ruidos
                daoSound.agregarSonido(new SoundEntity("Multitud de personas", Constantes.RUIDO, "ruido_personas.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Recreo de niños", Constantes.RUIDO, "ruido_recreo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Sirena Ambulancia", Constantes.RUIDO, "ruido_ambulancia.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Tráfico Intenso", Constantes.RUIDO, "ruido_trafico.mp3", Constantes.NO_PERSONALIZADO, false));
                // ORACIONES
                daoSound.agregarSonido(new SoundEntity("Al que madruga, Dios lo ayuda", Constantes.ORACIONES, "al que madruga.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("En boca cerrada no entran moscas", Constantes.ORACIONES, "en boca cerrada.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("No por mucho madrugar, se amanece más temprano", Constantes.ORACIONES, "no por mucho madrugar.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("No hay mal que por bien no venga", Constantes.ORACIONES, "no hay mal que.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("En casa de herrero, cuchillo de palo", Constantes.ORACIONES, "en casa de herrero.mp3", Constantes.NO_PERSONALIZADO, false));
                //NUMEROS MUJER 1-30
                daoSound.agregarSonido(new SoundEntity("1", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_UNO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("2", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DOS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("3", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_TRES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("4", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CUATRO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("5", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CINCO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("6", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_SEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("7", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_SIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("8", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_OCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("9", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_NUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("10", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIEZ, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("11", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_ONCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("12", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DOCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("13", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_TRECE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("14", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CATORCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("15", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_QUINCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("16", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIECISEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("17", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIECISIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("18", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIECIOCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("19", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIECINUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("20", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("21", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTIUNO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("22", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTIDOS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("23", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTITRES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("24", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTICUATRO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("25", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTICINCO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("26", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTISEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("27", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTISIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("28", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTIOCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("29", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTINUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("30", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_TREINTA, Constantes.NO_PERSONALIZADO, false));
//                 NUMEROS MUJER 100-130
                daoSound.agregarSonido(new SoundEntity("101", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_UNO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("102", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DOS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("103", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_TRES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("104", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_CUATRO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("105", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_CINCO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("106", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_SEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("107", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_SIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("108", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_OCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("109", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_NUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("110", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIEZ, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("111", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_ONCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("112", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DOCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("113", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_TRECE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("114", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_CATORCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("115", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_QUINCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("116", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIECISEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("117", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIECISIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("118", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIECIOCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("119", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIECINUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("120", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("121", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTIUNO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("122", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTIDOS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("123", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTITRES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("124", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTICUATRO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("125", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTICINCO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("126", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTISEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("127", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTISIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("128", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTIOCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("129", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTINUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("130", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_TREINTA, Constantes.NO_PERSONALIZADO, false));
                // CONECTOR NECESITO MUJER
                daoSound.agregarSonido(new SoundEntity("necesito", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_NECESITO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("vasos grandes", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_VASOS_GRANDES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("es hoy", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_ES_HOY, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("hoy es", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_HOY_ES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("y esta lloviendo", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_Y_LLOVIENDO, Constantes.NO_PERSONALIZADO, false));
                // ORACIONES MUJER
                daoSound.agregarSonido(new SoundEntity("Al que madruga, Dios lo ayuda", Constantes.ORACIONES, "al que madruga.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("En boca cerrada no entran moscas", Constantes.ORACIONES, "en boca cerrada.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("No por mucho madrugar, se amanece más temprano", Constantes.ORACIONES, "no por mucho madrugar.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("No hay mal que por bien no venga", Constantes.ORACIONES, "no hay mal que.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("En casa de herrero, cuchillo de palo", Constantes.ORACIONES, "en casa de herrero.mp3", Constantes.NO_PERSONALIZADO, false ));
            });
        }
        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
            dbExecutor.execute(() -> {
                SoundDao daoSound = INSTANCE.soundDao();
                // Colores
                daoSound.agregarSonido(new SoundEntity("Negro", Constantes.COLORES, "negro.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Azul", Constantes.COLORES, "azul.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Blanco", Constantes.COLORES, "blanco.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Marrón", Constantes.COLORES, "marron.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Amarillo", Constantes.COLORES, "amarillo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Rojo", Constantes.COLORES, "rojo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Rosa", Constantes.COLORES, "rosa.mp3", Constantes.NO_PERSONALIZADO,false));
                daoSound.agregarSonido(new SoundEntity("Verde", Constantes.COLORES, "verde.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Violeta", Constantes.COLORES, "violeta.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Naranja", Constantes.COLORES, "naranja.mp3", Constantes.NO_PERSONALIZADO, false));
                // DIAS MUJER
                daoSound.agregarSonido(new SoundEntity("Lunes", Constantes.DIAS_SEMANA, "female/days/f_lunes.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Martes", Constantes.DIAS_SEMANA, "female/days/f_martes.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Miércoles", Constantes.DIAS_SEMANA, "female/days/f_miercoles.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Jueves", Constantes.DIAS_SEMANA, "female/days/f_jueves.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Viernes", Constantes.DIAS_SEMANA, "female/days/f_viernes.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Sábado", Constantes.DIAS_SEMANA, "female/days/f_sabado.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("Domingo", Constantes.DIAS_SEMANA, "female/days/f_domingo.mp3", Constantes.NO_PERSONALIZADO, false ));
                // Meses
                daoSound.agregarSonido(new SoundEntity("Enero", Constantes.MESES, "enero.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Febrero", Constantes.MESES, "febrero.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Marzo", Constantes.MESES, "marzo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Abril", Constantes.MESES, "abril.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Mayo", Constantes.MESES, "mayo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Junio", Constantes.MESES, "junio.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Julio", Constantes.MESES, "julio.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Agosto", Constantes.MESES, "agosto.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Septiembre", Constantes.MESES, "septiembre.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Octubre", Constantes.MESES, "octubre.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Noviembre", Constantes.MESES, "noviembre.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Diciembre", Constantes.MESES, "diciembre.mp3", Constantes.NO_PERSONALIZADO, false));
                // Ruidos
                daoSound.agregarSonido(new SoundEntity("Multitud de personas", Constantes.RUIDO, "ruido_personas.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Recreo de niños", Constantes.RUIDO, "ruido_recreo.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Sirena Ambulancia", Constantes.RUIDO, "ruido_ambulancia.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("Tráfico Intenso", Constantes.RUIDO, "ruido_trafico.mp3", Constantes.NO_PERSONALIZADO, false));
                // ORACIONES
                daoSound.agregarSonido(new SoundEntity("Al que madruga, Dios lo ayuda", Constantes.ORACIONES, "al que madruga.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("En boca cerrada no entran moscas", Constantes.ORACIONES, "en boca cerrada.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("No por mucho madrugar, se amanece más temprano", Constantes.ORACIONES, "no por mucho madrugar.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("No hay mal que por bien no venga", Constantes.ORACIONES, "no hay mal que.mp3", Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("En casa de herrero, cuchillo de palo", Constantes.ORACIONES, "en casa de herrero.mp3", Constantes.NO_PERSONALIZADO, false));
                //NUMEROS MUJER 1-30
                daoSound.agregarSonido(new SoundEntity("1", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_UNO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("2", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DOS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("3", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_TRES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("4", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CUATRO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("5", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CINCO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("6", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_SEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("7", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_SIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("8", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_OCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("9", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_NUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("10", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIEZ, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("11", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_ONCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("12", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DOCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("13", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_TRECE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("14", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CATORCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("15", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_QUINCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("16", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIECISEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("17", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIECISIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("18", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIECIOCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("19", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_DIECINUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("20", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("21", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTIUNO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("22", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTIDOS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("23", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTITRES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("24", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTICUATRO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("25", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTICINCO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("26", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTISEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("27", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTISIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("28", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTIOCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("29", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_VEINTINUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("30", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_TREINTA, Constantes.NO_PERSONALIZADO, false));
//                 NUMEROS MUJER 100-130
                daoSound.agregarSonido(new SoundEntity("101", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_UNO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("102", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DOS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("103", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_TRES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("104", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_CUATRO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("105", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_CINCO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("106", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_SEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("107", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_SIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("108", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_OCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("109", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_NUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("110", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIEZ, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("111", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_ONCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("112", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DOCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("113", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_TRECE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("114", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_CATORCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("115", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_QUINCE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("116", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIECISEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("117", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIECISIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("118", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIECIOCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("119", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_DIECINUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("120", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("121", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTIUNO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("122", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTIDOS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("123", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTITRES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("124", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTICUATRO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("125", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTICINCO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("126", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTISEIS, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("127", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTISIETE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("128", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTIOCHO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("129", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_VEINTINUEVE, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("130", Constantes.NUMEROS, Rutas.PATH_NUMBERS_FEMALE + Numeros.FILE_CIENTO_TREINTA, Constantes.NO_PERSONALIZADO, false));
                // CONECTOR NECESITO MUJER
                daoSound.agregarSonido(new SoundEntity("necesito", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_NECESITO, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("vasos grandes", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_VASOS_GRANDES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("es hoy", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_ES_HOY, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("hoy es", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_HOY_ES, Constantes.NO_PERSONALIZADO, false));
                daoSound.agregarSonido(new SoundEntity("y esta lloviendo", Constantes.VARIOS, Rutas.PATH_CONNECTORS_FEMALE + Conectores.FILE_Y_LLOVIENDO, Constantes.NO_PERSONALIZADO, false));
                // ORACIONES MUJER
                daoSound.agregarSonido(new SoundEntity("Al que madruga, Dios lo ayuda", Constantes.ORACIONES, "al que madruga.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("En boca cerrada no entran moscas", Constantes.ORACIONES, "en boca cerrada.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("No por mucho madrugar, se amanece más temprano", Constantes.ORACIONES, "no por mucho madrugar.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("No hay mal que por bien no venga", Constantes.ORACIONES, "no hay mal que.mp3", Constantes.NO_PERSONALIZADO, false ));
                daoSound.agregarSonido(new SoundEntity("En casa de herrero, cuchillo de palo", Constantes.ORACIONES, "en casa de herrero.mp3", Constantes.NO_PERSONALIZADO, false ));
            });
        }
    };
}
