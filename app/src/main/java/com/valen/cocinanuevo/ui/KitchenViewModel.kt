package com.valen.cocinanuevo.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.valen.cocinanuevo.data.KitchenDatabase
import com.valen.cocinanuevo.data.KitchenOrderEntity
import com.valen.cocinanuevo.notifications.AlarmScheduler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class KitchenViewModel(private val db: KitchenDatabase) : ViewModel() {

    private val TAG = "KitchenViewModel"

    fun ordersByCategory(category: String): Flow<List<KitchenOrderEntity>> =
        if (category == "TODAS") db.kitchenDao().getAll()
        else db.kitchenDao().getByCategory(category)

    fun getById(id: Int): Flow<KitchenOrderEntity?> =
        db.kitchenDao().getById(id)

    fun insertAndSchedule(order: KitchenOrderEntity, triggerMillis: Long, context: Context) = viewModelScope.launch {
        try {
            val rowId = db.kitchenDao().insert(order) // devuelve Long
            Log.i(TAG, "insertAndSchedule: rowId=$rowId")
            if (rowId != -1L) {
                AlarmScheduler.schedule(context.applicationContext, rowId, triggerMillis)
                Log.i(TAG, "Alarm scheduled for inserted order rowId=$rowId")
            } else {
                Log.w(TAG, "insertAndSchedule: insert devolvió -1, no se programó alarma")
            }
        } catch (t: Throwable) {
            Log.e(TAG, "insertAndSchedule: error insertando o programando alarma", t)
        }
    }

    fun insert(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().insert(order)
    }

    fun update(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().update(order)
    }

    fun updateAndSchedule(order: KitchenOrderEntity, triggerMillis: Long, context: Context) = viewModelScope.launch {
        try {
            db.kitchenDao().update(order)
            val idLong = order.id.toLong()
            if (idLong > 0L) {
                AlarmScheduler.cancel(context.applicationContext, idLong)
                AlarmScheduler.schedule(context.applicationContext, idLong, triggerMillis)
            } else {
                Log.w(TAG, "updateAndSchedule: order.id inválido. No se reprogramó alarma.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error reprogramando alarma en updateAndSchedule", e)
        }
    }

    fun delete(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().delete(order)
    }

    fun deleteAndCancel(order: KitchenOrderEntity, context: Context) = viewModelScope.launch {
        try {
            val idLong = order.id.toLong()
            if (idLong > 0L) {
                AlarmScheduler.cancel(context.applicationContext, idLong)
            } else {
                Log.w(TAG, "deleteAndCancel: order.id inválido. No se canceló alarma.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error cancelando alarma en deleteAndCancel", e)
        } finally {
            db.kitchenDao().delete(order)
        }
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
