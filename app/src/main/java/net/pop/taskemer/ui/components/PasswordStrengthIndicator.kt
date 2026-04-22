package net.pop.taskemer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.screens.auth.register.PasswordStrength
import net.pop.taskemer.ui.theme.Rubik
import net.pop.taskemer.ui.theme.TaskemerPrimary
import net.pop.taskemer.ui.theme.TaskemerTextFieldBg

@Composable
fun PasswordStrengthIndicator(strength: PasswordStrength) {
    val activeColor = when (strength) {
        PasswordStrength.NONE -> Color.Transparent
        PasswordStrength.WEAK -> Color(0xFFE57373)
        PasswordStrength.FAIR -> Color(0xFFFFB74D)
        PasswordStrength.STRONG -> TaskemerPrimary
    }
    val inactiveColor = TaskemerTextFieldBg

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(4.dp)
                .background(
                    if (strength >= PasswordStrength.WEAK) activeColor else inactiveColor,
                    RoundedCornerShape(2.dp)
                )
        )
        Spacer(modifier = Modifier.width(4.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(4.dp)
                .background(
                    if (strength >= PasswordStrength.FAIR) activeColor else inactiveColor,
                    RoundedCornerShape(2.dp)
                )
        )
        Spacer(modifier = Modifier.width(4.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(4.dp)
                .background(
                    if (strength >= PasswordStrength.STRONG) activeColor else inactiveColor,
                    RoundedCornerShape(2.dp)
                )
        )

        Spacer(modifier = Modifier.width(12.dp))

        val labelText = if (strength == PasswordStrength.NONE) "" else strength.name
        Text(
            text = labelText,
            color = activeColor,
            fontSize = 10.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier.width(50.dp)
        )
    }
}