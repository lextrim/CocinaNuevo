package com.valen.cocinanuevo.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDateTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
