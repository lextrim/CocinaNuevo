
package com.valen.cocinanuevo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.valen.cocinanuevo.ui.AppRoot
import com.valen.cocinanuevo.ui.theme.CocinaNuevoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocinaNuevoTheme {
                AppRoot()
            }
        }
    }
}
