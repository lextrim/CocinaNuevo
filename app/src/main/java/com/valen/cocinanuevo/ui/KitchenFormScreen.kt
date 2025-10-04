package com.valen.cocinanuevo.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valen.cocinanuevo.data.KitchenOrderEntity
import com.valen.cocinanuevo.util.formatDateTime
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitchenFormScreen(
    category: String,
    orderId: Int? = null,
    onNavigateBack: () -> Unit,
    vm: KitchenViewModel = viewModel(factory = KitchenViewModel.factory(LocalContext.current))
) {
    val context = LocalContext.current

    // Observa el registro si hay id
    val order =
        if (orderId != null) vm.getById(orderId).collectAsState(initial = null).value
        else null

    var clientRef by remember { mutableStateOf("") }
    var deliveryDate by remember { mutableStateOf(System.currentTimeMillis()) }
    var cascos by remember { mutableStateOf(false) }
    var puertas by remember { mutableStateOf(false) }
    var terminada by remember { mutableStateOf(false) } // sin UI, conserva valor al editar
    var notes by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(category.uppercase()) }

    val categorias = listOf("TODAS", "ARMADA", "TERMINACIONES", "ENTREGA")
    var expanded by remember { mutableStateOf(false) }
    var loaded by remember(orderId) { mutableStateOf(false) }

    // Carga una sola vez cuando llega el registro
    LaunchedEffect(order?.id) {
        if (!loaded && order != null) {
            clientRef = order.clientRef
            deliveryDate = order.deliveryDate
            cascos = order.cascos
            puertas = order.puertas
            terminada = order.terminada
            notes = order.notes
            selectedCategory = order.category.uppercase()
            loaded = true
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (orderId == null) "NUEVA COCINA" else "EDITAR COCINA", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "VOLVER", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val newOrder = KitchenOrderEntity(
                        id = orderId ?: 0,
                        clientRef = clientRef.uppercase(),
                        deliveryDate = deliveryDate,
                        cascos = cascos,
                        puertas = puertas,
                        terminada = terminada, // se mantiene
                        notes = notes.uppercase(),
                        category = selectedCategory.uppercase()
                    )
                    if (orderId == null) vm.insert(newOrder) else vm.update(newOrder)
                    onNavigateBack()
                },
                containerColor = Color(0xFF1976D2)
            ) { Icon(Icons.Filled.Check, contentDescription = "GUARDAR", tint = Color.White) }
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = clientRef,
                onValueChange = { clientRef = it.uppercase() },
                label = { Text("CLIENTE", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val calendar = Calendar.getInstance().apply { timeInMillis = deliveryDate }
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            TimePickerDialog(
                                context,
                                { _, hour, minute ->
                                    calendar.set(year, month, dayOfMonth, hour, minute)
                                    deliveryDate = calendar.timeInMillis
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true
                            ).show()
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier.fillMaxWidth()
            ) { Text(formatDateTime(deliveryDate), color = Color.White) }

            Spacer(modifier = Modifier.height(12.dp))

            SwitchWithLabel("CASCOS", cascos) { cascos = it }
            SwitchWithLabel("PUERTAS", puertas) { puertas = it }
            // Quitado: Switch “TERMINADA”

            Spacer(modifier = Modifier.height(12.dp))

            // Categoría con ExposedDropdownMenuBox (estable)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("CATEGORÍA", color = Color.White) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(color = Color.White),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categorias.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = {
                                selectedCategory = cat.uppercase()
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it.uppercase() },
                label = { Text("NOTAS", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.Red)
            )
        }
    }
}

@Composable
fun SwitchWithLabel(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.White)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.Red,
                checkedTrackColor = Color.Green.copy(alpha = 0.5f),
                uncheckedTrackColor = Color.Red.copy(alpha = 0.5f)
            )
        )
    }
}
