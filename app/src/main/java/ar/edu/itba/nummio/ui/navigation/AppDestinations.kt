package ar.edu.itba.nummio.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import ar.edu.itba.nummio.R

enum class AppDestinations(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
)  {
    HOME (R.string.home, Icons.Filled.Home, "home"),
    START(R.string.start, Icons.Filled.Check, "start"),
    LOGIN(R.string.login, Icons.Filled.Check, "login"),
    SIGNUP(R.string.signup, Icons.Filled.Check, "signup")
}