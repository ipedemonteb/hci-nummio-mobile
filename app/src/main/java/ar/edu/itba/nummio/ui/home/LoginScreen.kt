package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onNavigateToSignup: () -> Unit,
    onNavigateToRecover: ()->Unit,
    viewModel: HomeViewModel
) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val uiState = viewModel.uiState
    Scaffold(modifier = Modifier
        .background(Color.White)
        .fillMaxSize(),
        topBar = {TopBar(title = stringResource(R.string.enter_email_and_password), onBackClick = onBackClick)}
    ) {
        paddingValues ->
        Column(modifier = Modifier
            .padding(vertical = 30.dp, horizontal = if(uiState.isLandscape) 100.dp else 30.dp)
            .padding(paddingValues)
            .verticalScroll(
                enabled = uiState.isLandscape,
                state = rememberScrollState())
        ) {
            Row(modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth()) {
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = { userEmail = it },
                    label = { Text(stringResource(R.string.email)) },
                    maxLines = 1,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DarkPurple,
                        cursorColor = DarkPurple
                    ),
                    modifier = Modifier.fillMaxWidth()
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
                                painter = if (passwordVisible) painterResource(R.drawable.eye_open) else painterResource(R.drawable.closed_eye),
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
                        cursorColor = DarkPurple)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    color = DarkPurple,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable { onNavigateToRecover()}
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.padding(horizontal = if(uiState.isLandscape) 100.dp else 0.dp)) {
                HighContrastBtn(
                    onClick = {
                        viewModel.login(userEmail, userPassword)
                    },
                    text = stringResource(R.string.login_button)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.padding(horizontal = if(uiState.isLandscape) 100.dp else 0.dp)) {
                LowContrastBtn( onClick = { onNavigateToSignup() }, text = stringResource(R.string.no_account))
            }
        }
    }
}

/*
@Preview(locale = "es")
@Composable
fun LoginScreenPreview() {
    NummioTheme {
        LoginScreen(
            onNavigateToSignup = {},
            viewModel = viewModel(factory = HomeViewModel.provideFactory((LocalContext.current.applicationContext as MyApplication))),
            onNavigateToRecover =
        )
    }
}
*/