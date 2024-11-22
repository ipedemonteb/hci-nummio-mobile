package ar.edu.itba.nummio.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import ar.edu.itba.nummio.R

enum class AppDestinations(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
)  {
    HOME (R.string.home, Icons.Filled.Home, "home"),
    START(R.string.start, Icons.Filled.Check, "start"),
    LOGIN(R.string.login, Icons.Filled.Check, "login"),
    SIGNUP(R.string.signup, Icons.Filled.Check, "signup"),
    TRANSFERS(R.string.transfers, Icons.Filled.Check, "transfers"),
    MOVEMENTS(R.string.movements, Icons.Filled.Check, "movements"),
    WALLET(R.string.wallet, Icons.Filled.Check, "wallet"),
    MAKE_PAYMENT(R.string.make_payments, Icons.Filled.Check, "make_payments"),
    GENERATE_LINK(R.string.generate_links, Icons.Filled.Check, "generate_links"),
    ADD_CARD(R.string.add_cards, Icons.Filled.Check, "add_cards"),
    RECOVER_PASSWORD(R.string.password_recoveries, Icons.Filled.Check, "recover_passwords")
}