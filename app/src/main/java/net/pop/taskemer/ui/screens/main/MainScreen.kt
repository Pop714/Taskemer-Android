package net.pop.taskemer.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.pop.taskemer.ui.components.TaskemerBottomNav
import net.pop.taskemer.ui.components.TaskemerTopBar
import net.pop.taskemer.ui.navigation.BottomNavItem
import net.pop.taskemer.ui.screens.focus.FocusScreen
import net.pop.taskemer.ui.screens.home.HomeScreen
import net.pop.taskemer.ui.screens.project.ProjectDetailScreen
import net.pop.taskemer.ui.theme.TaskemerBackground
import net.pop.taskemer.utils.NetworkConnectivityObserver
import kotlin.math.roundToInt

@Composable
fun MainScreen(
    logout: () -> Unit
) {
    val bottomNavController = rememberNavController()

    val context = LocalContext.current
    val networkObserver = remember { NetworkConnectivityObserver(context) }
    val isOnline by networkObserver.isConnected.collectAsState(initial = true)

    val bottomBarHeight = 96.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue - delta
                bottomBarOffsetHeightPx.floatValue = newOffset.coerceIn(0f, bottomBarHeightPx)
                return Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
        containerColor = TaskemerBackground,
        topBar = {
            TaskemerTopBar(isOnline = isOnline, logout = logout)
        },
        bottomBar = {
            TaskemerBottomNav(
                navController = bottomNavController,
                modifier = Modifier.offset {
                    IntOffset(x = 0, y = bottomBarOffsetHeightPx.floatValue.roundToInt())
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        ) {

            composable(BottomNavItem.Home.route) {
                HomeScreen {
                    bottomNavController.navigate(BottomNavItem.Focus.route) {
                        popUpTo(bottomNavController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }

            composable(BottomNavItem.Projects.route) {
                ProjectDetailScreen { }
            }

            composable(BottomNavItem.Focus.route) {
                FocusScreen()
            }
        }
    }
}