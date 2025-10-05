package com.valen.cocinanuevo

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
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
        private const val TAG = "NotifDebug"
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        Log.i(TAG, "POST_NOTIFICATIONS permission result: $isGranted")
        if (!isGranted) {
            android.widget.Toast.makeText(this, "Permiso de notificaciones no concedido. Activa en ajustes.", android.widget.Toast.LENGTH_LONG).show()
        } else {
            // permiso concedido; si quieres que haga algo adicional tras concederlo, lo añadimos aquí
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Solicitar permiso POST_NOTIFICATIONS en Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            val granted = ContextCompat.checkSelfPermission(this, permission) == android.content.pm.PackageManager.PERMISSION_GRANTED
            Log.i(TAG, "POST_NOTIFICATIONS granted=$granted")
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
