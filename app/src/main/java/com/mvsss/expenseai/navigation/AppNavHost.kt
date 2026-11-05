package com.mvsss.expenseai.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mvsss.expenseai.ui.components.ReceiptLensBottomBar
import com.mvsss.expenseai.ui.details.ReceiptDetailsScreen
import com.mvsss.expenseai.ui.history.HistoryScreen
import com.mvsss.expenseai.ui.home.HomeScreen
import com.mvsss.expenseai.ui.processing.ProcessingScreen
import com.mvsss.expenseai.ui.scan.ScanScreen
import com.mvsss.expenseai.ui.settings.SettingsScreen

@Composable
fun AppNavHost() {
    val navigationState = rememberNavigationState(
        startRoute = HomeRoute,
        topLevelRoutes = TopLevelRoutes
    )
    val navigator = remember(navigationState) { Navigator(navigationState) }

    val currentRoute = navigationState.backStacks[navigationState.topLevelRoute]?.lastOrNull()
    val showBottomBar = currentRoute in TopLevelRoutes

    val entryProvider = entryProvider {
        entry<HomeRoute> {
            HomeScreen(
                onScanClick = { navigator.navigate(ScanRoute) },
                onViewAllClick = { navigator.navigate(HistoryRoute) },
                onReceiptClick = { id -> navigator.navigate(ReceiptDetailsRoute(id)) }
            )
        }
        entry<HistoryRoute> {
            HistoryScreen(
                onReceiptClick = { id -> navigator.navigate(ReceiptDetailsRoute(id)) }
            )
        }
        entry<SettingsRoute> {
            SettingsScreen()
        }
        entry<ScanRoute> {
            ScanScreen(
                onBack = { navigator.goBack() },
                onCapture = { navigator.navigate(ProcessingRoute) }
            )
        }
        entry<ProcessingRoute> {
            ProcessingScreen(
                onClose = { navigator.goBack() },
                onComplete = {
                    // Leave scan + processing, land on editable receipt details.
                    navigator.goBack()
                    navigator.goBack()
                    navigator.navigate(ReceiptDetailsRoute("dmart"))
                }
            )
        }
        entry<ReceiptDetailsRoute> { key ->
            ReceiptDetailsScreen(
                receiptId = key.receiptId,
                onBack = { navigator.goBack() },
                onRetake = {
                    navigator.goBack()
                    navigator.navigate(ScanRoute)
                }
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                ReceiptLensBottomBar(
                    selectedRoute = navigationState.topLevelRoute,
                    onTabSelected = { navigator.navigate(it) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavDisplay(
                entries = navigationState.toEntries(entryProvider),
                onBack = { navigator.goBack() }
            )
        }
    }
}
