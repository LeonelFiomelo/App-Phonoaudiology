package com.example.app_phonoaudiology.infrastructure.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ErrorDao_Impl implements ErrorDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ErrorEntityDB> __insertionAdapterOfErrorEntityDB;

  public ErrorDao_Impl(RoomDatabase __db) {
    this.__db = __db;
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
  }

  @Override
  public void insertErrores(final List<ErrorEntityDB> listaDeErrores) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfErrorEntityDB.insert(listaDeErrores);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertError(final ErrorEntityDB error) {
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
  public LiveData<List<ErrorEntityDB>> getErrores(final String uuid) {
    final String _sql = "SELECT * FROM error_table WHERE uuidResultado = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (uuid == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, uuid);
    }
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
