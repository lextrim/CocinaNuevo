package com.valen.cocinanuevo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.valen.cocinanuevo.ui.KitchenFormScreen
import com.valen.cocinanuevo.ui.theme.CocinaNuevoTheme

class OrderEditActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orderId = intent?.getIntExtra(MainActivity.EXTRA_ORDER_ID, -1) ?: -1

        setContent {
            CocinaNuevoTheme {
                KitchenFormScreen(
                    category = "TODAS",
                    orderId = if (orderId > 0) orderId else null,
                    onNavigateBack = { finish() }
                )
            }
        }
    }
}
