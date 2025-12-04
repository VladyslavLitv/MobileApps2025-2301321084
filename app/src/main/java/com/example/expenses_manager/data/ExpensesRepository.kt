package com.example.expenses_manager.data

import kotlinx.coroutines.flow.Flow

class ExpensesRepository(private val expenseDao: ExpenseDao) {
    val allExpenses: Flow<List<Expense>> = expenseDao.getAllExpenses()

    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }
}
