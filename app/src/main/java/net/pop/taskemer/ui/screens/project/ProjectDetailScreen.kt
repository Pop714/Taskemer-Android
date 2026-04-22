package net.pop.taskemer.ui.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.pop.taskemer.ui.components.MetricCard
import net.pop.taskemer.ui.components.TaskCard
import net.pop.taskemer.ui.theme.*

@Composable
fun ProjectDetailScreen(
    viewModel: ProjectDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit // Connect this to your TopBar later
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
        // --- HEADER SECTION ---
        Text(
            text = "ACTIVE PROJECT",
            color = TaskemerTextSecondary,
            fontSize = 11.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = state.projectTitle,
            color = TaskemerTextPrimary,
            fontSize = 32.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = state.projectDescription,
            color = TaskemerTextSecondary,
            fontSize = 14.sp,
            fontFamily = Rubik,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- METRICS GRID ---
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            MetricCard(title = "Tasks", value = "${state.taskCount} Total", modifier = Modifier.weight(1f))
            MetricCard(title = "Deadline", value = state.deadline, valueColor = androidx.compose.ui.graphics.Color(0xFFE040FB), modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            MetricCard(title = "Active Since", value = state.activeDuration, modifier = Modifier.weight(1f))
            MetricCard(title = "Status", value = state.projectStatus, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- TASKS SECTION HEADER ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Project Tasks",
                color = TaskemerTextPrimary,
                fontSize = 20.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { /* Navigate to Create Task */ },
                colors = ButtonDefaults.buttonColors(containerColor = TaskemerPrimary.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("+ NEW TASK", color = TaskemerPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- TASKS LIST ---
        state.tasks.forEach { task ->
            TaskCard(task = task)
        }

        // Extra padding to account for the floating bottom navigation
        Spacer(modifier = Modifier.height(96.dp))
    }
}