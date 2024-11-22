package ar.edu.itba.nummio.ui.navigation

import TransferScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ar.edu.itba.nummio.ui.home.AddCardScreen
import ar.edu.itba.nummio.ui.home.GenerateLinkScreen
import ar.edu.itba.nummio.ui.home.HomeScreen
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.home.LoginScreen
import ar.edu.itba.nummio.ui.home.OtherScreen
import ar.edu.itba.nummio.ui.home.RecoverPasswordScreen
import ar.edu.itba.nummio.ui.home.SignupScreen
import ar.edu.itba.nummio.ui.home.StartScreen
import ar.edu.itba.nummio.ui.home.MovementsScreen
import ar.edu.itba.nummio.ui.home.PayScreen
import ar.edu.itba.nummio.ui.home.WalletScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel
) {
    val startDestination = if (viewModel.uiState.isAuthenticated) "home" else "start"
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(AppDestinations.START.route) {
            StartScreen(
                onNavigateToRoute = { route -> navController.navigate(route) },
                viewModel = viewModel
            )
        }
        composable(AppDestinations.HOME.route) {
            HomeScreen(
                onNavigateToTransfer = { navController.navigate(AppDestinations.TRANSFERS.route) },
                onNavigateToMovements = { navController.navigate(AppDestinations.MOVEMENTS.route) },
                onNavigateToCards = { navController.navigate(AppDestinations.WALLET.route) },
                onNavigateToInvestments = {}, //@TODO
                onNavigateToMakePayment= { navController.navigate(AppDestinations.MAKE_PAYMENT.route) },
                onNavigateToGenerateLink= { navController.navigate(AppDestinations.GENERATE_LINK.route) },
                onNavigateToPromotions= {},//@TODO
                onNavigateToContacts = {},//@TODO
                onNavigateToHelp= {},//@TODO
                viewModel = viewModel
                )
        }
        composable(AppDestinations.LOGIN.route) {
            LoginScreen(
                onNavigateToRoute = { route -> navController.navigate(route) },
                viewModel = viewModel
            )
        }
        composable(AppDestinations.SIGNUP.route) {
            SignupScreen (
                onNavigateToRoute = { route -> navController.navigate(route) },
                viewModel = viewModel
            )
        }
        composable(AppDestinations.RECOVER_PASSWORD.route) {
            RecoverPasswordScreen(
                onNavigateToRoute = { route -> navController.navigate(route) },
                viewModel = viewModel
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
            TransferScreen(recipients = emptyList(), onBackClick = {navController.popBackStack()}, onRecipientClick = {}) //@TODO: onRecipientClick(Si apreto en un contacto que me lleve a transferirle, cambiarle el nombre) + addContact
        }
        composable(AppDestinations.WALLET.route){
            WalletScreen(onBackClick = {navController.popBackStack()}, onNavigateToAddCard = {navController.navigate(AppDestinations.ADD_CARD.route)})
        }
        composable(AppDestinations.MAKE_PAYMENT.route){
            PayScreen(onBackClick = {navController.popBackStack()})
        }
        composable(AppDestinations.GENERATE_LINK.route){
            GenerateLinkScreen(onBackClick = {navController.popBackStack()})
        }
        composable(AppDestinations.ADD_CARD.route){
            AddCardScreen(onBackClick = {navController.popBackStack()})
        }
    }
}