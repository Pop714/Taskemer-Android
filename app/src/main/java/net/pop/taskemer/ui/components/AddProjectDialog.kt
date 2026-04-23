package net.pop.taskemer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.pop.taskemer.data.local.enums.ProjectPriority
import net.pop.taskemer.data.local.enums.ProjectStatus
import net.pop.taskemer.ui.theme.*

@Composable
fun AddProjectDialog(
    onDismiss: () -> Unit,
    onConfirm: (title: String, desc: String, priority: ProjectPriority, status: ProjectStatus, deadline: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(ProjectPriority.MEDIUM) }
    var deadline by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = TaskemerBackground,
        title = { Text("New Project", color = TaskemerTextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Project Title") },
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = TaskemerPrimary),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = TaskemerPrimary),
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                )
                OutlinedTextField(
                    value = priority.name,
                    onValueChange = { priority = priority },
                    label = { Text("Priority (High, Medium, Low)") },
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = TaskemerPrimary),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = deadline,
                    onValueChange = { deadline = it },
                    label = { Text("Deadline (e.g. Oct 24)") },
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = TaskemerPrimary),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onConfirm(title, description, priority, ProjectStatus.ARCHIVED, deadline)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = TaskemerPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Create", color = TaskemerBackground, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = TaskemerTextSecondary)
            }
        }
    )
}