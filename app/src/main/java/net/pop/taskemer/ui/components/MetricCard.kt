package net.pop.taskemer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.theme.*

@Composable
fun MetricCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color = TaskemerTextPrimary
) {
    Surface(
        color = TaskemerSurface,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title.uppercase(),
                color = TaskemerTextSecondary,
                fontSize = 10.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                color = valueColor,
                fontSize = 18.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold
            )
        }
    }
}