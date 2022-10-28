package com.example.app_phonoaudiology.infrastructure.db.dao;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoErroresEntityDB;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ResultadoDao_Impl implements ResultadoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ResultadoEntityDB> __insertionAdapterOfResultadoEntityDB;

  private final EntityInsertionAdapter<ErrorEntityDB> __insertionAdapterOfErrorEntityDB;

  private final EntityDeletionOrUpdateAdapter<ResultadoEntityDB> __deletionAdapterOfResultadoEntityDB;

  private final EntityDeletionOrUpdateAdapter<ResultadoEntityDB> __updateAdapterOfResultadoEntityDB;

  private final EntityDeletionOrUpdateAdapter<ErrorEntityDB> __updateAdapterOfErrorEntityDB;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllResultados;

  public ResultadoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfResultadoEntityDB = new EntityInsertionAdapter<ResultadoEntityDB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `resultado_table` (`uuid`,`fecha`,`correctas`,`intentos`,`categoria`,`subcategoria`,`ejercicio`,`ruido`,`tipoRuido`,`intensidad`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ResultadoEntityDB value) {
        if (value.getUuid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUuid());
        }
        if (value.getFecha() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFecha());
        }
        stmt.bindLong(3, value.getCorrectas());
        stmt.bindLong(4, value.getIntentos());
        if (value.getCategoria() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCategoria());
        }
        if (value.getSubcategoria() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSubcategoria());
        }
        if (value.getEjercicio() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEjercicio());
        }
        final Integer _tmp = value.getRuido() == null ? null : (value.getRuido() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp);
        }
        if (value.getTipoRuido() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTipoRuido());
        }
        stmt.bindDouble(10, value.getIntensidad());
      }
    };
    this.__insertionAdapterOfErrorEntityDB = new EntityInsertionAdapter<ErrorEntityDB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `error_table` (`id`,`uuidResultado`,`estimulo`,`primeraRespuesta`,`segundaRespuesta`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ErrorEntityDB value) {
        stmt.bindLong(1, value.getId());
        if (value.getUuidResultado() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUuidResultado());
        }
        if (value.getEstimulo() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEstimulo());
        }
        if (value.getPrimeraRespuesta() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPrimeraRespuesta());
        }
        if (value.getSegundaRespuesta() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSegundaRespuesta());
        }
      }
    };
    this.__deletionAdapterOfResultadoEntityDB = new EntityDeletionOrUpdateAdapter<ResultadoEntityDB>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `resultado_table` WHERE `uuid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ResultadoEntityDB value) {
        if (value.getUuid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUuid());
        }
      }
    };
    this.__updateAdapterOfResultadoEntityDB = new EntityDeletionOrUpdateAdapter<ResultadoEntityDB>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `resultado_table` SET `uuid` = ?,`fecha` = ?,`correctas` = ?,`intentos` = ?,`categoria` = ?,`subcategoria` = ?,`ejercicio` = ?,`ruido` = ?,`tipoRuido` = ?,`intensidad` = ? WHERE `uuid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ResultadoEntityDB value) {
        if (value.getUuid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUuid());
        }
        if (value.getFecha() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFecha());
        }
        stmt.bindLong(3, value.getCorrectas());
        stmt.bindLong(4, value.getIntentos());
        if (value.getCategoria() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCategoria());
        }
        if (value.getSubcategoria() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSubcategoria());
        }
        if (value.getEjercicio() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEjercicio());
        }
        final Integer _tmp = value.getRuido() == null ? null : (value.getRuido() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp);
        }
        if (value.getTipoRuido() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTipoRuido());
        }
        stmt.bindDouble(10, value.getIntensidad());
        if (value.getUuid() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getUuid());
        }
      }
    };
    this.__updateAdapterOfErrorEntityDB = new EntityDeletionOrUpdateAdapter<ErrorEntityDB>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `error_table` SET `id` = ?,`uuidResultado` = ?,`estimulo` = ?,`primeraRespuesta` = ?,`segundaRespuesta` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ErrorEntityDB value) {
        stmt.bindLong(1, value.getId());
        if (value.getUuidResultado() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUuidResultado());
        }
        if (value.getEstimulo() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEstimulo());
        }
        if (value.getPrimeraRespuesta() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPrimeraRespuesta());
        }
        if (value.getSegundaRespuesta() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSegundaRespuesta());
        }
        stmt.bindLong(6, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllResultados = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM resultado_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final ResultadoEntityDB resultado) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfResultadoEntityDB.insert(resultado);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final ErrorEntityDB error) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfErrorEntityDB.insert(error);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void eliminarResultado(final ResultadoEntityDB resultado) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfResultadoEntityDB.handle(resultado);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ResultadoEntityDB resultado) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfResultadoEntityDB.handle(resultado);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ErrorEntityDB error) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfErrorEntityDB.handle(error);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllResultados() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllResultados.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllResultados.release(_stmt);
    }
  }

  @Override
  public LiveData<ResultadoEntityDB> getResultado(final String uuid) {
    final String _sql = "SELECT * FROM resultado_table WHERE uuid = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (uuid == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, uuid);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"resultado_table"}, false, new Callable<ResultadoEntityDB>() {
      @Override
      public ResultadoEntityDB call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfCorrectas = CursorUtil.getColumnIndexOrThrow(_cursor, "correctas");
          final int _cursorIndexOfIntentos = CursorUtil.getColumnIndexOrThrow(_cursor, "intentos");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfSubcategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "subcategoria");
          final int _cursorIndexOfEjercicio = CursorUtil.getColumnIndexOrThrow(_cursor, "ejercicio");
          final int _cursorIndexOfRuido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruido");
          final int _cursorIndexOfTipoRuido = CursorUtil.getColumnIndexOrThrow(_cursor, "tipoRuido");
          final int _cursorIndexOfIntensidad = CursorUtil.getColumnIndexOrThrow(_cursor, "intensidad");
          final ResultadoEntityDB _result;
          if(_cursor.moveToFirst()) {
            final String _tmpUuid;
            if (_cursor.isNull(_cursorIndexOfUuid)) {
              _tmpUuid = null;
            } else {
              _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
            }
            final String _tmpFecha;
            if (_cursor.isNull(_cursorIndexOfFecha)) {
              _tmpFecha = null;
            } else {
              _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            }
            final int _tmpCorrectas;
            _tmpCorrectas = _cursor.getInt(_cursorIndexOfCorrectas);
            final int _tmpIntentos;
            _tmpIntentos = _cursor.getInt(_cursorIndexOfIntentos);
            final String _tmpCategoria;
            if (_cursor.isNull(_cursorIndexOfCategoria)) {
              _tmpCategoria = null;
            } else {
              _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            }
            final String _tmpSubcategoria;
            if (_cursor.isNull(_cursorIndexOfSubcategoria)) {
              _tmpSubcategoria = null;
            } else {
              _tmpSubcategoria = _cursor.getString(_cursorIndexOfSubcategoria);
            }
            final String _tmpEjercicio;
            if (_cursor.isNull(_cursorIndexOfEjercicio)) {
              _tmpEjercicio = null;
            } else {
              _tmpEjercicio = _cursor.getString(_cursorIndexOfEjercicio);
            }
            final Boolean _tmpRuido;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfRuido)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfRuido);
            }
            _tmpRuido = _tmp == null ? null : _tmp != 0;
            final String _tmpTipoRuido;
            if (_cursor.isNull(_cursorIndexOfTipoRuido)) {
              _tmpTipoRuido = null;
            } else {
              _tmpTipoRuido = _cursor.getString(_cursorIndexOfTipoRuido);
            }
            final float _tmpIntensidad;
            _tmpIntensidad = _cursor.getFloat(_cursorIndexOfIntensidad);
            _result = new ResultadoEntityDB(_tmpUuid,_tmpFecha,_tmpCorrectas,_tmpIntentos,_tmpCategoria,_tmpSubcategoria,_tmpEjercicio,_tmpRuido,_tmpTipoRuido,_tmpIntensidad);
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

  @Override
  public LiveData<List<ResultadoEntityDB>> getAllResultados() {
    final String _sql = "SELECT * FROM resultado_table ORDER BY fecha DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"resultado_table"}, false, new Callable<List<ResultadoEntityDB>>() {
      @Override
      public List<ResultadoEntityDB> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfCorrectas = CursorUtil.getColumnIndexOrThrow(_cursor, "correctas");
          final int _cursorIndexOfIntentos = CursorUtil.getColumnIndexOrThrow(_cursor, "intentos");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfSubcategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "subcategoria");
          final int _cursorIndexOfEjercicio = CursorUtil.getColumnIndexOrThrow(_cursor, "ejercicio");
          final int _cursorIndexOfRuido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruido");
          final int _cursorIndexOfTipoRuido = CursorUtil.getColumnIndexOrThrow(_cursor, "tipoRuido");
          final int _cursorIndexOfIntensidad = CursorUtil.getColumnIndexOrThrow(_cursor, "intensidad");
          final List<ResultadoEntityDB> _result = new ArrayList<ResultadoEntityDB>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ResultadoEntityDB _item;
            final String _tmpUuid;
            if (_cursor.isNull(_cursorIndexOfUuid)) {
              _tmpUuid = null;
            } else {
              _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
            }
            final String _tmpFecha;
            if (_cursor.isNull(_cursorIndexOfFecha)) {
              _tmpFecha = null;
            } else {
              _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            }
            final int _tmpCorrectas;
            _tmpCorrectas = _cursor.getInt(_cursorIndexOfCorrectas);
            final int _tmpIntentos;
            _tmpIntentos = _cursor.getInt(_cursorIndexOfIntentos);
            final String _tmpCategoria;
            if (_cursor.isNull(_cursorIndexOfCategoria)) {
              _tmpCategoria = null;
            } else {
              _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            }
            final String _tmpSubcategoria;
            if (_cursor.isNull(_cursorIndexOfSubcategoria)) {
              _tmpSubcategoria = null;
            } else {
              _tmpSubcategoria = _cursor.getString(_cursorIndexOfSubcategoria);
            }
            final String _tmpEjercicio;
            if (_cursor.isNull(_cursorIndexOfEjercicio)) {
              _tmpEjercicio = null;
            } else {
              _tmpEjercicio = _cursor.getString(_cursorIndexOfEjercicio);
            }
            final Boolean _tmpRuido;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfRuido)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfRuido);
            }
            _tmpRuido = _tmp == null ? null : _tmp != 0;
            final String _tmpTipoRuido;
            if (_cursor.isNull(_cursorIndexOfTipoRuido)) {
              _tmpTipoRuido = null;
            } else {
              _tmpTipoRuido = _cursor.getString(_cursorIndexOfTipoRuido);
            }
            final float _tmpIntensidad;
            _tmpIntensidad = _cursor.getFloat(_cursorIndexOfIntensidad);
            _item = new ResultadoEntityDB(_tmpUuid,_tmpFecha,_tmpCorrectas,_tmpIntentos,_tmpCategoria,_tmpSubcategoria,_tmpEjercicio,_tmpRuido,_tmpTipoRuido,_tmpIntensidad);
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
  public LiveData<List<ErrorEntityDB>> getAllErrores() {
    final String _sql = "SELECT * FROM error_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"error_table"}, false, new Callable<List<ErrorEntityDB>>() {
      @Override
      public List<ErrorEntityDB> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUuidResultado = CursorUtil.getColumnIndexOrThrow(_cursor, "uuidResultado");
          final int _cursorIndexOfEstimulo = CursorUtil.getColumnIndexOrThrow(_cursor, "estimulo");
          final int _cursorIndexOfPrimeraRespuesta = CursorUtil.getColumnIndexOrThrow(_cursor, "primeraRespuesta");
          final int _cursorIndexOfSegundaRespuesta = CursorUtil.getColumnIndexOrThrow(_cursor, "segundaRespuesta");
          final List<ErrorEntityDB> _result = new ArrayList<ErrorEntityDB>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ErrorEntityDB _item;
            _item = new ErrorEntityDB();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpUuidResultado;
            if (_cursor.isNull(_cursorIndexOfUuidResultado)) {
              _tmpUuidResultado = null;
            } else {
              _tmpUuidResultado = _cursor.getString(_cursorIndexOfUuidResultado);
            }
            _item.setUuidResultado(_tmpUuidResultado);
            final String _tmpEstimulo;
            if (_cursor.isNull(_cursorIndexOfEstimulo)) {
              _tmpEstimulo = null;
            } else {
              _tmpEstimulo = _cursor.getString(_cursorIndexOfEstimulo);
            }
            _item.setEstimulo(_tmpEstimulo);
            final String _tmpPrimeraRespuesta;
            if (_cursor.isNull(_cursorIndexOfPrimeraRespuesta)) {
              _tmpPrimeraRespuesta = null;
            } else {
              _tmpPrimeraRespuesta = _cursor.getString(_cursorIndexOfPrimeraRespuesta);
            }
            _item.setPrimeraRespuesta(_tmpPrimeraRespuesta);
            final String _tmpSegundaRespuesta;
            if (_cursor.isNull(_cursorIndexOfSegundaRespuesta)) {
              _tmpSegundaRespuesta = null;
            } else {
              _tmpSegundaRespuesta = _cursor.getString(_cursorIndexOfSegundaRespuesta);
            }
            _item.setSegundaRespuesta(_tmpSegundaRespuesta);
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
  public LiveData<List<ResultadoErroresEntityDB>> getAllResultadosConErrores() {
    final String _sql = "SELECT * FROM resultado_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"error_table","resultado_table"}, true, new Callable<List<ResultadoErroresEntityDB>>() {
      @Override
      public List<ResultadoErroresEntityDB> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
            final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
            final int _cursorIndexOfCorrectas = CursorUtil.getColumnIndexOrThrow(_cursor, "correctas");
            final int _cursorIndexOfIntentos = CursorUtil.getColumnIndexOrThrow(_cursor, "intentos");
            final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
            final int _cursorIndexOfSubcategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "subcategoria");
            final int _cursorIndexOfEjercicio = CursorUtil.getColumnIndexOrThrow(_cursor, "ejercicio");
            final int _cursorIndexOfRuido = CursorUtil.getColumnIndexOrThrow(_cursor, "ruido");
            final int _cursorIndexOfTipoRuido = CursorUtil.getColumnIndexOrThrow(_cursor, "tipoRuido");
            final int _cursorIndexOfIntensidad = CursorUtil.getColumnIndexOrThrow(_cursor, "intensidad");
            final ArrayMap<String, ArrayList<ErrorEntityDB>> _collectionErrores = new ArrayMap<String, ArrayList<ErrorEntityDB>>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfUuid)) {
                final String _tmpKey = _cursor.getString(_cursorIndexOfUuid);
                ArrayList<ErrorEntityDB> _tmpErroresCollection = _collectionErrores.get(_tmpKey);
                if (_tmpErroresCollection == null) {
                  _tmpErroresCollection = new ArrayList<ErrorEntityDB>();
                  _collectionErrores.put(_tmpKey, _tmpErroresCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshiperrorTableAscomExampleAppPhonoaudiologyInfrastructureDbEntityErrorEntityDB(_collectionErrores);
            final List<ResultadoErroresEntityDB> _result = new ArrayList<ResultadoErroresEntityDB>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final ResultadoErroresEntityDB _item;
              final ResultadoEntityDB _tmpResultadoEntityDB;
              if (! (_cursor.isNull(_cursorIndexOfUuid) && _cursor.isNull(_cursorIndexOfFecha) && _cursor.isNull(_cursorIndexOfCorrectas) && _cursor.isNull(_cursorIndexOfIntentos) && _cursor.isNull(_cursorIndexOfCategoria) && _cursor.isNull(_cursorIndexOfSubcategoria) && _cursor.isNull(_cursorIndexOfEjercicio) && _cursor.isNull(_cursorIndexOfRuido) && _cursor.isNull(_cursorIndexOfTipoRuido) && _cursor.isNull(_cursorIndexOfIntensidad))) {
                final String _tmpUuid;
                if (_cursor.isNull(_cursorIndexOfUuid)) {
                  _tmpUuid = null;
                } else {
                  _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
                }
                final String _tmpFecha;
                if (_cursor.isNull(_cursorIndexOfFecha)) {
                  _tmpFecha = null;
                } else {
                  _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
                }
                final int _tmpCorrectas;
                _tmpCorrectas = _cursor.getInt(_cursorIndexOfCorrectas);
                final int _tmpIntentos;
                _tmpIntentos = _cursor.getInt(_cursorIndexOfIntentos);
                final String _tmpCategoria;
                if (_cursor.isNull(_cursorIndexOfCategoria)) {
                  _tmpCategoria = null;
                } else {
                  _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
                }
                final String _tmpSubcategoria;
                if (_cursor.isNull(_cursorIndexOfSubcategoria)) {
                  _tmpSubcategoria = null;
                } else {
                  _tmpSubcategoria = _cursor.getString(_cursorIndexOfSubcategoria);
                }
                final String _tmpEjercicio;
                if (_cursor.isNull(_cursorIndexOfEjercicio)) {
                  _tmpEjercicio = null;
                } else {
                  _tmpEjercicio = _cursor.getString(_cursorIndexOfEjercicio);
                }
                final Boolean _tmpRuido;
                final Integer _tmp;
                if (_cursor.isNull(_cursorIndexOfRuido)) {
                  _tmp = null;
                } else {
                  _tmp = _cursor.getInt(_cursorIndexOfRuido);
                }
                _tmpRuido = _tmp == null ? null : _tmp != 0;
                final String _tmpTipoRuido;
                if (_cursor.isNull(_cursorIndexOfTipoRuido)) {
                  _tmpTipoRuido = null;
                } else {
                  _tmpTipoRuido = _cursor.getString(_cursorIndexOfTipoRuido);
                }
                final float _tmpIntensidad;
                _tmpIntensidad = _cursor.getFloat(_cursorIndexOfIntensidad);
                _tmpResultadoEntityDB = new ResultadoEntityDB(_tmpUuid,_tmpFecha,_tmpCorrectas,_tmpIntentos,_tmpCategoria,_tmpSubcategoria,_tmpEjercicio,_tmpRuido,_tmpTipoRuido,_tmpIntensidad);
              }  else  {
                _tmpResultadoEntityDB = null;
              }
              ArrayList<ErrorEntityDB> _tmpErroresCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfUuid)) {
                final String _tmpKey_1 = _cursor.getString(_cursorIndexOfUuid);
                _tmpErroresCollection_1 = _collectionErrores.get(_tmpKey_1);
              }
              if (_tmpErroresCollection_1 == null) {
                _tmpErroresCollection_1 = new ArrayList<ErrorEntityDB>();
              }
              _item = new ResultadoErroresEntityDB();
              _item.resultadoEntityDB = _tmpResultadoEntityDB;
              _item.errores = _tmpErroresCollection_1;
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
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

  private void __fetchRelationshiperrorTableAscomExampleAppPhonoaudiologyInfrastructureDbEntityErrorEntityDB(
      final ArrayMap<String, ArrayList<ErrorEntityDB>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<ErrorEntityDB>> _tmpInnerMap = new ArrayMap<String, ArrayList<ErrorEntityDB>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshiperrorTableAscomExampleAppPhonoaudiologyInfrastructureDbEntityErrorEntityDB(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<ErrorEntityDB>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshiperrorTableAscomExampleAppPhonoaudiologyInfrastructureDbEntityErrorEntityDB(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`uuidResultado`,`estimulo`,`primeraRespuesta`,`segundaRespuesta` FROM `error_table` WHERE `uuidResultado` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "uuidResultado");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfUuidResultado = 1;
      final int _cursorIndexOfEstimulo = 2;
      final int _cursorIndexOfPrimeraRespuesta = 3;
      final int _cursorIndexOfSegundaRespuesta = 4;
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey = _cursor.getString(_itemKeyIndex);
          ArrayList<ErrorEntityDB> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final ErrorEntityDB _item_1;
            _item_1 = new ErrorEntityDB();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item_1.setId(_tmpId);
            final String _tmpUuidResultado;
            if (_cursor.isNull(_cursorIndexOfUuidResultado)) {
              _tmpUuidResultado = null;
            } else {
              _tmpUuidResultado = _cursor.getString(_cursorIndexOfUuidResultado);
            }
            _item_1.setUuidResultado(_tmpUuidResultado);
            final String _tmpEstimulo;
            if (_cursor.isNull(_cursorIndexOfEstimulo)) {
              _tmpEstimulo = null;
            } else {
              _tmpEstimulo = _cursor.getString(_cursorIndexOfEstimulo);
            }
            _item_1.setEstimulo(_tmpEstimulo);
            final String _tmpPrimeraRespuesta;
            if (_cursor.isNull(_cursorIndexOfPrimeraRespuesta)) {
              _tmpPrimeraRespuesta = null;
            } else {
              _tmpPrimeraRespuesta = _cursor.getString(_cursorIndexOfPrimeraRespuesta);
            }
            _item_1.setPrimeraRespuesta(_tmpPrimeraRespuesta);
            final String _tmpSegundaRespuesta;
            if (_cursor.isNull(_cursorIndexOfSegundaRespuesta)) {
              _tmpSegundaRespuesta = null;
            } else {
              _tmpSegundaRespuesta = _cursor.getString(_cursorIndexOfSegundaRespuesta);
            }
            _item_1.setSegundaRespuesta(_tmpSegundaRespuesta);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
