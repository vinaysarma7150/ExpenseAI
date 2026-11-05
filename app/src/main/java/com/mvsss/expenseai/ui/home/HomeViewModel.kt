package com.mvsss.expenseai.ui.home

import androidx.lifecycle.ViewModel
import com.mvsss.expenseai.data.DummyReceipts
import com.mvsss.expenseai.data.model.Receipt
import com.mvsss.expenseai.data.model.SpendingSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val summary: SpendingSummary = DummyReceipts.spendingSummary,
    val recentReceipts: List<Receipt> = DummyReceipts.recentReceipts()
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}
