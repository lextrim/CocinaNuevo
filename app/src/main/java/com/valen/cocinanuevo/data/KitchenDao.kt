package com.valen.cocinanuevo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface KitchenDao {

    @Query("SELECT * FROM KitchenOrderEntity ORDER BY deliveryDate ASC")
    fun getAll(): Flow<List<KitchenOrderEntity>>

    @Query("SELECT * FROM KitchenOrderEntity WHERE category = :category ORDER BY deliveryDate ASC")
    fun getByCategory(category: String): Flow<List<KitchenOrderEntity>>

    @Query("SELECT * FROM KitchenOrderEntity WHERE id = :id LIMIT 1")
    fun getById(id: Int): Flow<KitchenOrderEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: KitchenOrderEntity): Long

    @Update
    suspend fun update(order: KitchenOrderEntity)

    @Delete
    suspend fun delete(order: KitchenOrderEntity)
}
