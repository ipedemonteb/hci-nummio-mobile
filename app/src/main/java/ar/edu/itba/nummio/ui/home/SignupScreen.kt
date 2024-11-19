package ar.edu.itba.nummio.ui.home

import androidx.compose.runtime.Composable
import ar.edu.itba.nummio.ui.component.LoginSignupForm

@Composable
fun SignupScreen(
    onNavigateToRoute: (route: String) -> Unit
) {
    LoginSignupForm(
        title = "Creá tu cuenta",
        onConfirmClick = {},
        onCancelClick = { onNavigateToRoute("start") }
    )
}