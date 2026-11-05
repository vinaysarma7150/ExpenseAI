package com.mvsss.expenseai.ui.history

import androidx.lifecycle.ViewModel
import com.mvsss.expenseai.data.DummyReceipts
import com.mvsss.expenseai.data.model.Receipt
import com.mvsss.expenseai.data.model.ReceiptCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HistoryUiState(
    val query: String = "",
    val selectedFilter: String = "All",
    val filters: List<String> = listOf("All", "Groceries", "Food", "Shopping", "Transport", "Other"),
    val totalAmount: String = DummyReceipts.historyTotal,
    val receipts: List<Receipt> = DummyReceipts.receipts
)

class HistoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
        applyFilters()
    }

    fun onFilterSelected(filter: String) {
        _uiState.update { it.copy(selectedFilter = filter) }
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        val filtered = DummyReceipts.receipts.filter { receipt ->
            val matchesQuery = state.query.isBlank() ||
                receipt.merchant.contains(state.query, ignoreCase = true) ||
                receipt.category.label.contains(state.query, ignoreCase = true)
            val matchesFilter = when (state.selectedFilter) {
                "All" -> true
                "Transport" -> receipt.category == ReceiptCategory.Fuel
                else -> receipt.category.label.equals(state.selectedFilter, ignoreCase = true)
            }
            matchesQuery && matchesFilter
        }
        _uiState.update { it.copy(receipts = filtered) }
    }
}
