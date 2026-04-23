package net.pop.taskemer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.PestControlRodent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.taskemer.ui.theme.Black
import net.pop.taskemer.ui.theme.Rubik
import net.pop.taskemer.ui.theme.TaskemerPrimary
import net.pop.taskemer.ui.theme.TaskemerTextColor
import net.pop.taskemer.ui.theme.TaskemerTextSecondary

@Composable
fun TaskemerTopBar(
    modifier: Modifier = Modifier,
    isOnline: Boolean = true,
    logout: () -> Unit
) {
    Row(
        modifier = modifier
            .height(58.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 16.dp,
                spotColor = TaskemerPrimary,
                ambientColor = TaskemerPrimary
            )
            .background(Black)
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Spacer(modifier = Modifier.width(16.dp))

//            TaskemerLogo(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp))
//                    .size(42.dp)
//                    .background(TaskemerPrimary)
//                    .padding(2.dp),
//                color = TaskemerTextPrimary
//            )

            Icon(
                imageVector = Icons.Default.PestControlRodent,
                tint = TaskemerPrimary,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(42.dp),
                contentDescription = "Person"
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Taskemer",
                color = TaskemerTextColor,
                fontSize = 20.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = if (isOnline) "ONLINE" else "OFFLINE",
                color = TaskemerTextSecondary,
                fontSize = 10.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(if (isOnline) Color(0xFF4CAF50) else Color(0xFFE57373))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Output,
                    contentDescription = "logout",
                    tint = TaskemerPrimary,
                    modifier = Modifier.clickable(true, onClick = logout)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}