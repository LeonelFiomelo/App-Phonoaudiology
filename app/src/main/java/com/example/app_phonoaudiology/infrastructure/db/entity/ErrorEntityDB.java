package com.example.app_phonoaudiology.infrastructure.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "error_table",
        foreignKeys = @ForeignKey(
                entity = ResultadoEntityDB.class,
                parentColumns = "id",
                childColumns = "resultado_id"
        )
)
public class ErrorEntityDB {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "resultado_id")
    private int resultadoId;
    @ColumnInfo(name = "estimulo")
    private String estimulo;
    @ColumnInfo(name = "primeraRespuesta")
    private String primeraRespuesta;
    @ColumnInfo(name = "segundaRespuesta")
    private String segundaRespuesta;

    public ErrorEntityDB() {
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() { return id; }

    public int getResultadoId() {
        return resultadoId;
    }

    public void setResultadoId(int resultadoRelacionId) {
        this.resultadoId = resultadoRelacionId;
    }

    public String getEstimulo() {
        return estimulo;
    }

    public void setEstimulo(String estimulo) {
        this.estimulo = estimulo;
    }

    public String getPrimeraRespuesta() {
        return primeraRespuesta;
    }

    public void setPrimeraRespuesta(String primeraRespuesta) {
        this.primeraRespuesta = primeraRespuesta;
    }

    public String getSegundaRespuesta() {
        return segundaRespuesta;
    }

    public void setSegundaRespuesta(String segundaRespuesta) {
        this.segundaRespuesta = segundaRespuesta;
    }
}
