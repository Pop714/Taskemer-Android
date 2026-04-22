package net.pop.taskemer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.screens.task.TaskStatus
import net.pop.taskemer.ui.theme.Rubik
import net.pop.taskemer.ui.theme.TaskemerPrimary

@Composable
fun StatusPill(status: TaskStatus) {
    val (backgroundColor, textColor) = when (status) {
        TaskStatus.ONGOING -> Pair(TaskemerPrimary.copy(alpha = 0.2f), TaskemerPrimary)
        TaskStatus.ENDED -> Pair(Color(0xFFE040FB).copy(alpha = 0.2f), Color(0xFFE040FB))
        TaskStatus.ARCHIVED -> Pair(Color(0xFFFFC107).copy(alpha = 0.2f), Color(0xFFFFC107))
    }

    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = status.label,
            color = textColor,
            fontSize = 9.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
    }
}