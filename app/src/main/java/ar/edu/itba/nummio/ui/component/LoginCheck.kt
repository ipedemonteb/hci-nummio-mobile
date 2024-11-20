package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.ui.theme.DarkPurple

@Composable
fun LoginCheck(
    modifier: Modifier
) {
    var code by remember { mutableStateOf("") }
    Column (
        modifier = modifier.fillMaxWidth()
            .padding(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier.padding(bottom = 20.dp),
            fontSize = 25.sp,
            text = "Ingrese el codigo enviado al mail ju*****@gmail.com",
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            modifier = modifier.padding(bottom = 20.dp),
            value = code,
            onValueChange = { code = it },
            label = { Text("Codigo") }
        )
        Button(
            onClick = {},
            modifier = modifier.fillMaxWidth(),
            colors = ButtonColors(DarkPurple, Color.White, Color.White, Color.White)
        ) { Text("Enviar") }
    }
}

@Preview
@Composable
fun LoginCheckPreview() {
    LoginCheck(modifier = Modifier)
}