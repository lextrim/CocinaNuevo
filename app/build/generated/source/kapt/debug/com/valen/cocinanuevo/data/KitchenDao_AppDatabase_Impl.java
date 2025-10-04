package com.valen.cocinanuevo.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
public final class KitchenDao_AppDatabase_Impl implements KitchenDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<KitchenOrderEntity> __insertionAdapterOfKitchenOrderEntity;

  private final EntityDeletionOrUpdateAdapter<KitchenOrderEntity> __deletionAdapterOfKitchenOrderEntity;

  private final EntityDeletionOrUpdateAdapter<KitchenOrderEntity> __updateAdapterOfKitchenOrderEntity;

  public KitchenDao_AppDatabase_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfKitchenOrderEntity = new EntityInsertionAdapter<KitchenOrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `kitchen_orders` (`id`,`clientRef`,`deliveryDate`,`cascos`,`puertas`,`terminada`,`notes`,`category`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final KitchenOrderEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getClientRef() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getClientRef());
        }
        statement.bindLong(3, entity.getDeliveryDate());
        final int _tmp = entity.getCascos() ? 1 : 0;
        statement.bindLong(4, _tmp);
        final int _tmp_1 = entity.getPuertas() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        final int _tmp_2 = entity.getTerminada() ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategory());
        }
      }
    };
    this.__deletionAdapterOfKitchenOrderEntity = new EntityDeletionOrUpdateAdapter<KitchenOrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `kitchen_orders` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final KitchenOrderEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfKitchenOrderEntity = new EntityDeletionOrUpdateAdapter<KitchenOrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `kitchen_orders` SET `id` = ?,`clientRef` = ?,`deliveryDate` = ?,`cascos` = ?,`puertas` = ?,`terminada` = ?,`notes` = ?,`category` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final KitchenOrderEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getClientRef() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getClientRef());
        }
        statement.bindLong(3, entity.getDeliveryDate());
        final int _tmp = entity.getCascos() ? 1 : 0;
        statement.bindLong(4, _tmp);
        final int _tmp_1 = entity.getPuertas() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        final int _tmp_2 = entity.getTerminada() ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategory());
        }
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final KitchenOrderEntity order,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfKitchenOrderEntity.insert(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final KitchenOrderEntity order,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfKitchenOrderEntity.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final KitchenOrderEntity order,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfKitchenOrderEntity.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<KitchenOrderEntity>> getAll() {
    final String _sql = "SELECT * FROM kitchen_orders ORDER BY deliveryDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"kitchen_orders"}, new Callable<List<KitchenOrderEntity>>() {
      @Override
      @NonNull
      public List<KitchenOrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfClientRef = CursorUtil.getColumnIndexOrThrow(_cursor, "clientRef");
          final int _cursorIndexOfDeliveryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryDate");
          final int _cursorIndexOfCascos = CursorUtil.getColumnIndexOrThrow(_cursor, "cascos");
          final int _cursorIndexOfPuertas = CursorUtil.getColumnIndexOrThrow(_cursor, "puertas");
          final int _cursorIndexOfTerminada = CursorUtil.getColumnIndexOrThrow(_cursor, "terminada");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final List<KitchenOrderEntity> _result = new ArrayList<KitchenOrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final KitchenOrderEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpClientRef;
            if (_cursor.isNull(_cursorIndexOfClientRef)) {
              _tmpClientRef = null;
            } else {
              _tmpClientRef = _cursor.getString(_cursorIndexOfClientRef);
            }
            final long _tmpDeliveryDate;
            _tmpDeliveryDate = _cursor.getLong(_cursorIndexOfDeliveryDate);
            final boolean _tmpCascos;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCascos);
            _tmpCascos = _tmp != 0;
            final boolean _tmpPuertas;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfPuertas);
            _tmpPuertas = _tmp_1 != 0;
            final boolean _tmpTerminada;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfTerminada);
            _tmpTerminada = _tmp_2 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item = new KitchenOrderEntity(_tmpId,_tmpClientRef,_tmpDeliveryDate,_tmpCascos,_tmpPuertas,_tmpTerminada,_tmpNotes,_tmpCategory);
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
  public Flow<List<KitchenOrderEntity>> getByCategory(final String category) {
    final String _sql = "SELECT * FROM kitchen_orders WHERE category = ? ORDER BY deliveryDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"kitchen_orders"}, new Callable<List<KitchenOrderEntity>>() {
      @Override
      @NonNull
      public List<KitchenOrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfClientRef = CursorUtil.getColumnIndexOrThrow(_cursor, "clientRef");
          final int _cursorIndexOfDeliveryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryDate");
          final int _cursorIndexOfCascos = CursorUtil.getColumnIndexOrThrow(_cursor, "cascos");
          final int _cursorIndexOfPuertas = CursorUtil.getColumnIndexOrThrow(_cursor, "puertas");
          final int _cursorIndexOfTerminada = CursorUtil.getColumnIndexOrThrow(_cursor, "terminada");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final List<KitchenOrderEntity> _result = new ArrayList<KitchenOrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final KitchenOrderEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpClientRef;
            if (_cursor.isNull(_cursorIndexOfClientRef)) {
              _tmpClientRef = null;
            } else {
              _tmpClientRef = _cursor.getString(_cursorIndexOfClientRef);
            }
            final long _tmpDeliveryDate;
            _tmpDeliveryDate = _cursor.getLong(_cursorIndexOfDeliveryDate);
            final boolean _tmpCascos;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCascos);
            _tmpCascos = _tmp != 0;
            final boolean _tmpPuertas;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfPuertas);
            _tmpPuertas = _tmp_1 != 0;
            final boolean _tmpTerminada;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfTerminada);
            _tmpTerminada = _tmp_2 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item = new KitchenOrderEntity(_tmpId,_tmpClientRef,_tmpDeliveryDate,_tmpCascos,_tmpPuertas,_tmpTerminada,_tmpNotes,_tmpCategory);
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
  public Flow<KitchenOrderEntity> getById(final int id) {
    final String _sql = "SELECT * FROM kitchen_orders WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"kitchen_orders"}, new Callable<KitchenOrderEntity>() {
      @Override
      @Nullable
      public KitchenOrderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfClientRef = CursorUtil.getColumnIndexOrThrow(_cursor, "clientRef");
          final int _cursorIndexOfDeliveryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryDate");
          final int _cursorIndexOfCascos = CursorUtil.getColumnIndexOrThrow(_cursor, "cascos");
          final int _cursorIndexOfPuertas = CursorUtil.getColumnIndexOrThrow(_cursor, "puertas");
          final int _cursorIndexOfTerminada = CursorUtil.getColumnIndexOrThrow(_cursor, "terminada");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final KitchenOrderEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpClientRef;
            if (_cursor.isNull(_cursorIndexOfClientRef)) {
              _tmpClientRef = null;
            } else {
              _tmpClientRef = _cursor.getString(_cursorIndexOfClientRef);
            }
            final long _tmpDeliveryDate;
            _tmpDeliveryDate = _cursor.getLong(_cursorIndexOfDeliveryDate);
            final boolean _tmpCascos;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCascos);
            _tmpCascos = _tmp != 0;
            final boolean _tmpPuertas;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfPuertas);
            _tmpPuertas = _tmp_1 != 0;
            final boolean _tmpTerminada;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfTerminada);
            _tmpTerminada = _tmp_2 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _result = new KitchenOrderEntity(_tmpId,_tmpClientRef,_tmpDeliveryDate,_tmpCascos,_tmpPuertas,_tmpTerminada,_tmpNotes,_tmpCategory);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
