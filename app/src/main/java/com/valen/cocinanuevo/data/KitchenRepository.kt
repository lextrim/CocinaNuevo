
package com.valen.cocinanuevo.data

import kotlinx.coroutines.flow.Flow

class KitchenRepository(private val dao: KitchenDao) {
    fun getAll(): Flow<List<KitchenOrderEntity>> = dao.getAll()
    fun getByCategory(category: String): Flow<List<KitchenOrderEntity>> = dao.getByCategory(category)
    suspend fun insert(order: KitchenOrderEntity) = dao.insert(order)
}
