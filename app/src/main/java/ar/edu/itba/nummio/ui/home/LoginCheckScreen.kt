package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.itba.nummio.ui.component.LoginCheck

@Composable
fun LoginCheckScreen() {
    val modifier = Modifier
    Column(
        modifier = modifier.background(Color.White)
    ) {
        LoginCheck(modifier)
    }
}

@Preview
@Composable
fun LoginCheckScreenPreview() {
    LoginCheckScreen()
}
