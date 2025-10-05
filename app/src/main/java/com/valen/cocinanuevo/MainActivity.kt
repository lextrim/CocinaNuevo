package com.valen.cocinanuevo

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.valen.cocinanuevo.ui.AppRoot
import com.valen.cocinanuevo.ui.theme.CocinaNuevoTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val ACTION_OPEN_ORDER = "com.valen.cocinanuevo.ACTION_OPEN_ORDER"
        const val EXTRA_ORDER_ID = "orderId"
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* no-op */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Solicitar permiso POST_NOTIFICATIONS en Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            val granted = ContextCompat.checkSelfPermission(this, permission) == android.content.pm.PackageManager.PERMISSION_GRANTED
            if (!granted) {
                requestPermissionLauncher.launch(permission)
            }
        }

        setContent {
            CocinaNuevoTheme {
                AppRoot()
            }
        }
    }

    // Abrir ajustes de notificaciones de la app (útil si el usuario las bloqueó)
    fun openAppNotificationSettings() {
        val intent = Intent().apply {
            action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}
