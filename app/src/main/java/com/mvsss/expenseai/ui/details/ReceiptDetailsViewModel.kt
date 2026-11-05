package com.mvsss.expenseai.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvsss.expenseai.data.DummyReceipts
import com.mvsss.expenseai.data.model.Receipt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ReceiptDetailsUiState(
    val receipt: Receipt
)

class ReceiptDetailsViewModel(
    receiptId: String
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        ReceiptDetailsUiState(receipt = DummyReceipts.findById(receiptId))
    )
    val uiState: StateFlow<ReceiptDetailsUiState> = _uiState.asStateFlow()

    companion object {
        fun factory(receiptId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ReceiptDetailsViewModel(receiptId) as T
                }
            }
    }
}
