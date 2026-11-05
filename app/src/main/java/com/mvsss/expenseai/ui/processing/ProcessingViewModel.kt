package com.mvsss.expenseai.ui.processing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvsss.expenseai.data.DummyReceipts
import com.mvsss.expenseai.data.model.ProcessingStep
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProcessingUiState(
    val progressPercent: Int = 75,
    val stepLabel: String = "Step 3 of 4",
    val merchantPreview: String = "Supermart • $48.20",
    val headline: String = "Analyzing your receipt",
    val body: String = "Reading and understanding the receipt with private on-device intelligence",
    val steps: List<ProcessingStep> = DummyReceipts.processingSteps,
    val categoryPreview: String = "Groceries & Household Supplies",
    val confidence: String = "99.4%",
    val isComplete: Boolean = false
)

class ProcessingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProcessingUiState())
    val uiState: StateFlow<ProcessingUiState> = _uiState.asStateFlow()

    init {
        // UI-only auto-advance so the flow feels complete without backend.
        viewModelScope.launch {
            delay(2200)
            _uiState.update { it.copy(isComplete = true, progressPercent = 100) }
        }
    }
}
