package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoverPasswordScreen(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel
) {
    var userEmail by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var passwordError by remember { mutableStateOf("") }
    var showPasswordError by remember { mutableStateOf(false) }
    var codeError by remember { mutableStateOf("") }
    var showCodeError by remember { mutableStateOf(false) }
    val MANDATORY_INPUT_ERROR = stringResource(R.string.mandatory_input_error)
    val PASSWORD_SHORT_ERROR = stringResource(R.string.password_short)

    fun handleConfirm() {
        showPasswordError = false
        showCodeError = false
        if(password.length < 8) {
            showPasswordError = true
            passwordError = if(password.isEmpty())
                MANDATORY_INPUT_ERROR
            else
                PASSWORD_SHORT_ERROR
        }
        if(code.isEmpty()) {
            showCodeError = true
            codeError = MANDATORY_INPUT_ERROR
        }
        if(!showPasswordError && !showCodeError)
            viewModel.resetPassword(token=code, password=password)
    }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        topBar = { TopBar(title = stringResource(R.string.password_recovery), onBackClick = {
            onBackClick()
            viewModel.resetRecoverPasswordSent()
        }, viewModel = viewModel)}
    ){
        paddingValues ->
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 30.dp).padding(paddingValues)) {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Text(
                    text = stringResource(R.string.enter_your_email),
                    fontSize = 18.sp,
                    color = DarkPurple,
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
                    readOnly = viewModel.uiState.recoverCodeSent,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = DarkPurple,
                        cursorColor = DarkPurple
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    isError = !viewModel.uiState.recoverCodeSent && viewModel.uiState.error != null,
                    supportingText = {
                        if(!viewModel.uiState.recoverCodeSent && viewModel.uiState.error != null)
                            Text(stringResource(when(viewModel.uiState.error!!.message) {
                                "User not found" -> R.string.user_not_exists
                                else -> R.string.unexpected_error
                            }))
                    }
                )
            }

            if(!viewModel.uiState.recoverCodeSent){
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    HighContrastBtn(onClick = { viewModel.recoverPassword(userEmail) }, text = stringResource(R.string.send_code))
                }
            } else {
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    Text(
                        text = stringResource(R.string.enter_code),
                        fontSize = 18.sp,
                        color = DarkPurple,
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = code,
                        onValueChange = { code = it },
                        label = { Text(stringResource(R.string.code)) },
                        maxLines = 1,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = DarkPurple,
                            cursorColor = DarkPurple
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        isError = showCodeError || (viewModel.uiState.recoverCodeSent && viewModel.uiState.error != null),
                        supportingText = {
                            if(showCodeError)
                                Text(codeError)
                            else if(viewModel.uiState.recoverCodeSent && viewModel.uiState.error != null)
                                Text(stringResource(when(viewModel.uiState.error!!.message) {
                                    "Invalid code" -> R.string.invalid_code
                                    else -> R.string.unexpected_error
                                }))
                        }
                    )
                }
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    Text(
                        text = stringResource(R.string.new_password),
                        fontSize = 18.sp,
                        color = DarkPurple
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(R.string.password)) },
                        maxLines = 1,
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
                        isError = showPasswordError,
                        supportingText = { if(showPasswordError) Text(passwordError) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = DarkPurple,
                            cursorColor = DarkPurple
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.resend_code),
                        color = DarkPurple,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { viewModel.recoverPassword(userEmail) }
                    )
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    HighContrastBtn(onClick = { handleConfirm() }, text = stringResource(R.string.confirm_button))
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    LowContrastBtn(onClick = {  onBackClick()
                                                viewModel.resetRecoverPasswordSent()
                                             }, text = stringResource(R.string.cancel_button))
                }
            }
        }
    }
}


@Preview
@Composable
fun RecoverPasswordScreenPreview() {
    RecoverPasswordScreen({}, viewModel = viewModel(factory = HomeViewModel.provideFactory((LocalContext.current.applicationContext as MyApplication))))
}
