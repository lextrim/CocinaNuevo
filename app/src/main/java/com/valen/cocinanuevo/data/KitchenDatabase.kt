package com.valen.cocinanuevo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [KitchenOrderEntity::class],
    version = 4, // subido tras quitar el campo 'entrega'
    exportSchema = false
)
abstract class KitchenDatabase : RoomDatabase() {
    abstract fun kitchenDao(): KitchenDao

    companion object {
        @Volatile private var INSTANCE: KitchenDatabase? = null
        fun getDatabase(context: Context): KitchenDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KitchenDatabase::class.java,
                    "kitchen_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
