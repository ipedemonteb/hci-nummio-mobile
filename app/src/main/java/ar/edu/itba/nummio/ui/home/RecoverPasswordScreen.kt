package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
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
    var canEdit by remember { mutableStateOf(true) }
    var userEmail by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var codeSent by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        topBar = { TopBar(title = stringResource(R.string.password_recovery), onBackClick = {onBackClick()}, viewModel = viewModel)}
    ){
        paddingValues ->
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 30.dp).padding(paddingValues)) {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Text(
                    text = stringResource(R.string.enter_your_email),
                    fontSize = 18.sp,
                    color = DarkPurple
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
                    readOnly = !canEdit,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = DarkPurple,
                        cursorColor = DarkPurple
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if(!codeSent){
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    HighContrastBtn(onClick = { codeSent = true; viewModel.recoverPassword(userEmail); canEdit = !canEdit}, text = stringResource(R.string.send_code))

                }
            }
            else {
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    Text(
                        text = stringResource(R.string.enter_code),
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
                        value = code,
                        onValueChange = { code = it },
                        label = { Text(stringResource(R.string.code)) },
                        maxLines = 1,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = DarkPurple,
                            cursorColor = DarkPurple
                        ),
                        modifier = Modifier.fillMaxWidth()
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
                    HighContrastBtn(onClick = {viewModel.resetPassword(token=code, password=password)}, text = stringResource(R.string.confirm_button))
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    LowContrastBtn(onClick = {canEdit = !canEdit}, text = stringResource(R.string.cancel_button))
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
