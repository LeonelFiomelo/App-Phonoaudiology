package com.example.app_phonoaudiology.infrastructure.db.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

public class ResultadoErroresEntityDB {

    @Embedded public ResultadoEntityDB resultadoEntityDB;
    @Relation(
            parentColumn = "id",
            entityColumn = "resultado_id"
    )
    public List<ErrorEntityDB> errores;

}
