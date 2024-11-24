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
import ar.edu.itba.nummio.R
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
import ar.edu.itba.nummio.ui.home.ResultScreen
import ar.edu.itba.nummio.ui.home.SendScreen
import ar.edu.itba.nummio.ui.home.SettingsScreen
import ar.edu.itba.nummio.ui.home.VerifyScreen
import ar.edu.itba.nummio.ui.home.WalletScreen
import ar.edu.itba.nummio.ui.home.windowScreenWidth

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel,
    windowSizeClass: windowScreenWidth
) {
    var isExpanded = windowSizeClass == windowScreenWidth.EXPANDED
    //Para cada pantalla en particular, pasarle el booleano isExpanded como un "Es tablet?"

    val destination =
        if(viewModel.uiState.isAuthenticated) {
            if (viewModel.uiState.paymentConfirmed || viewModel.uiState.rechargeConfirmed)
                AppDestinations.RESULT_SCREEN.route
            else
                AppDestinations.HOME.route
        } else {
            if (viewModel.uiState.hasBeenVerified)
                AppDestinations.LOGIN.route
            else if(viewModel.uiState.recoverConfirmed)
                AppDestinations.RESULT_SCREEN.route
            else
                AppDestinations.START.route
        }
    NavHost(
        navController = navController,
        startDestination = destination,
        modifier = modifier,
    ) {
        composable(AppDestinations.START.route) {
            StartScreen(
                onNavigateToSignup = { navController.navigate(AppDestinations.SIGNUP.route) },
                onNavigateToLogin = { navController.navigate(AppDestinations.LOGIN.route) },
                viewModel = viewModel
            )
        }
        composable(AppDestinations.HOME.route) {
            HomeScreen(
                onNavigateToTransfer = { navController.navigate(AppDestinations.TRANSFERS.route) },
                onNavigateToMovements = { navController.navigate(AppDestinations.MOVEMENTS.route) },
                onNavigateToCards = { navController.navigate(AppDestinations.WALLET.route) },
                onNavigateToInvestments = { navController.navigate(AppDestinations.INVESTMENTS.route) },
                onNavigateToMakePayment = { navController.navigate(AppDestinations.MAKE_PAYMENT.route) },
                onNavigateToGenerateLink = { navController.navigate(AppDestinations.GENERATE_LINK.route) },
                onNavigateToPromotions = {},
                onNavigateToContacts = {},
                onNavigateToHelp = {},
                onNavigateToDeposit = { navController.navigate(AppDestinations.DEPOSIT.route) },
                viewModel = viewModel
            )
        }
        composable(AppDestinations.LOGIN.route) {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToSignup = {
                    navController.navigate(AppDestinations.SIGNUP.route) {
                        popUpTo(
                            AppDestinations.START.route
                        )
                    }
                },
                onNavigateToRecover = { navController.navigate(AppDestinations.RECOVER_PASSWORD.route) },
                viewModel = viewModel
            )
        }
        composable(AppDestinations.SIGNUP.route) {
            SignupScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToLogin = {
                    navController.navigate(AppDestinations.LOGIN.route) {
                        popUpTo(
                            AppDestinations.START.route
                        )
                    }
                },
                onNavigateToVerify = { mailAndPassword -> navController.navigate("${AppDestinations.VERIFY_SCREEN.route}/$mailAndPassword") },
                viewModel = viewModel
            )
        }
        composable(AppDestinations.RECOVER_PASSWORD.route) {
            RecoverPasswordScreen(
                onBackClick = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
        composable(
            "other/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ) {
            OtherScreen(it.arguments?.getInt("id"))
        }
        composable(AppDestinations.MOVEMENTS.route) {
            MovementsScreen(
                viewModel = viewModel,
                movements = viewModel.uiState.paymentHistory?.map {
                    it.asTransactionData(
                        viewModel.uiState.currentUser?.email ?: ""
                    )
                }
                    ?: emptyList(),
                onBackClick = { navController.popBackStack() })
        }
        composable(AppDestinations.TRANSFERS.route) {
            TransferScreen(
                recipients = emptyList(),
                onBackClick = { navController.popBackStack() },
                onRecipientClick = {},
                onNavigateToSendScreen = { email -> navController.navigate("${AppDestinations.SEND_PAYMENT.route}/$email") },
                viewModel = viewModel) //@TODO: onRecipientClick(Si apreto en un contacto que me lleve a transferirle, cambiarle el nombre) + addContact
        }
        composable(AppDestinations.WALLET.route) {
            WalletScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToAddCard = { navController.navigate(AppDestinations.ADD_CARD.route) },
                onNavigateToConfirmScreen = { message, id -> navController.navigate("${AppDestinations.CONFIRM_SCREEN.route}/$message/$id") },
                viewModel = viewModel
            )
        }
        composable(AppDestinations.MAKE_PAYMENT.route) {
            PayScreen(onBackClick = { navController.popBackStack() }, viewModel = viewModel)
        }
        composable(AppDestinations.GENERATE_LINK.route) {
            GenerateLinkScreen(onBackClick = { navController.popBackStack() }, viewModel = viewModel)
        }
        composable(AppDestinations.ADD_CARD.route) {
            AddCardScreen(onBackClick = { navController.popBackStack() }, viewModel = viewModel)
        }
        composable(AppDestinations.INVESTMENTS.route) {
            InvestmentScreen(onBackClick = { navController.popBackStack() }, viewModel = viewModel)
        }
        composable(
            "${AppDestinations.SEND_PAYMENT.route}/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            SendScreen(
                onBackClick = { navController.popBackStack() },
                backStackEntry.arguments?.getString("email") ?: "",
                viewModel = viewModel
            )
        }
        composable(AppDestinations.DEPOSIT.route) {
            DepositScreen(onBackClick = { navController.popBackStack() }, viewModel)
        }
        composable(
            "${AppDestinations.CONFIRM_SCREEN.route}/{message}/{cardId}",
            arguments = listOf(
                navArgument("message") { type = NavType.StringType },
                navArgument("cardId") { type = NavType.IntType })

        ) { backStackEntry ->
            ConfirmScreen(
                action = backStackEntry.arguments?.getString("message") ?: "",
                cardId = backStackEntry.arguments?.getInt("cardId") ?: 0,
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = viewModel)
        }
        composable(AppDestinations.DATA_SCREEN.route) {
            DataScreen(onBackClick = { navController.popBackStack() }, viewModel = viewModel)
        }
        composable(
            "${AppDestinations.VERIFY_SCREEN.route}/{mailAndPassword}",
            arguments = listOf(navArgument("mailAndPassword") { type = NavType.StringType })
        ) { backStackEntry ->
            VerifyScreen(
                onBackClick = { navController.popBackStack() },
                mailAndPassword = backStackEntry.arguments?.getString("mailAndPassword") ?: "",
                separator = ";",
                viewModel=viewModel
            )
        }
        composable(AppDestinations.SETTINGS.route){
            SettingsScreen(onBackClick = {navController.popBackStack()}, viewModel)
        }
        composable(AppDestinations.RESULT_SCREEN.route) {
            ResultScreen(
                onNavigateToRoute = {
                    if (viewModel.uiState.recoverConfirmed)
                        navController.navigate(AppDestinations.LOGIN.route)
                    else if(viewModel.uiState.paymentConfirmed)
                        navController.navigate(AppDestinations.HOME.route)
                },
                postNavigate = {
                    if(viewModel.uiState.recoverConfirmed)
                        {}
                    else if(viewModel.uiState.paymentConfirmed)
                        {viewModel.resetPaymentConfirmed()}
                    else if(viewModel.uiState.rechargeConfirmed)
                        {viewModel.resetRechargeConfirmed()}
                },
                success = true, // @TODO: ver
                viewModel = viewModel,
                msg =
                    if (viewModel.uiState.recoverConfirmed)
                        R.string.recover_confirmed
                    else if(viewModel.uiState.paymentConfirmed)
                        R.string.payment_confirmed
                    else if(viewModel.uiState.rechargeConfirmed)
                        R.string.recharge_confirmed
                    else
                        R.string.nummio,
                btnMsg =
                    if (viewModel.uiState.recoverConfirmed)
                        R.string.go_to_login
                    else if(viewModel.uiState.paymentConfirmed || viewModel.uiState.rechargeConfirmed)
                        R.string.go_to_home
                    else
                        R.string.nummio
            )
        }
    }
}