package ar.edu.itba.nummio.ui.navigation

import TransferScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.home.HomeScreen
import ar.edu.itba.nummio.ui.home.LoginScreen
import ar.edu.itba.nummio.ui.home.OtherScreen
import ar.edu.itba.nummio.ui.home.RecoverPasswordScreen
import ar.edu.itba.nummio.ui.home.SignupScreen
import ar.edu.itba.nummio.ui.home.StartScreen
import ar.edu.itba.nummio.ui.home.MovementsScreen
import ar.edu.itba.nummio.ui.home.WalletScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    isAuthenticated: Boolean = false
) {
    val startDestination = if (isAuthenticated) "home" else "start"
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("start") {
            StartScreen(
                onNavigateToRoute = { route -> navController.navigate(route) }
            )
        }
        composable("home") {
            HomeScreen(
                onNavigateToTransfer = { navController.navigate(AppDestinations.TRANSFERS.route) },
                onNavigateToMovements = { navController.navigate(AppDestinations.MOVEMENTS.route) },
                onNavigateToCards = { navController.navigate(AppDestinations.WALLET.route) }
            )
        }
        composable("login") {
            LoginScreen(
                onNavigateToRoute = { route -> navController.navigate(route) }
            )
        }
        composable("signup") {
            SignupScreen (
                onNavigateToRoute = { route -> navController.navigate(route) }
            )
        }
        composable("recover") {
            RecoverPasswordScreen(
                onNavigateToRoute = { route -> navController.navigate(route) }
            )
        }
        composable(
            "other/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ) {
            OtherScreen(it.arguments?.getInt("id"))
        }
        composable(AppDestinations.MOVEMENTS.route){
            MovementsScreen(movements = emptyList(), onBackClick = {navController.popBackStack()})
        }
        composable(AppDestinations.TRANSFERS.route){
            TransferScreen(recipients = emptyList(), onBackClick = {navController.popBackStack()}, onRecipientClick = {}) //@TODO: onRecipientClick + addContact
        }
        composable(AppDestinations.WALLET.route){
            WalletScreen(onBackClick = {navController.popBackStack()}, onNavigateToAddCard = {AppDestinations.ADD_CARD.route})
        }
    }
}