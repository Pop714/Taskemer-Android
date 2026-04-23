package net.pop.taskemer.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.pop.taskemer.ui.components.TaskemerLogo
import net.pop.taskemer.ui.navigation.TaskemerNavGraph
import net.pop.taskemer.ui.theme.TaskemerBackground
import net.pop.taskemer.ui.theme.TaskemerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        setContent {
            TaskemerTheme {
                val viewModel: MainViewModel = hiltViewModel()
                if (viewModel.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(TaskemerBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        TaskemerLogo(isAnimating = true)
                    }
                } else {
                    TaskemerNavGraph(
                        startDestination = viewModel.startDestination
                    )
                }
            }
        }
    }
}