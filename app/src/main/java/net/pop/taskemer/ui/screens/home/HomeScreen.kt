package net.pop.taskemer.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.pop.taskemer.ui.components.FocusModeCard
import net.pop.taskemer.ui.components.GreetingSection
import net.pop.taskemer.ui.components.ProductivityPulseChart
import net.pop.taskemer.ui.components.SummaryCard
import net.pop.taskemer.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToFocus: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TaskemerBackground)
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        // 1. Dynamic Greeting
        GreetingSection(username = state.username)

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Summary Card (Handles Empty State)
        SummaryCard(state = state)

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Dummy Progress Chart
        ProductivityPulseChart()

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Focus Mode Card
        FocusModeCard(navigateToFocus = navigateToFocus)

        // Extra padding at bottom to clear the floating navigation bar
        Spacer(modifier = Modifier.height(96.dp))
    }
}