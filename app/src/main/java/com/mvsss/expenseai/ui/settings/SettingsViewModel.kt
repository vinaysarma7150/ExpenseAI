package com.mvsss.expenseai.ui.settings

import androidx.lifecycle.ViewModel
import com.mvsss.expenseai.data.model.ThemePreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SettingsUiState(
    val themePreference: ThemePreference = ThemePreference.System,
    val showClearDialog: Boolean = false,
    val showClearedMessage: Boolean = false,
    val appVersion: String = "1.0.0"
)

class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun onThemeSelected(preference: ThemePreference) {
        _uiState.update { it.copy(themePreference = preference) }
    }

    fun onClearHistoryClick() {
        _uiState.update { it.copy(showClearDialog = true) }
    }

    fun dismissClearDialog() {
        _uiState.update { it.copy(showClearDialog = false) }
    }

    fun confirmClearHistory() {
        _uiState.update {
            it.copy(
                showClearDialog = false,
                showClearedMessage = true
            )
        }
    }

    fun dismissClearedMessage() {
        _uiState.update { it.copy(showClearedMessage = false) }
    }
}
