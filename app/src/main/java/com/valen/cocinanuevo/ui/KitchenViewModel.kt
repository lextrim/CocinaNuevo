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

/**
 * ViewModel para gestionar pedidos.
 *
 * Añade funciones nuevas:
 * - insertAndSchedule(order, triggerMillis, context)
 * - updateAndSchedule(order, triggerMillis, context)
 * - deleteAndCancel(order, context)
 *
 * Estas funciones programan / reprograman / cancelan la notificación usando AlarmScheduler.
 *
 * Suposición: KitchenOrderEntity tiene una propiedad `id` (tipo Int o Long) que identifica el pedido.
 * Si el nombre del id en la entidad es distinto cambia `order.id` por el campo correcto.
 */
class KitchenViewModel(private val db: KitchenDatabase) : ViewModel() {

    fun ordersByCategory(category: String): Flow<List<KitchenOrderEntity>> =
        if (category == "TODAS") db.kitchenDao().getAll()
        else db.kitchenDao().getByCategory(category)

    fun getById(id: Int): Flow<KitchenOrderEntity?> =
        db.kitchenDao().getById(id)

    /**
     * Inserta sin programar notificación.
     * (mantengo la función original para compatibilidad)
     */
    fun insert(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().insert(order)
    }

    /**
     * Inserta y programa notificación para triggerMillis (epoch millis).
     * IMPORTANT: order.id debe contener el id correcto. Si tu DAO genera el id al insertar
     * y no lo updates en el objeto `order`, programa la notificación desde la capa UI después
     * de obtener el id generado.
     */
    fun insertAndSchedule(order: KitchenOrderEntity, triggerMillis: Long, context: Context) = viewModelScope.launch {
        db.kitchenDao().insert(order)
        try {
            val idLong = try { (order.id as? Long) ?: (order.id as? Int)?.toLong() ?: -1L } catch (e: Exception) { -1L }
            if (idLong > 0L) {
                AlarmScheduler.schedule(context.applicationContext, idLong, triggerMillis)
            } else {
                Log.w("KitchenViewModel", "insertAndSchedule: order.id inválido. Si tu DAO devuelve id generado, programa la alarma desde UI con el id devuelto.")
            }
        } catch (e: Exception) {
            Log.e("KitchenViewModel", "Error programando alarma en insertAndSchedule", e)
        }
    }

    /**
     * Actualiza sin cambiar notificación.
     */
    fun update(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().update(order)
    }

    /**
     * Actualiza y reprograma notificación.
     * Primero cancela la alarma previa (por si existe) y luego programa la nueva.
     */
    fun updateAndSchedule(order: KitchenOrderEntity, triggerMillis: Long, context: Context) = viewModelScope.launch {
        db.kitchenDao().update(order)
        try {
            val idLong = try { (order.id as? Long) ?: (order.id as? Int)?.toLong() ?: -1L } catch (e: Exception) { -1L }
            if (idLong > 0L) {
                AlarmScheduler.cancel(context.applicationContext, idLong)
                AlarmScheduler.schedule(context.applicationContext, idLong, triggerMillis)
            } else {
                Log.w("KitchenViewModel", "updateAndSchedule: order.id inválido. No se reprogramó alarma.")
            }
        } catch (e: Exception) {
            Log.e("KitchenViewModel", "Error reprogramando alarma en updateAndSchedule", e)
        }
    }

    /**
     * Borra pedido y cancela la alarma asociada.
     */
    fun delete(order: KitchenOrderEntity) = viewModelScope.launch {
        db.kitchenDao().delete(order)
    }

    fun deleteAndCancel(order: KitchenOrderEntity, context: Context) = viewModelScope.launch {
        try {
            val idLong = try { (order.id as? Long) ?: (order.id as? Int)?.toLong() ?: -1L } catch (e: Exception) { -1L }
            if (idLong > 0L) {
                AlarmScheduler.cancel(context.applicationContext, idLong)
            } else {
                Log.w("KitchenViewModel", "deleteAndCancel: order.id inválido. No se canceló alarma.")
            }
        } catch (e: Exception) {
            Log.e("KitchenViewModel", "Error cancelando alarma en deleteAndCancel", e)
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
