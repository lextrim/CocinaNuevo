package com.valen.cocinanuevo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kitchen_orders")
data class KitchenOrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clientRef: String,
    val deliveryDate: Long,
    val cascos: Boolean = false,
    val puertas: Boolean = false,
    val terminada: Boolean = false,
    val notes: String = "",
    val category: String = "TODAS"
)
