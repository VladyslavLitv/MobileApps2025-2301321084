package com.example.expenses_manager

import com.example.expenses_manager.data.Expense
import com.example.expenses_manager.data.ExpensesRepository
import com.example.expenses_manager.ui.ExpensesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ExpensesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: ExpensesRepository
    private lateinit var viewModel: ExpensesViewModel

    @Before
    fun setup() {
        repository = mock()
        whenever(repository.allExpenses).thenReturn(flowOf(emptyList()))
        viewModel = ExpensesViewModel(repository)
    }

    @Test
    fun addExpense_callsRepositoryInsert() = runTest {
        val title = "Lunch"
        val amount = 15.0

        viewModel.addExpense(title, amount)

        verify(repository).insertExpense(any())
    }

    @Test
    fun deleteExpense_callsRepositoryDelete() = runTest {
        val expense = Expense(id = 1, title = "Coffee", amount = 5.0)

        viewModel.deleteExpense(expense)

        verify(repository).deleteExpense(expense)
    }
    
    @Test
    fun initialState_isEmpty() = runTest {
        val expenses = viewModel.allExpenses.value
        assertEquals(emptyList<Expense>(), expenses)
    }
}
