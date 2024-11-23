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
import ar.edu.itba.nummio.ui.home.ConfirmScreen
import ar.edu.itba.nummio.ui.home.DataScreen
import ar.edu.itba.nummio.ui.home.DepositScreen
import ar.edu.itba.nummio.ui.home.GenerateLinkScreen
import ar.edu.itba.nummio.ui.home.HomeScreen
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.home.InvestmentScreen
import ar.edu.itba.nummio.ui.home.LoginScreen
import ar.edu.itba.nummio.ui.home.OtherScreen
import ar.edu.itba.nummio.ui.home.RecoverPasswordScreen
import ar.edu.itba.nummio.ui.home.SignupScreen
import ar.edu.itba.nummio.ui.home.StartScreen
import ar.edu.itba.nummio.ui.home.MovementsScreen
import ar.edu.itba.nummio.ui.home.PayScreen
import ar.edu.itba.nummio.ui.home.SendScreen
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
                onNavigateToInvestments = {navController.navigate(AppDestinations.INVESTMENTS.route)},
                onNavigateToMakePayment= { navController.navigate(AppDestinations.MAKE_PAYMENT.route) },
                onNavigateToGenerateLink= { navController.navigate(AppDestinations.GENERATE_LINK.route) },
                onNavigateToPromotions= {},
                onNavigateToContacts = {},
                onNavigateToHelp= {},
                onNavigateToDeposit = {navController.navigate(AppDestinations.DEPOSIT.route)},
                viewModel = viewModel
                )
        }
        composable(AppDestinations.LOGIN.route) {
            LoginScreen(
                onNavigateToRoute = { route -> navController.navigate(route) },
                onNavigateToRecover = {navController.navigate(AppDestinations.RECOVER_PASSWORD.route)},
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
            MovementsScreen(viewModel = viewModel, movements = viewModel.uiState.paymentHistory?.map { it.asTransactionData() }
                ?: emptyList(), onBackClick = {navController.popBackStack()})
        }
        composable(AppDestinations.TRANSFERS.route){
            TransferScreen(recipients = emptyList(), onBackClick = {navController.popBackStack()}, onRecipientClick = {}, onNavigateToSendScreen = {email -> navController.navigate("${AppDestinations.SEND_PAYMENT.route}/$email")}) //@TODO: onRecipientClick(Si apreto en un contacto que me lleve a transferirle, cambiarle el nombre) + addContact
        }
        composable(AppDestinations.WALLET.route){
            WalletScreen(onBackClick = {navController.popBackStack()}, onNavigateToAddCard = {navController.navigate(AppDestinations.ADD_CARD.route)}, onNavigateToConfirmScreen = {message, job-> navController.navigate("${AppDestinations.CONFIRM_SCREEN.route }/$message") }, viewModel = viewModel)
        }
        composable("${AppDestinations.WALLET.route}/{bool}",
            arguments = listOf(navArgument("bool") { type = NavType.BoolType })
            ){
            backStackEntry ->
            WalletScreen(wasConfirmed = backStackEntry.arguments?.getBoolean("bool")?: false, onBackClick = {navController.popBackStack()}, onNavigateToAddCard = {navController.navigate(AppDestinations.ADD_CARD.route)}, onNavigateToConfirmScreen = {message, job-> navController.navigate("${AppDestinations.CONFIRM_SCREEN.route}/$message"); job.start()}, viewModel = viewModel)
        }
        composable(AppDestinations.MAKE_PAYMENT.route){
            PayScreen(onBackClick = {navController.popBackStack()})
        }
        composable(AppDestinations.GENERATE_LINK.route){
            GenerateLinkScreen(onBackClick = {navController.popBackStack()})
        }
        composable(AppDestinations.ADD_CARD.route){
            AddCardScreen(onBackClick = {navController.popBackStack()}, viewModel = viewModel)
        }
        composable(AppDestinations.INVESTMENTS.route){
            InvestmentScreen(onBackClick = {navController.popBackStack()})
        }
        composable("${AppDestinations.SEND_PAYMENT.route}/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ){
            backStackEntry ->
            SendScreen(onBackClick = {navController.popBackStack()}, backStackEntry.arguments?.getString("email")?: "", viewModel = viewModel)
        }
        composable(AppDestinations.DEPOSIT.route){
            DepositScreen(onBackClick = {navController.popBackStack()}, viewModel)
        }
        composable("${AppDestinations.CONFIRM_SCREEN.route}/{message}",
            arguments = listOf(navArgument("message") { type = NavType.StringType })
        ){
            backStackEntry ->
            ConfirmScreen(action = backStackEntry.arguments?.getString("message")?: "", onBackClick = {bool -> navController.navigate("${AppDestinations.WALLET.route}/$bool"){
                popUpTo(AppDestinations.WALLET.route){inclusive = true}
            } })
        }
        composable(AppDestinations.DATA_SCREEN.route){
            DataScreen(onBackClick = {navController.popBackStack()})
        }
    }
}