package com.mvsss.expenseai.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material.icons.outlined.LocalGroceryStore
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector

enum class ReceiptCategory(val label: String, val icon: ImageVector) {
    Groceries("Groceries", Icons.Outlined.LocalGroceryStore),
    Dining("Dining", Icons.Outlined.Restaurant),
    Food("Food", Icons.Outlined.Restaurant),
    Fuel("Fuel", Icons.Outlined.LocalGasStation),
    Shopping("Shopping", Icons.Outlined.ShoppingBag),
    Health("Health", Icons.Outlined.Medication),
    Other("Other", Icons.Outlined.Devices)
}

data class LineItem(
    val name: String,
    val quantityLabel: String,
    val unitPrice: String,
    val totalPrice: String,
    val iconHint: String = "inventory"
)

data class Receipt(
    val id: String,
    val merchant: String,
    val location: String,
    val dateLabel: String,
    val timeLabel: String,
    val category: ReceiptCategory,
    val amount: String,
    val confidencePercent: Int,
    val itemsCount: Int,
    val savingsLabel: String? = null,
    val lineItems: List<LineItem> = emptyList(),
    val subtotal: String = "",
    val discount: String? = null,
    val taxLabel: String = "",
    val taxAmount: String = "",
    val paymentMethod: String = "",
    val receiptNumber: String = ""
)

data class SpendingSummary(
    val periodLabel: String,
    val totalAmount: String,
    val receiptCount: Int,
    val monthContext: String
)

enum class ThemePreference {
    System, Light, Dark
}

data class ProcessingStep(
    val title: String,
    val subtitle: String,
    val status: StepStatus
)

enum class StepStatus {
    Done, Active, Queued
}
