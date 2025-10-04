package com.valen.cocinanuevo.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.valen.cocinanuevo.data.KitchenDatabase
import com.valen.cocinanuevo.data.KitchenOrderEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



class KitchenViewModel(private val db: KitchenDatabase) : ViewModel() {

    fun ordersByCategory(category: String): Flow<List<KitchenOrderEntity>> =
        if (category == "TODAS") db.kitchenDao().getAll()
        else db.kitchenDao().getByCategory(category)

    fun getById(id: Int): Flow<KitchenOrderEntity?> =
        db.kitchenDao().getById(id)

    fun insert(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().insert(order)
    }

    fun update(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().update(order)
    }

    fun delete(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().delete(order)
    }

    companion object {
        fun factory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = KitchenDatabase.getDatabase(context)
                    return KitchenViewModel(db) as T
                }
            }
    }
}
