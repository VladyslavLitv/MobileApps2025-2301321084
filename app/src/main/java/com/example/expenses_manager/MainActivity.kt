package com.example.expenses_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expenses_manager.ui.AddExpenseScreen
import com.example.expenses_manager.ui.ExpensesViewModel
import com.example.expenses_manager.ui.ExpensesViewModelFactory
import com.example.expenses_manager.ui.HomeScreen
import com.example.expenses_manager.ui.theme.Expenses_ManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Expenses_ManagerTheme {
                ExpensesApp()
            }
        }
    }
}

@Composable
fun ExpensesApp() {
    val navController = rememberNavController()
    val application = (androidx.compose.ui.platform.LocalContext.current.applicationContext as ExpensesApplication)
    val viewModel: ExpensesViewModel = viewModel(
        factory = ExpensesViewModelFactory(application.repository)
    )

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToAdd = { navController.navigate("add_expense") },
                viewModel = viewModel
            )
        }
        composable("add_expense") {
            AddExpenseScreen(
                onNavigateBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}
