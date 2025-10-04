package com.valen.cocinanuevo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("UNICO COCINAS", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            }
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            val buttonModifier = Modifier.fillMaxWidth().height(80.dp).padding(vertical = 10.dp)

            Button(onClick = { onNavigate("form/TODAS") }, modifier = buttonModifier,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))) {
                Text("AÑADIR COCINA", fontSize = 18.sp, color = Color.White)
            }
            Button(onClick = { onNavigate("list/TODAS") }, modifier = buttonModifier,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))) {
                Text("LISTADO DE COCINAS", fontSize = 18.sp, color = Color.White)
            }
            Button(onClick = { onNavigate("list/ARMADA") }, modifier = buttonModifier,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))) {
                Text("COCINAS ARMADAS", fontSize = 18.sp, color = Color.White)
            }
            Button(onClick = { onNavigate("list/TERMINACIONES") }, modifier = buttonModifier,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))) {
                Text("TERMINACIONES", fontSize = 18.sp, color = Color.White)
            }
            Button(onClick = { onNavigate("list/ENTREGA") }, modifier = buttonModifier,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))) {
                Text("ENTREGA", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
