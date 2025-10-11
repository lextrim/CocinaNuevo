package com.valen.cocinanuevo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "KitchenOrderEntity")
data class KitchenOrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val clientRef: String = "",
    val deliveryDate: Long = 0L,
    val cascos: Boolean = false,
    val puertas: Boolean = false,
    val terminada: Boolean = false,
    val notes: String = "",
    val category: String = "TODAS"
)
