package com.example.notaviva.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class InterviewDao_Impl implements InterviewDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<InterviewEntity> __insertionAdapterOfInterviewEntity;

  private final EntityDeletionOrUpdateAdapter<InterviewEntity> __deletionAdapterOfInterviewEntity;

  public InterviewDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInterviewEntity = new EntityInsertionAdapter<InterviewEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `interviews` (`id`,`caseId`,`personName`,`date`,`findings`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InterviewEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCaseId());
        if (entity.getPersonName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPersonName());
        }
        if (entity.getDate() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDate());
        }
        if (entity.getFindings() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFindings());
        }
      }
    };
    this.__deletionAdapterOfInterviewEntity = new EntityDeletionOrUpdateAdapter<InterviewEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `interviews` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InterviewEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final InterviewEntity interview,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInterviewEntity.insertAndReturnId(interview);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final InterviewEntity interview,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfInterviewEntity.handle(interview);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<InterviewEntity>> getByCaseId(final long caseId) {
    final String _sql = "SELECT * FROM interviews WHERE caseId = ? ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, caseId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"interviews"}, new Callable<List<InterviewEntity>>() {
      @Override
      @NonNull
      public List<InterviewEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "caseId");
          final int _cursorIndexOfPersonName = CursorUtil.getColumnIndexOrThrow(_cursor, "personName");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfFindings = CursorUtil.getColumnIndexOrThrow(_cursor, "findings");
          final List<InterviewEntity> _result = new ArrayList<InterviewEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InterviewEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpCaseId;
            _tmpCaseId = _cursor.getLong(_cursorIndexOfCaseId);
            final String _tmpPersonName;
            if (_cursor.isNull(_cursorIndexOfPersonName)) {
              _tmpPersonName = null;
            } else {
              _tmpPersonName = _cursor.getString(_cursorIndexOfPersonName);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpFindings;
            if (_cursor.isNull(_cursorIndexOfFindings)) {
              _tmpFindings = null;
            } else {
              _tmpFindings = _cursor.getString(_cursorIndexOfFindings);
            }
            _item = new InterviewEntity(_tmpId,_tmpCaseId,_tmpPersonName,_tmpDate,_tmpFindings);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
