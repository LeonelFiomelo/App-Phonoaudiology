package com.example.app_phonoaudiology.infrastructure.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoErroresEntityDB;

import java.util.List;

@Dao
public interface ResultadoDao {

    @Insert
    void insert(ResultadoEntityDB resultado);

    @Insert
    void insert(ErrorEntityDB error);

    @Update
    void update(ResultadoEntityDB resultado);

    @Update
    void update(ErrorEntityDB error);

    @Query("DELETE FROM resultado_table")
    void deleteAllResultados();

    @Query("SELECT * FROM resultado_table ORDER BY id DESC")
    LiveData<List<ResultadoEntityDB>> getAllResultados();

    @Query("SELECT * FROM error_table ORDER BY id DESC")
    LiveData<List<ErrorEntityDB>> getAllErrores();

    @Transaction
    @Query("SELECT * FROM resultado_table")
    LiveData<List<ResultadoErroresEntityDB>> getAllResultadosConErrores();

}
