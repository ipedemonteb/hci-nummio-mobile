package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.itba.nummio.ui.component.LoginSignupForm

@Composable
fun LoginScreen(
    onNavigateToRoute: (route: String) -> Unit
) {
    LoginSignupForm(
        modifier = Modifier.background(Color.White),
        title = "Iniciá sesión",
        onConfirmClick = {},
        onCancelClick = { onNavigateToRoute("start") }
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(onNavigateToRoute = {})
}