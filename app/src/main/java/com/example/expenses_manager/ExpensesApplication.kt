package com.example.expenses_manager

import android.app.Application
import com.example.expenses_manager.data.AppDatabase
import com.example.expenses_manager.data.ExpensesRepository

class ExpensesApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ExpensesRepository(database.expenseDao()) }
}
