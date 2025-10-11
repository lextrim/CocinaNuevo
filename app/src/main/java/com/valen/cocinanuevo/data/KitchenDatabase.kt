package com.valen.cocinanuevo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KitchenOrderEntity::class], version = 1, exportSchema = false)
abstract class KitchenDatabase : RoomDatabase() {
    abstract fun kitchenDao(): KitchenDao

    companion object {
        @Volatile
        private var INSTANCE: KitchenDatabase? = null

        fun getDatabase(context: Context): KitchenDatabase {
            return INSTANCE ?: synchronized(this) {
                val inst = Room.databaseBuilder(
                    context.applicationContext,
                    KitchenDatabase::class.java,
                    "kitchen_db"
                )
                    // durante desarrollo esto recrea la base si cambias la entidad/version
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = inst
                inst
            }
        }
    }
}
