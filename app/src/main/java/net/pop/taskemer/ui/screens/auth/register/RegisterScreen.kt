package net.pop.taskemer.ui.screens.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.pop.taskemer.ui.components.PasswordStrengthIndicator
import net.pop.taskemer.ui.components.TaskemerPrimaryButton
import net.pop.taskemer.ui.components.TaskemerTextField
import net.pop.taskemer.ui.theme.Rubik
import net.pop.taskemer.ui.theme.TaskemerBackground
import net.pop.taskemer.ui.theme.TaskemerPrimary
import net.pop.taskemer.ui.theme.TaskemerSurface
import net.pop.taskemer.ui.theme.TaskemerTextPrimary
import net.pop.taskemer.ui.theme.TaskemerTextSecondary

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(state.registerSuccess) {
        if (state.registerSuccess) {
            onNavigateToLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TaskemerBackground)
            .verticalScroll(scrollState)
            .padding(24.dp), horizontalAlignment = CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Taskemer",
            color = TaskemerPrimary,
            fontSize = 32.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold
        )
        Text(
            "DESIGN YOUR PRODUCTIVITY ARC",
            color = TaskemerTextSecondary,
            fontSize = 12.sp,
            letterSpacing = 1.sp,
            fontFamily = Rubik
        )

        Spacer(modifier = Modifier.height(32.dp))

        Surface(
            color = TaskemerSurface,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {

                Text(
                    "Create Account",
                    color = TaskemerTextPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Rubik
                )

                Text(
                    "Join the architecture of high-performance teams.",
                    color = TaskemerTextSecondary,
                    fontSize = 14.sp,
                    fontFamily = Rubik
                )

                Spacer(modifier = Modifier.height(24.dp))

                TaskemerTextField(
                    label = "Firstname",
                    hint = "Albraa",
                    value = state.firstname,
                    onValueChange = viewModel::onFirstnameChange
                )

                Spacer(modifier = Modifier.height(16.dp))

                TaskemerTextField(
                    label = "Lastname",
                    hint = "Alhrairy",
                    value = state.lastname,
                    onValueChange = viewModel::onLastnameChange
                )

                Spacer(modifier = Modifier.height(16.dp))

                TaskemerTextField(
                    label = "Username",
                    hint = "pop",
                    value = state.username,
                    onValueChange = viewModel::onUsernameChange
                )

                Spacer(modifier = Modifier.height(16.dp))

                TaskemerTextField(
                    label = "Password",
                    hint = "••••••••",
                    value = state.password,
                    isPassword = true,
                    onValueChange = viewModel::onPasswordChange
                )

                PasswordStrengthIndicator(strength = state.passwordStrength)

                Spacer(modifier = Modifier.height(16.dp))

                TaskemerTextField(
                    label = "Confirm Password",
                    hint = "••••••••",
                    value = state.confirmPassword,
                    isPassword = true,
                    onValueChange = viewModel::onConfirmPasswordChange,
                    isError = state.passwordMismatchError,
                    errorMessage = "Passwords do not match"
                )

                Spacer(modifier = Modifier.height(24.dp))

                TaskemerPrimaryButton(
                    text = if (state.isLoading) "Loading..." else "Sign Up",
                    onClick = { viewModel.register() }
                )

                state.errorMessage?.let { error ->
                    Text(
                        text = error, color = MaterialTheme.colorScheme.error,
                        fontSize = 14.sp,
                        fontFamily = Rubik
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    RegisterScreen(
        onNavigateToLogin = { })
}