package com.mvsss.expenseai.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : NavKey

@Serializable
data object HistoryRoute : NavKey

@Serializable
data object SettingsRoute : NavKey

@Serializable
data object ScanRoute : NavKey

@Serializable
data object ProcessingRoute : NavKey

@Serializable
data class ReceiptDetailsRoute(val receiptId: String) : NavKey

val TopLevelRoutes: Set<NavKey> = setOf(HomeRoute, HistoryRoute, SettingsRoute)
