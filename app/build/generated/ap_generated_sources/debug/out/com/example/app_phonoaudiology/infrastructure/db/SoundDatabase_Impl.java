package com.example.app_phonoaudiology.infrastructure.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.app_phonoaudiology.infrastructure.db.dao.ResultadoDao;
import com.example.app_phonoaudiology.infrastructure.db.dao.ResultadoDao_Impl;
import com.example.app_phonoaudiology.infrastructure.db.dao.SoundDao;
import com.example.app_phonoaudiology.infrastructure.db.dao.SoundDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SoundDatabase_Impl extends SoundDatabase {
  private volatile SoundDao _soundDao;

  private volatile ResultadoDao _resultadoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(13) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `sound_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre_sonido` TEXT, `categoria_sonido` TEXT, `ruta_sonido` TEXT, `personalizado` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `resultado_table` (`resultadoId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fecha` TEXT, `correctas` INTEGER NOT NULL, `intentos` INTEGER NOT NULL, `categoria` TEXT, `subcategoria` TEXT, `ejercicio` TEXT, `ruido` INTEGER, `tipoRuido` TEXT, `intensidad` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `error_table` (`errorId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `resultadoRelacionId` INTEGER NOT NULL, `estimulo` TEXT, `primeraRespuesta` TEXT, `segundaRespuesta` TEXT, FOREIGN KEY(`resultadoRelacionId`) REFERENCES `resultado_table`(`resultadoId`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a73b2eb129c95bd1a39d77e3afe772ca')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `sound_table`");
        _db.execSQL("DROP TABLE IF EXISTS `resultado_table`");
        _db.execSQL("DROP TABLE IF EXISTS `error_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSoundTable = new HashMap<String, TableInfo.Column>(5);
        _columnsSoundTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSoundTable.put("nombre_sonido", new TableInfo.Column("nombre_sonido", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSoundTable.put("categoria_sonido", new TableInfo.Column("categoria_sonido", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSoundTable.put("ruta_sonido", new TableInfo.Column("ruta_sonido", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSoundTable.put("personalizado", new TableInfo.Column("personalizado", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSoundTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSoundTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSoundTable = new TableInfo("sound_table", _columnsSoundTable, _foreignKeysSoundTable, _indicesSoundTable);
        final TableInfo _existingSoundTable = TableInfo.read(_db, "sound_table");
        if (! _infoSoundTable.equals(_existingSoundTable)) {
          return new RoomOpenHelper.ValidationResult(false, "sound_table(com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity).\n"
                  + " Expected:\n" + _infoSoundTable + "\n"
                  + " Found:\n" + _existingSoundTable);
        }
        final HashMap<String, TableInfo.Column> _columnsResultadoTable = new HashMap<String, TableInfo.Column>(10);
        _columnsResultadoTable.put("resultadoId", new TableInfo.Column("resultadoId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("fecha", new TableInfo.Column("fecha", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("correctas", new TableInfo.Column("correctas", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("intentos", new TableInfo.Column("intentos", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("categoria", new TableInfo.Column("categoria", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("subcategoria", new TableInfo.Column("subcategoria", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("ejercicio", new TableInfo.Column("ejercicio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("ruido", new TableInfo.Column("ruido", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("tipoRuido", new TableInfo.Column("tipoRuido", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResultadoTable.put("intensidad", new TableInfo.Column("intensidad", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysResultadoTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesResultadoTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoResultadoTable = new TableInfo("resultado_table", _columnsResultadoTable, _foreignKeysResultadoTable, _indicesResultadoTable);
        final TableInfo _existingResultadoTable = TableInfo.read(_db, "resultado_table");
        if (! _infoResultadoTable.equals(_existingResultadoTable)) {
          return new RoomOpenHelper.ValidationResult(false, "resultado_table(com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB).\n"
                  + " Expected:\n" + _infoResultadoTable + "\n"
                  + " Found:\n" + _existingResultadoTable);
        }
        final HashMap<String, TableInfo.Column> _columnsErrorTable = new HashMap<String, TableInfo.Column>(5);
        _columnsErrorTable.put("errorId", new TableInfo.Column("errorId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsErrorTable.put("resultadoRelacionId", new TableInfo.Column("resultadoRelacionId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsErrorTable.put("estimulo", new TableInfo.Column("estimulo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsErrorTable.put("primeraRespuesta", new TableInfo.Column("primeraRespuesta", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsErrorTable.put("segundaRespuesta", new TableInfo.Column("segundaRespuesta", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysErrorTable = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysErrorTable.add(new TableInfo.ForeignKey("resultado_table", "NO ACTION", "NO ACTION",Arrays.asList("resultadoRelacionId"), Arrays.asList("resultadoId")));
        final HashSet<TableInfo.Index> _indicesErrorTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoErrorTable = new TableInfo("error_table", _columnsErrorTable, _foreignKeysErrorTable, _indicesErrorTable);
        final TableInfo _existingErrorTable = TableInfo.read(_db, "error_table");
        if (! _infoErrorTable.equals(_existingErrorTable)) {
          return new RoomOpenHelper.ValidationResult(false, "error_table(com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB).\n"
                  + " Expected:\n" + _infoErrorTable + "\n"
                  + " Found:\n" + _existingErrorTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a73b2eb129c95bd1a39d77e3afe772ca", "6cb3b8339972d274b7a2af02ec3874a4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "sound_table","resultado_table","error_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `sound_table`");
      _db.execSQL("DELETE FROM `error_table`");
      _db.execSQL("DELETE FROM `resultado_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SoundDao.class, SoundDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ResultadoDao.class, ResultadoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public SoundDao soundDao() {
    if (_soundDao != null) {
      return _soundDao;
    } else {
      synchronized(this) {
        if(_soundDao == null) {
          _soundDao = new SoundDao_Impl(this);
        }
        return _soundDao;
      }
    }
  }

  @Override
  public ResultadoDao resultadoDao() {
    if (_resultadoDao != null) {
      return _resultadoDao;
    } else {
      synchronized(this) {
        if(_resultadoDao == null) {
          _resultadoDao = new ResultadoDao_Impl(this);
        }
        return _resultadoDao;
      }
    }
  }
}
