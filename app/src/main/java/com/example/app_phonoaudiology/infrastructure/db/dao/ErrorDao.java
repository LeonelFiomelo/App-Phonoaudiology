package com.example.app_phonoaudiology.infrastructure.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;

import java.util.List;

@Dao
public interface ErrorDao {

    @Query("SELECT * FROM error_table WHERE uuidResultado = :uuid")
    LiveData<List<ErrorEntityDB>> getErrores(String uuid);

    @Insert
    void insertErrores(List<ErrorEntityDB> listaDeErrores);

    @Insert
    void insertError(ErrorEntityDB error);

}
