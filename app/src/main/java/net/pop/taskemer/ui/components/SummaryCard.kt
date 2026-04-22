package net.pop.taskemer.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.screens.home.HomeState
import net.pop.taskemer.ui.theme.TaskemerPrimary
import net.pop.taskemer.ui.theme.TaskemerSurface
import net.pop.taskemer.ui.theme.TaskemerTextSecondary

@Composable
fun SummaryCard(state: HomeState) {
    if (state.totalProjects == 0) {
        Surface(
            color = TaskemerSurface,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "You don't have any active projects right now. Time to build something new.",
                color = TaskemerTextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(24.dp)
            )
        }
    } else {
        Text(
            text = buildAnnotatedString {
                append("You have ")
                withStyle(style = SpanStyle(color = TaskemerPrimary, fontWeight = FontWeight.Bold)) {
                    append("${state.activeTasks} tasks")
                }
                append(" to complete today across ")
                withStyle(style = SpanStyle(color = TaskemerPrimary, fontWeight = FontWeight.Bold)) {
                    append("${state.activeProjects} active projects")
                }
                append(" and ${state.archivedProjects} archived.")
            },
            color = TaskemerTextSecondary,
            fontSize = 15.sp,
            lineHeight = 22.sp
        )
    }
}