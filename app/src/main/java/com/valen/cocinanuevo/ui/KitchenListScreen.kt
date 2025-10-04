package com.valen.cocinanuevo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valen.cocinanuevo.data.KitchenOrderEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitchenListScreen(
    category: String,
    onNavigateBack: () -> Unit,
    onEdit: (Int) -> Unit,
    onDelete: (KitchenOrderEntity) -> Unit,
    vm: KitchenViewModel = viewModel(factory = KitchenViewModel.factory(LocalContext.current))
) {
    val orders by vm.ordersByCategory(category.uppercase()).collectAsState(initial = emptyList())

    var query by remember { mutableStateOf("") }

    val filtered = remember(orders, query) {
        if (query.isBlank()) orders
        else {
            val q = query.trim().lowercase(Locale.getDefault())
            orders.filter { o ->
                (o.clientRef.ifBlank { "" }.lowercase(Locale.getDefault()).contains(q))
                        || (o.notes.ifBlank { "" }.lowercase(Locale.getDefault()).contains(q))
                        || (o.category.ifBlank { "" }.lowercase(Locale.getDefault()).contains(q))
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("LISTADO DE COCINAS", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF1976D2))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                placeholder = { Text("Buscar por cliente, notas o categoría") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Borrar")
                        }
                    }
                },
                singleLine = true,
                // No usamos KeyboardOptions para evitar problemas de import/resolución.
            )

            if (filtered.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay resultados", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(filtered) { item ->
                        KitchenCard(
                            order = item,
                            onEdit = { onEdit(item.id) },
                            onDelete = { onDelete(item) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

/** Formato de fecha local para mostrar deliveryDate (ms epoch) */
private fun formatDateShort(epochMillis: Long): String {
    return try {
        val fmt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        fmt.format(Date(epochMillis))
    } catch (e: Exception) {
        epochMillis.toString()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitchenCard(
    order: KitchenOrderEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = order.clientRef.ifBlank { "Sin referencia" },
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Entrega: ${formatDateShort(order.deliveryDate)}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Categoría: ${order.category}",
                    style = MaterialTheme.typography.bodySmall
                )
                if (order.notes.isNotBlank()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = order.notes,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = onEdit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")
                }
            }
        }
    }
}
