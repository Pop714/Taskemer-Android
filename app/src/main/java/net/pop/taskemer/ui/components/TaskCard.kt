package net.pop.taskemer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.screens.task.TaskStatus
import net.pop.taskemer.ui.screens.task.TaskUiModel
import net.pop.taskemer.ui.theme.*

@Composable
fun TaskCard(task: TaskUiModel) {
    Surface(
        color = TaskemerSurface,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Top Row: Status Pill + Updated Time + Icons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    StatusPill(status = task.status)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = task.lastUpdated,
                        color = TaskemerTextSecondary,
                        fontSize = 11.sp,
                        fontFamily = Rubik
                    )
                }

                // Media Icons
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (task.hasVoiceNote) {
                        Icon(Icons.Default.Mic, contentDescription = "Voice Note", tint = TaskemerTextSecondary, modifier = Modifier.size(16.dp))
                    }
                    if (task.hasAttachment) {
                        Icon(Icons.Default.Attachment, contentDescription = "Attachment", tint = TaskemerTextSecondary, modifier = Modifier.size(16.dp))
                    }
                    if (task.status == TaskStatus.ARCHIVED) {
                        Icon(Icons.Default.Warning, contentDescription = "Warning", tint = TaskemerTextSecondary, modifier = Modifier.size(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Task Title
            Text(
                text = task.title,
                color = TaskemerTextPrimary,
                fontSize = 16.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp
            )
        }
    }
}