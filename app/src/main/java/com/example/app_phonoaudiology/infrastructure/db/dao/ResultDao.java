package com.example.app_phonoaudiology.infrastructure.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.app_phonoaudiology.infrastructure.db.entity.ResultEntity;

import java.util.List;

@Dao
public interface ResultDao {

    // CONSULTAS
    @Query("SELECT * FROM  result_table order by id DESC")
    LiveData<List<ResultEntity>> getAllResultados();
    @Query("DELETE FROM result_table")
    void borrarTodos();

    // INSERTAR
    @Insert
    void agregarResultado(ResultEntity resultado);

    // ELIMINAR
    @Delete
    void eliminarResultado(ResultEntity resultado);

}
