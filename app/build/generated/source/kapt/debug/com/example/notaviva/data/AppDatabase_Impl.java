package com.example.notaviva.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CaseDao _caseDao;

  private volatile InterviewDao _interviewDao;

  private volatile EvidenceDao _evidenceDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `cases` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `date` TEXT NOT NULL, `status` TEXT NOT NULL, `conclusion` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `interviews` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `caseId` INTEGER NOT NULL, `personName` TEXT NOT NULL, `date` TEXT NOT NULL, `findings` TEXT NOT NULL, FOREIGN KEY(`caseId`) REFERENCES `cases`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_interviews_caseId` ON `interviews` (`caseId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `evidences` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `caseId` INTEGER NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, FOREIGN KEY(`caseId`) REFERENCES `cases`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_evidences_caseId` ON `evidences` (`caseId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '22b6ccf53c643f93991cf6deeac08fa3')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `cases`");
        db.execSQL("DROP TABLE IF EXISTS `interviews`");
        db.execSQL("DROP TABLE IF EXISTS `evidences`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCases = new HashMap<String, TableInfo.Column>(6);
        _columnsCases.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCases.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCases.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCases.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCases.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCases.put("conclusion", new TableInfo.Column("conclusion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCases = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCases = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCases = new TableInfo("cases", _columnsCases, _foreignKeysCases, _indicesCases);
        final TableInfo _existingCases = TableInfo.read(db, "cases");
        if (!_infoCases.equals(_existingCases)) {
          return new RoomOpenHelper.ValidationResult(false, "cases(com.example.notaviva.data.CaseEntity).\n"
                  + " Expected:\n" + _infoCases + "\n"
                  + " Found:\n" + _existingCases);
        }
        final HashMap<String, TableInfo.Column> _columnsInterviews = new HashMap<String, TableInfo.Column>(5);
        _columnsInterviews.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterviews.put("caseId", new TableInfo.Column("caseId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterviews.put("personName", new TableInfo.Column("personName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterviews.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterviews.put("findings", new TableInfo.Column("findings", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInterviews = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysInterviews.add(new TableInfo.ForeignKey("cases", "CASCADE", "NO ACTION", Arrays.asList("caseId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesInterviews = new HashSet<TableInfo.Index>(1);
        _indicesInterviews.add(new TableInfo.Index("index_interviews_caseId", false, Arrays.asList("caseId"), Arrays.asList("ASC")));
        final TableInfo _infoInterviews = new TableInfo("interviews", _columnsInterviews, _foreignKeysInterviews, _indicesInterviews);
        final TableInfo _existingInterviews = TableInfo.read(db, "interviews");
        if (!_infoInterviews.equals(_existingInterviews)) {
          return new RoomOpenHelper.ValidationResult(false, "interviews(com.example.notaviva.data.InterviewEntity).\n"
                  + " Expected:\n" + _infoInterviews + "\n"
                  + " Found:\n" + _existingInterviews);
        }
        final HashMap<String, TableInfo.Column> _columnsEvidences = new HashMap<String, TableInfo.Column>(4);
        _columnsEvidences.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvidences.put("caseId", new TableInfo.Column("caseId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvidences.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvidences.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvidences = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysEvidences.add(new TableInfo.ForeignKey("cases", "CASCADE", "NO ACTION", Arrays.asList("caseId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesEvidences = new HashSet<TableInfo.Index>(1);
        _indicesEvidences.add(new TableInfo.Index("index_evidences_caseId", false, Arrays.asList("caseId"), Arrays.asList("ASC")));
        final TableInfo _infoEvidences = new TableInfo("evidences", _columnsEvidences, _foreignKeysEvidences, _indicesEvidences);
        final TableInfo _existingEvidences = TableInfo.read(db, "evidences");
        if (!_infoEvidences.equals(_existingEvidences)) {
          return new RoomOpenHelper.ValidationResult(false, "evidences(com.example.notaviva.data.EvidenceEntity).\n"
                  + " Expected:\n" + _infoEvidences + "\n"
                  + " Found:\n" + _existingEvidences);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "22b6ccf53c643f93991cf6deeac08fa3", "1464e804d0f1680ac697c2711112a829");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "cases","interviews","evidences");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `cases`");
      _db.execSQL("DELETE FROM `interviews`");
      _db.execSQL("DELETE FROM `evidences`");
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
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CaseDao.class, CaseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(InterviewDao.class, InterviewDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EvidenceDao.class, EvidenceDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CaseDao caseDao() {
    if (_caseDao != null) {
      return _caseDao;
    } else {
      synchronized(this) {
        if(_caseDao == null) {
          _caseDao = new CaseDao_Impl(this);
        }
        return _caseDao;
      }
    }
  }

  @Override
  public InterviewDao interviewDao() {
    if (_interviewDao != null) {
      return _interviewDao;
    } else {
      synchronized(this) {
        if(_interviewDao == null) {
          _interviewDao = new InterviewDao_Impl(this);
        }
        return _interviewDao;
      }
    }
  }

  @Override
  public EvidenceDao evidenceDao() {
    if (_evidenceDao != null) {
      return _evidenceDao;
    } else {
      synchronized(this) {
        if(_evidenceDao == null) {
          _evidenceDao = new EvidenceDao_Impl(this);
        }
        return _evidenceDao;
      }
    }
  }
}
