package com.example.app_phonoaudiology.infrastructure.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.util.List;

@Dao
public interface SoundDao {

    @Query("SELECT * FROM sound_table")
    LiveData<List<SoundEntity>> getSoundList();
    @Query("SELECT * FROM sound_table WHERE categoria_sonido = 'Números'")
    LiveData<List<SoundEntity>> getListOfNumeros();
    @Query("SELECT * FROM sound_table WHERE categoria_sonido = 'Días de la Semana'")
    LiveData<List<SoundEntity>> getListOfDias();
    @Query("SELECT * FROM sound_table WHERE categoria_sonido = 'Meses'")
    LiveData<List<SoundEntity>> getListOfMeses();
    @Query("SELECT * FROM sound_table WHERE categoria_sonido = 'Colores'")
    LiveData<List<SoundEntity>> getListOfColores();
    @Query("SELECT * FROM sound_table WHERE categoria_sonido='Ruido'")
    LiveData<List<SoundEntity>> getListOfRuido();
    @Query("SELECT * FROM sound_table WHERE categoria_sonido = 'Oraciones'")
    LiveData<List<SoundEntity>> getListOfOraciones();
    @Query("SELECT * FROM sound_table WHERE nombre_sonido = :nombreSonido")
    LiveData<List<SoundEntity>> getRutaSonido(String nombreSonido);
    @Query("SELECT * FROM sound_table WHERE nombre_sonido = :nombreSonido")
    LiveData<SoundEntity> getNecesitoConnector(String nombreSonido);
    @Query("DELETE FROM sound_table")
    void borrarTodos();

    @Insert
    void agregarSonido(SoundEntity sound_table);

    @Delete
    void eliminarSonido(SoundEntity sound_table);

}
