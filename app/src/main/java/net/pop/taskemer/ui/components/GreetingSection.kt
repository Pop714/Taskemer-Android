package net.pop.taskemer.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.theme.Rubik
import net.pop.taskemer.ui.theme.TaskemerTextPrimary
import java.util.Calendar

@Composable
fun GreetingSection(username: String) {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greeting = when (currentHour) {
        in 0..11 -> "Good morning"
        in 12..16 -> "Good afternoon"
        else -> "Good evening"
    }

    Text(
        text = "$greeting,\n$username.",
        color = TaskemerTextPrimary,
        fontSize = 32.sp,
        fontFamily = Rubik,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    )
}