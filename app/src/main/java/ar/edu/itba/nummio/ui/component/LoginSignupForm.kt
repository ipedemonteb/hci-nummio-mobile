package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.DarkPurple

@Composable
fun LoginSignupForm(
    modifier: Modifier = Modifier,
    title: String,
    onConfirmClick: () -> Unit,
    onCancelClick: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordHiden by remember { mutableStateOf(true) }
    Column (
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 130.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier.padding(bottom = 30.dp),
            text = title,
            fontSize = 25.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            modifier = modifier.padding(bottom = 10.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") }
        )
        OutlinedTextField(
            modifier = modifier.padding(bottom = 10.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick = {
                viewModel.login("johndoe@email.com", "1234567890")//@TODO levantar de password
            },
            modifier = modifier.fillMaxWidth()
                .padding(bottom = 10.dp),
            colors = ButtonColors(DarkPurple, Color.White, Color.White, Color.White)
        ) { Text("Confirmar") }
        Button(
            onClick = onCancelClick,
            modifier = modifier.fillMaxWidth()
                .padding(bottom = 10.dp),
            colors = ButtonColors(Color.White, DarkPurple, Color.White, Color.White)
        ) { Text("Cancelar") }
    }
}