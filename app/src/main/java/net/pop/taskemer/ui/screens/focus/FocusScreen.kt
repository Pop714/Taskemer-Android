package net.pop.taskemer.ui.screens.focus

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoNotDisturb
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.pop.taskemer.ui.theme.*

@Composable
fun FocusScreen(
    viewModel: FocusViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val notificationManager = remember {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    // State directly from the Singleton Manager
    val timeRemaining by viewModel.focusManager.timeRemaining.collectAsState()
    val totalDuration by viewModel.focusManager.totalDuration.collectAsState()
    val isRunning by viewModel.focusManager.isRunning.collectAsState()
    val isDndEnabled by viewModel.focusManager.isDndEnabled.collectAsState()

    // Setup the Settings Launcher (For DND)
    val dndSettingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // When they return from settings, check if they actually granted it
        if (notificationManager.isNotificationPolicyAccessGranted) {
            viewModel.toggleDnd()
        }
    }

    // Setup the Notification Launcher (For Android 13+)
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        // If granted, or if they are on an older Android version that doesn't need this permission, start the timer
        if (isGranted || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            viewModel.toggleTimer(context)
        }
    }

    // Calculations for the UI
    val progress = if (totalDuration > 0) timeRemaining.toFloat() / totalDuration.toFloat() else 0f
    val mins = timeRemaining / 60
    val secs = timeRemaining % 60
    val formattedTime = String.format("%02d:%02d", mins, secs)

    // Smooth progress bar animation
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        label = "timer_progress"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TaskemerBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // --- HEADER ---
        Text(
            text = "DEEP FOCUS",
            color = TaskemerTextSecondary,
            fontSize = 14.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        // --- THE GLOWING TIMER RING ---
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(280.dp)
                // The glowing shadow effect (only shows when running)
                .shadow(
                    elevation = if (isRunning) 32.dp else 0.dp,
                    shape = CircleShape,
                    spotColor = TaskemerPrimary,
                    ambientColor = TaskemerPrimary
                )
                .background(TaskemerSurface, CircleShape)
        ) {
            // Background Track
            CircularProgressIndicator(
                progress = { 1f },
                modifier = Modifier.fillMaxSize(),
                color = TaskemerBackground,
                strokeWidth = 12.dp,
            )
            // Active Progress
            CircularProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier.fillMaxSize(),
                color = TaskemerPrimary,
                strokeWidth = 12.dp,
                strokeCap = StrokeCap.Round,
            )

            // Time Text inside the circle
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = formattedTime,
                    color = TaskemerTextPrimary,
                    fontSize = 64.sp,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Bold
                )
                if (!isRunning) {
                    Text("Ready", color = TaskemerTextSecondary, fontFamily = Rubik, fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // --- TIME PRESETS (Only show if not running) ---
        if (!isRunning) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PresetChip(time = 15, onClick = { viewModel.setDuration(15) })
                PresetChip(time = 25, onClick = { viewModel.setDuration(25) })
                PresetChip(time = 60, onClick = { viewModel.setDuration(60) })
            }
        } else {
            // Invisible spacer to maintain the layout height when chips disappear
            Spacer(modifier = Modifier.height(40.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- DO NOT DISTURB TOGGLE ---
        Surface(
            color = TaskemerSurface,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DoNotDisturb,
                        contentDescription = "DND",
                        tint = if (isDndEnabled) TaskemerPrimary else TaskemerTextSecondary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Do Not Disturb", color = TaskemerTextPrimary, fontFamily = Rubik, fontWeight = FontWeight.Bold)
                        Text("Block notifications during focus", color = TaskemerTextSecondary, fontFamily = Rubik, fontSize = 12.sp)
                    }
                }

                Switch(
                    checked = isDndEnabled,
                    onCheckedChange = {
                        if (notificationManager.isNotificationPolicyAccessGranted) {
                            // We have permission, just toggle the state!
                            viewModel.toggleDnd()
                        } else {
                            // We do NOT have permission, open Android Settings
                            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                            dndSettingsLauncher.launch(intent)
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = TaskemerPrimary,
                        checkedTrackColor = TaskemerPrimary.copy(alpha = 0.3f)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- CONTROLS ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Reset Button
            IconButton(
                onClick = { viewModel.resetTimer(context) },
                modifier = Modifier
                    .size(56.dp)
                    .background(TaskemerSurface, CircleShape)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Reset", tint = TaskemerTextPrimary)
            }

            Spacer(modifier = Modifier.width(24.dp))

            // Main Play/Pause Button
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(TaskemerPrimary)
                    .clickable {
                        // On Android 13+, we must ask for POST_NOTIFICATIONS before starting the foreground service
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !isRunning) {
                            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        } else {
                            viewModel.toggleTimer(context)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = "Play/Pause",
                    tint = TaskemerBackground,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        // Extra padding to clear your floating bottom navigation
        Spacer(modifier = Modifier.height(96.dp))
    }
}

@Composable
fun PresetChip(time: Long, onClick: () -> Unit) {
    Surface(
        color = TaskemerSurface,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = "${time}m",
            color = TaskemerTextPrimary,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold
        )
    }
}