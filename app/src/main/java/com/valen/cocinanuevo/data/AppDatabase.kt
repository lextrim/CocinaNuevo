package com.valen.cocinanuevo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [KitchenOrderEntity::class],
    version = 2, // 🔥 actualizado de 1 a 2
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun kitchenDao(): KitchenDao
}
