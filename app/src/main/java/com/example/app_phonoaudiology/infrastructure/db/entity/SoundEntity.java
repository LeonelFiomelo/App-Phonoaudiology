package com.example.app_phonoaudiology.infrastructure.db.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sound_table")
public class SoundEntity {

    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombre_sonido")
    private String nombre_sonido;
    @ColumnInfo(name = "categoria_sonido")
    private String categoria_sonido;
    @ColumnInfo(name = "ruta_sonido")
    private String ruta_sonido;
    @ColumnInfo(name = "personalizado")
    private String personalizado;
    @ColumnInfo(name = "cache")
    private Boolean cache;

    public SoundEntity(String nombre_sonido, String categoria_sonido, String ruta_sonido, String personalizado, @Nullable Boolean cache) {
        this.nombre_sonido = nombre_sonido;
        this.categoria_sonido = categoria_sonido;
        this.ruta_sonido = ruta_sonido;
        this.personalizado = personalizado;
        this.cache = cache;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public String getNombre_sonido() { return nombre_sonido; }
    public String getCategoria_sonido() { return categoria_sonido; }
    public String getRuta_sonido() { return ruta_sonido; }
    public String getPersonalizado() { return personalizado; }
    public Boolean getCache() { return cache; }

}
