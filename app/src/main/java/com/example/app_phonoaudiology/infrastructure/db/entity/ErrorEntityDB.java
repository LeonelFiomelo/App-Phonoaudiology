package com.example.app_phonoaudiology.infrastructure.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "error_table",
        foreignKeys = @ForeignKey(
                entity = ResultadoEntityDB.class,
                parentColumns = "resultadoId",
                childColumns = "resultadoRelacionId"
        )
)
public class ErrorEntityDB {

    @PrimaryKey(autoGenerate = true)
    private int errorId;
    @ColumnInfo(name = "resultadoRelacionId")
    private int resultadoRelacionId;
    @ColumnInfo(name = "estimulo")
    private String estimulo;
    @ColumnInfo(name = "primeraRespuesta")
    private String primeraRespuesta;
    @ColumnInfo(name = "segundaRespuesta")
    private String segundaRespuesta;

    public ErrorEntityDB() {
//        this.resultadoRelacionId = resultadoRelacionId;
//        this.estimulo = estimulo;
//        this.primeraRespuesta = primeraRespuesta;
//        this.segundaRespuesta = segundaRespuesta;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }
    public int getErrorId() { return errorId; }

    public int getResultadoRelacionId() {
        return resultadoRelacionId;
    }

    public void setResultadoRelacionId(int resultadoRelacionId) {
        this.resultadoRelacionId = resultadoRelacionId;
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
