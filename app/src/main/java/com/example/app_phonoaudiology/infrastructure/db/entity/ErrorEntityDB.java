package com.example.app_phonoaudiology.infrastructure.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "error_table",
        foreignKeys = @ForeignKey(
                entity = ResultadoEntityDB.class,
                parentColumns = "uuid",
                childColumns = "uuidResultado",
                onDelete = ForeignKey.CASCADE
        )
)
public class ErrorEntityDB {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "uuidResultado")
    private String uuidResultado;
    @ColumnInfo(name = "estimulo")
    private String estimulo;
    @ColumnInfo(name = "primeraRespuesta")
    private String primeraRespuesta;
    @ColumnInfo(name = "segundaRespuesta")
    private String segundaRespuesta;

    public ErrorEntityDB() {
    }

    public long getId() { return id; }
    public String getUuidResultado() { return uuidResultado; }
    public String getEstimulo() {
        return estimulo;
    }
    public String getPrimeraRespuesta() {
        return primeraRespuesta;
    }

    public String getSegundaRespuesta() {
        return segundaRespuesta;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setUuidResultado(String uuidResultado) {
        this.uuidResultado = uuidResultado;
    }
    public void setEstimulo(String estimulo) {
        this.estimulo = estimulo;
    }
    public void setPrimeraRespuesta(String primeraRespuesta) {
        this.primeraRespuesta = primeraRespuesta;
    }
    public void setSegundaRespuesta(String segundaRespuesta) {
        this.segundaRespuesta = segundaRespuesta;
    }
}
