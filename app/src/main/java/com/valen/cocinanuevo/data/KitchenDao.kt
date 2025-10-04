package com.valen.cocinanuevo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface KitchenDao {

    @Query("SELECT * FROM kitchen_orders ORDER BY deliveryDate ASC")
    fun getAll(): Flow<List<KitchenOrderEntity>>

    @Query("SELECT * FROM kitchen_orders WHERE category = :category ORDER BY deliveryDate ASC")
    fun getByCategory(category: String): Flow<List<KitchenOrderEntity>>

    @Query("SELECT * FROM kitchen_orders WHERE id = :id LIMIT 1")
    fun getById(id: Int): Flow<KitchenOrderEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: KitchenOrderEntity)

    @Update
    suspend fun update(order: KitchenOrderEntity)

    @Delete
    suspend fun delete(order: KitchenOrderEntity)
}
