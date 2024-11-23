package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple

@Composable
fun SignupScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToVerify: (String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: HomeViewModel
) {
    var userPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    val emailHasErrors by remember(email) {
        derivedStateOf {
            if (email.isNotEmpty()) {
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            } else {
                false
            }
        }
    }
    var emailErrorText by remember { mutableStateOf("") }
    var emailIsEmpty by remember { mutableStateOf(false) }
    var passwordErrorText by remember { mutableStateOf("") }
    var showPasswordError by remember { mutableStateOf(false) }
    var confirmPasswordErrorText by remember { mutableStateOf("") }
    var showConfirmPasswordError by remember { mutableStateOf(false) }
    val MANDATORY_INPUT_ERROR = stringResource(R.string.mandatory_input_error)
    val SAME_PASSWORD_ERROR = stringResource(R.string.same_passwords_error)
    val PASSWORD_SHORT_ERROR = stringResource(R.string.password_short)

    val uiState = viewModel.uiState
    Scaffold(modifier = Modifier
        .background(Color.White)
        .fillMaxSize(),
        topBar = { TopBar(title = stringResource(R.string.create_account), onBackClick = {onBackClick()}) }
    ) {
        paddingValues ->
        Column(modifier = Modifier
            .padding(vertical = 0.dp, horizontal = if(uiState.isLandscape) 100.dp else 30.dp)
            .padding(paddingValues)
            .verticalScroll(
                enabled = uiState.isLandscape,
                state = rememberScrollState()
            )
        ) {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Text(
                    text = stringResource(R.string.enter_email),
                    fontSize = 18.sp,
                    color = DarkPurple
                )
            }
            Row(modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth()) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it; emailIsEmpty=false},
                    label = { Text(stringResource(R.string.email)) },
                    isError = emailHasErrors || emailIsEmpty,
                    supportingText = {
                        if (emailIsEmpty) {
                            Text(emailErrorText)
                        }
                        if (emailHasErrors) {
                            Text(stringResource(R.string.invalid_mail_format))
                        }
                    },
                    maxLines = 1,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DarkPurple,
                        cursorColor = DarkPurple
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Text(
                    text = stringResource(R.string.enter_password),
                    fontSize = 18.sp,
                    color = DarkPurple
                )
            }
            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                OutlinedTextField(
                    value = userPassword,
                    onValueChange = { userPassword = it },
                    label = { Text(stringResource(R.string.password)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = if (passwordVisible) painterResource(R.drawable.eye_open) else painterResource(
                                    R.drawable.closed_eye),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DarkPurple,
                        cursorColor = DarkPurple
                    ),
                    isError = showPasswordError,
                    supportingText = {if (showPasswordError) Text(passwordErrorText)}
                )
            }
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Text(
                    text = stringResource(R.string.enter_password_again),
                    fontSize = 18.sp,
                    color = DarkPurple
                )
            }
            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text(stringResource(R.string.confirm_password)) },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                painter = if (confirmPasswordVisible) painterResource(R.drawable.eye_open) else painterResource(
                                    R.drawable.closed_eye),
                                contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password",
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DarkPurple,
                        cursorColor = DarkPurple
                    ),
                    isError = showConfirmPasswordError,
                    supportingText = {if (showConfirmPasswordError) Text(confirmPasswordErrorText)}
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.padding(horizontal = if(uiState.isLandscape) 100.dp else 0.dp)) {
                HighContrastBtn( onClick = {
                    showPasswordError = false
                    showConfirmPasswordError = false
                    emailIsEmpty = false

                    if (email.isEmpty()) {
                        emailErrorText = MANDATORY_INPUT_ERROR
                        emailIsEmpty = true
                    }
                    if (userPassword.isEmpty()) {
                        passwordErrorText = MANDATORY_INPUT_ERROR
                        showPasswordError = true
                        if (confirmPassword.isEmpty()) {
                            confirmPasswordErrorText = MANDATORY_INPUT_ERROR
                            showConfirmPasswordError = true
                        }
                    } else if(userPassword.length < 8) {
                        passwordErrorText = PASSWORD_SHORT_ERROR
                        showPasswordError = true
                    }
                    else if (userPassword != confirmPassword) {
                        confirmPasswordErrorText = SAME_PASSWORD_ERROR
                        showConfirmPasswordError = true
                    }

                    if (!showPasswordError && !showConfirmPasswordError && !emailIsEmpty && !emailHasErrors) {
                        onNavigateToVerify("${email};${userPassword}") //@TODO: ver si se puede hacer mejor (es muy feo)
                    }
                }, text = stringResource(R.string.register_button))
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.padding(horizontal = if(uiState.isLandscape) 100.dp else 0.dp)) {
                LowContrastBtn( onClick = { onNavigateToLogin() }, text = stringResource(R.string.has_account))
            }
        }
    }
}

