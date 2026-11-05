package com.mvsss.expenseai.ui.scan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ScanUiState(
    val hintTitle: String = "Position the receipt inside the frame",
    val hintBody: String = "Make sure the receipt is clear, flat, and well lit.",
    val isLocalOnly: Boolean = true
)

class ScanViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()
}
