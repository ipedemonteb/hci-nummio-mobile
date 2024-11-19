package ar.edu.itba.nummio.ui.home

import androidx.compose.runtime.Composable
import ar.edu.itba.nummio.ui.component.LoginSignupForm

@Composable
fun LoginScreen(
    onNavigateToRoute: (route: String) -> Unit
) {
    LoginSignupForm(
        title = "Iniciá sesión",
        onConfirmClick = {},
        onCancelClick = { onNavigateToRoute("start") }
    )
}