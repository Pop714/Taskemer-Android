package net.pop.taskemer.ui.screens.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.pop.taskemer.ui.components.TaskemerLogo
import net.pop.taskemer.ui.components.TaskemerPrimaryButton
import net.pop.taskemer.ui.components.TaskemerTextField
import net.pop.taskemer.ui.theme.Rubik
import net.pop.taskemer.ui.theme.TaskemerBackground
import net.pop.taskemer.ui.theme.TaskemerPrimary
import net.pop.taskemer.ui.theme.TaskemerSurface
import net.pop.taskemer.ui.theme.TaskemerTextPrimary
import net.pop.taskemer.ui.theme.TaskemerTextSecondary

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.loginSuccess) {
        if (state.loginSuccess) {
            onNavigateToHome()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TaskemerBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Logo & Title
        TaskemerLogo()
        Text(
            "Taskemer",
            color = TaskemerPrimary,
            fontSize = 32.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Focus. Build. Achieve.",
            color = TaskemerTextSecondary,
            fontSize = 14.sp,
            fontFamily = Rubik
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Surface Card
        Surface(
            color = TaskemerSurface,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    "Welcome Back",
                    color = TaskemerTextPrimary,
                    fontSize = 24.sp,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Enter your credentials to access your workspace.",
                    color = TaskemerTextSecondary,
                    fontSize = 14.sp,
                    fontFamily = Rubik
                )

                Spacer(modifier = Modifier.height(24.dp))

                TaskemerTextField(
                    label = "Username",
                    hint = "pop",
                    value = state.username,
                    onValueChange = viewModel::onUsernameChange,
                    leadingIcon = Icons.Default.PermIdentity
                )

                Spacer(modifier = Modifier.height(16.dp))

                TaskemerTextField(
                    label = "Password",
                    hint = "••••••••",
                    value = state.password,
                    onValueChange = viewModel::onPasswordChange,
                    leadingIcon = Icons.Default.Lock,
                    isPassword = true,
                    trailingContent = {
                        Text("Forgot Password?", color = TaskemerPrimary, fontSize = 11.sp)
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                TaskemerPrimaryButton(
                    text = if (state.isLoading) "Loading..." else "Sign In",
                    onClick = { viewModel.login() }
                )

                state.errorMessage?.let { error ->
                    Text(
                        text = error, color = MaterialTheme.colorScheme.error,
                        fontSize = 14.sp,
                        fontFamily = Rubik
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onNavigateToRegister) {
            Text("Don't have an account? ", color = TaskemerTextSecondary, fontFamily = Rubik)
            Text(
                "Create an Account",
                color = TaskemerPrimary,
                fontWeight = FontWeight.Bold,
                fontFamily = Rubik
            )
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen(
        onNavigateToRegister = {},
        onNavigateToHome = {}
    )
}