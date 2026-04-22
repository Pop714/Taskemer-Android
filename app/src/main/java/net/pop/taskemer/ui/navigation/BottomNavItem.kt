package net.pop.taskemer.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusStrong
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("nav_home", "Home", Icons.Default.Home)
    object Projects : BottomNavItem("nav_projects", "Projects", Icons.Default.Task)
    object Focus : BottomNavItem("nav_focus", "Focus", Icons.Default.CenterFocusStrong)
}