package net.pop.taskemer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.theme.Rubik
import net.pop.taskemer.ui.theme.TaskemerPrimary
import net.pop.taskemer.ui.theme.TaskemerSurface
import net.pop.taskemer.ui.theme.TaskemerTextPrimary
import net.pop.taskemer.ui.theme.TaskemerTextSecondary

@Composable
fun ProductivityPulseChart() {
    Surface(
        color = TaskemerSurface,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PRODUCTIVITY\nPULSE",
                    color = TaskemerTextSecondary,
                    fontSize = 11.sp,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    lineHeight = 14.sp
                )

                Column(horizontalAlignment = Alignment.End) {
                    Text("84", color = TaskemerTextPrimary, fontSize = 28.sp, fontFamily = Rubik, fontWeight = FontWeight.Bold)
                    Text("FOCUS SCORE", color = TaskemerTextSecondary, fontSize = 9.sp, fontFamily = Rubik, letterSpacing = 1.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                val barHeights = listOf(0.4f, 0.6f, 0.9f, 0.5f, 0.3f, 0.7f, 1.0f)

                barHeights.forEach { heightFraction ->
                    Box(
                        modifier = Modifier
                            .width(16.dp)
                            .fillMaxHeight(heightFraction)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(
                                if (heightFraction == 1.0f) TaskemerPrimary else TaskemerPrimary.copy(alpha = 0.4f)
                            )
                    )
                }
            }
        }
    }
}