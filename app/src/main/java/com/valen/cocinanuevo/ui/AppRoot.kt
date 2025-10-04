package com.valen.cocinanuevo.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val vm: KitchenViewModel = viewModel(factory = KitchenViewModel.factory(LocalContext.current))

    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        composable("menu") {
            MenuScreen(onNavigate = { route -> navController.navigate(route) })
        }

        composable("list/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "TODAS"
            KitchenListScreen(
                category = category,
                onNavigateBack = { navController.popBackStack() },
                onEdit = { id -> navController.navigate("form/$category?id=$id") },
                onDelete = { order -> vm.delete(order) },
                vm = vm
            )
        }

        // 🔹 ruta corregida para soportar ?id=
        composable(
            route = "form/{category}?id={id}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "TODAS"
            val orderId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            KitchenFormScreen(
                category = category,
                orderId = orderId,
                onNavigateBack = { navController.popBackStack() },
                vm = vm
            )
        }
    }
}
