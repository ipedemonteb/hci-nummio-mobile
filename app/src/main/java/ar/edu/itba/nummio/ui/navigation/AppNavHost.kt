package ar.edu.itba.nummio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ar.edu.itba.nummio.ui.home.HomeScreen
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.home.LoginScreen
import ar.edu.itba.nummio.ui.home.OtherScreen
import ar.edu.itba.nummio.ui.home.RecoverPasswordScreen
import ar.edu.itba.nummio.ui.home.SignupScreen
import ar.edu.itba.nummio.ui.home.StartScreen

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
        composable("start") {
            StartScreen(
                onNavigateToRoute = { route -> navController.navigate(route) },
                viewModel = viewModel
            )
        }
        composable("home") {
            HomeScreen(
                onNavigateToOtherScreen = { route -> navController.navigate(route) },
                viewModel = viewModel
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
    }
}