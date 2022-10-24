package com.example.app_phonoaudiology.infrastructure.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SoundDao_Impl implements SoundDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SoundEntity> __insertionAdapterOfSoundEntity;

  private final EntityDeletionOrUpdateAdapter<SoundEntity> __deletionAdapterOfSoundEntity;

  private final SharedSQLiteStatement __preparedStmtOfBorrarTodos;

  public SoundDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSoundEntity = new EntityInsertionAdapter<SoundEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `sound_table` (`id`,`nombre_sonido`,`categoria_sonido`,`ruta_sonido`,`personalizado`,`cache`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SoundEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getNombre_sonido() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNombre_sonido());
        }
        if (value.getCategoria_sonido() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCategoria_sonido());
        }
        if (value.getRuta_sonido() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRuta_sonido());
        }
        if (value.getPersonalizado() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPersonalizado());
        }
        final Integer _tmp = value.getCache() == null ? null : (value.getCache() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp);
        }
      }
    };
    this.__deletionAdapterOfSoundEntity = new EntityDeletionOrUpdateAdapter<SoundEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `sound_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SoundEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfBorrarTodos = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM sound_table";
        return _query;
      }
    };
  }

  @Override
  public void agregarSonido(final SoundEntity sound_table) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSoundEntity.insert(sound_table);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void eliminarSonido(final SoundEntity sound_table) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSoundEntity.handle(sound_table);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void borrarTodos() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfBorrarTodos.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfBorrarTodos.release(_stmt);
    }
  }

  @Override
  public LiveData<List<SoundEntity>> getSoundList() {
    final String _sql = "SELECT * FROM sound_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<List<SoundEntity>>() {
      @Override
      public List<SoundEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final List<SoundEntity> _result = new ArrayList<SoundEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SoundEntity _item;
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _item = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SoundEntity>> getListOfNumeros() {
    final String _sql = "SELECT * FROM sound_table WHERE categoria_sonido = 'Números'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<List<SoundEntity>>() {
      @Override
      public List<SoundEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final List<SoundEntity> _result = new ArrayList<SoundEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SoundEntity _item;
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _item = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SoundEntity>> getListOfDias() {
    final String _sql = "SELECT * FROM sound_table WHERE categoria_sonido = 'Días de la Semana'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<List<SoundEntity>>() {
      @Override
      public List<SoundEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final List<SoundEntity> _result = new ArrayList<SoundEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SoundEntity _item;
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _item = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SoundEntity>> getListOfMeses() {
    final String _sql = "SELECT * FROM sound_table WHERE categoria_sonido = 'Meses'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<List<SoundEntity>>() {
      @Override
      public List<SoundEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final List<SoundEntity> _result = new ArrayList<SoundEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SoundEntity _item;
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _item = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SoundEntity>> getListOfColores() {
    final String _sql = "SELECT * FROM sound_table WHERE categoria_sonido = 'Colores'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<List<SoundEntity>>() {
      @Override
      public List<SoundEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final List<SoundEntity> _result = new ArrayList<SoundEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SoundEntity _item;
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _item = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SoundEntity>> getListOfRuido() {
    final String _sql = "SELECT * FROM sound_table WHERE categoria_sonido='Ruido'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<List<SoundEntity>>() {
      @Override
      public List<SoundEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final List<SoundEntity> _result = new ArrayList<SoundEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SoundEntity _item;
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _item = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SoundEntity>> getListOfOraciones() {
    final String _sql = "SELECT * FROM sound_table WHERE categoria_sonido = 'Oraciones'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<List<SoundEntity>>() {
      @Override
      public List<SoundEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final List<SoundEntity> _result = new ArrayList<SoundEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SoundEntity _item;
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _item = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SoundEntity>> getRutaSonido(final String nombreSonido) {
    final String _sql = "SELECT * FROM sound_table WHERE nombre_sonido = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nombreSonido == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nombreSonido);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<List<SoundEntity>>() {
      @Override
      public List<SoundEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final List<SoundEntity> _result = new ArrayList<SoundEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SoundEntity _item;
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _item = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<SoundEntity> getNecesitoConnector(final String nombreSonido) {
    final String _sql = "SELECT * FROM sound_table WHERE nombre_sonido = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nombreSonido == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nombreSonido);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"sound_table"}, false, new Callable<SoundEntity>() {
      @Override
      public SoundEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre_sonido");
          final int _cursorIndexOfCategoriaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria_sonido");
          final int _cursorIndexOfRutaSonido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruta_sonido");
          final int _cursorIndexOfPersonalizado = CursorUtil.getColumnIndexOrThrow(_cursor, "personalizado");
          final int _cursorIndexOfCache = CursorUtil.getColumnIndexOrThrow(_cursor, "cache");
          final SoundEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpNombre_sonido;
            if (_cursor.isNull(_cursorIndexOfNombreSonido)) {
              _tmpNombre_sonido = null;
            } else {
              _tmpNombre_sonido = _cursor.getString(_cursorIndexOfNombreSonido);
            }
            final String _tmpCategoria_sonido;
            if (_cursor.isNull(_cursorIndexOfCategoriaSonido)) {
              _tmpCategoria_sonido = null;
            } else {
              _tmpCategoria_sonido = _cursor.getString(_cursorIndexOfCategoriaSonido);
            }
            final String _tmpRuta_sonido;
            if (_cursor.isNull(_cursorIndexOfRutaSonido)) {
              _tmpRuta_sonido = null;
            } else {
              _tmpRuta_sonido = _cursor.getString(_cursorIndexOfRutaSonido);
            }
            final String _tmpPersonalizado;
            if (_cursor.isNull(_cursorIndexOfPersonalizado)) {
              _tmpPersonalizado = null;
            } else {
              _tmpPersonalizado = _cursor.getString(_cursorIndexOfPersonalizado);
            }
            final Boolean _tmpCache;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfCache)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfCache);
            }
            _tmpCache = _tmp == null ? null : _tmp != 0;
            _result = new SoundEntity(_tmpNombre_sonido,_tmpCategoria_sonido,_tmpRuta_sonido,_tmpPersonalizado,_tmpCache);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _result.setId(_tmpId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
