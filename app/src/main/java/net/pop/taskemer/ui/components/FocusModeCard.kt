package net.pop.taskemer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.theme.Rubik
import net.pop.taskemer.ui.theme.TaskemerBackground
import net.pop.taskemer.ui.theme.TaskemerPrimary
import net.pop.taskemer.ui.theme.TaskemerTextPrimary
import net.pop.taskemer.ui.theme.TaskemerTextSecondary

@Composable
fun FocusModeCard(
    navigateToFocus: () -> Unit
) {
    Surface(
        color = TaskemerBackground,
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(
            3.dp,
            TaskemerPrimary.copy(alpha = 0.5f)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = TaskemerPrimary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = "Focus",
                    tint = TaskemerPrimary,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Quick Focus Session",
                    color = TaskemerTextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Rubik
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Block notifications and focus on your tasks.",
                    color = TaskemerTextSecondary,
                    fontSize = 12.sp,
                    fontFamily = Rubik
                )

                Spacer(modifier = Modifier.height(12.dp))

                TaskemerPrimaryButton(
                    text = "Start Timer (25m)",
                    onClick = navigateToFocus,
                    modifier = Modifier.height(100.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun FocusPreview() {
    FocusModeCard{}
}