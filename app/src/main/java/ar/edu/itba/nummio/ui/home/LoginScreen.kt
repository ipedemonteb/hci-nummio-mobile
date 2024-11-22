package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToRoute: (route: String) -> Unit,
    viewModel: HomeViewModel
) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Surface(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 30.dp)) {
            Row(modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth().offset(x = (-5).dp),) {
                IconButton({
                    onNavigateToRoute("start")
                }) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = null,
                        tint = DarkPurple,
                        modifier = Modifier.size(40.dp).offset(x = (-10).dp),
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 30.dp),
                horizontalArrangement = Arrangement.Center,) {
                Text(
                    text = stringResource(R.string.enter_email_and_password),
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    color = DarkPurple,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center,
                    lineHeight = 35.sp
                )
            }
            Row(modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth()) {
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = { userEmail = it },
                    label = { Text(stringResource(R.string.email)) },
                    maxLines = 1,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = DarkPurple,
                        cursorColor = DarkPurple
                    )
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
                    modifier = Modifier.clickable { }
                )
            }
            Row(modifier = Modifier.padding(top = 30.dp)) {
                HighContrastBtn(
                    onClick = {
                        viewModel.login(userEmail, userPassword)
                    },
                    text = stringResource(R.string.login_button)
                )
            }
            Row(modifier = Modifier.padding(vertical = 20.dp)) {
                LowContrastBtn( onClick = { onNavigateToRoute("signup") }, text = stringResource(R.string.no_account))
            }
        }
    }
}

@Preview(locale = "es")
@Composable
fun LoginScreenPreview() {
    NummioTheme {
        LoginScreen(onNavigateToRoute = {}, viewModel = viewModel(factory = HomeViewModel.provideFactory((LocalContext.current.applicationContext as MyApplication))))
    }
}