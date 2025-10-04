package com.valen.cocinanuevo.data;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class KitchenDatabase_Impl extends KitchenDatabase {
  private volatile KitchenDao _kitchenDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `kitchen_orders` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `clientRef` TEXT NOT NULL, `deliveryDate` INTEGER NOT NULL, `cascos` INTEGER NOT NULL, `puertas` INTEGER NOT NULL, `terminada` INTEGER NOT NULL, `notes` TEXT NOT NULL, `category` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '59cc05e2ea6085381eeb17d9ae04c58a')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `kitchen_orders`");
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
        final HashMap<String, TableInfo.Column> _columnsKitchenOrders = new HashMap<String, TableInfo.Column>(8);
        _columnsKitchenOrders.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKitchenOrders.put("clientRef", new TableInfo.Column("clientRef", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKitchenOrders.put("deliveryDate", new TableInfo.Column("deliveryDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKitchenOrders.put("cascos", new TableInfo.Column("cascos", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKitchenOrders.put("puertas", new TableInfo.Column("puertas", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKitchenOrders.put("terminada", new TableInfo.Column("terminada", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKitchenOrders.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKitchenOrders.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysKitchenOrders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesKitchenOrders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoKitchenOrders = new TableInfo("kitchen_orders", _columnsKitchenOrders, _foreignKeysKitchenOrders, _indicesKitchenOrders);
        final TableInfo _existingKitchenOrders = TableInfo.read(db, "kitchen_orders");
        if (!_infoKitchenOrders.equals(_existingKitchenOrders)) {
          return new RoomOpenHelper.ValidationResult(false, "kitchen_orders(com.valen.cocinanuevo.data.KitchenOrderEntity).\n"
                  + " Expected:\n" + _infoKitchenOrders + "\n"
                  + " Found:\n" + _existingKitchenOrders);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "59cc05e2ea6085381eeb17d9ae04c58a", "261df2d31aa3294473fd2796afd216fd");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "kitchen_orders");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `kitchen_orders`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
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
    _typeConvertersMap.put(KitchenDao.class, KitchenDao_KitchenDatabase_Impl.getRequiredConverters());
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
  public KitchenDao kitchenDao() {
    if (_kitchenDao != null) {
      return _kitchenDao;
    } else {
      synchronized(this) {
        if(_kitchenDao == null) {
          _kitchenDao = new KitchenDao_KitchenDatabase_Impl(this);
        }
        return _kitchenDao;
      }
    }
  }
}
