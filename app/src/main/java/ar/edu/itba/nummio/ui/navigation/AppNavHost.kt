package ar.edu.itba.nummio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ar.edu.itba.nummio.ui.home.HomeScreen
import ar.edu.itba.nummio.ui.home.LoginScreen
import ar.edu.itba.nummio.ui.home.OtherScreen
import ar.edu.itba.nummio.ui.home.SignupScreen
import ar.edu.itba.nummio.ui.home.StartScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.START.route // @TODO: tiene ser segun si esta loggedIn o no
) {
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
                onNavigateToOtherScreen = { id -> navController.navigate("other/$id") }
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
        composable(
            "other/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ) {
            OtherScreen(it.arguments?.getInt("id"))
        }
    }
}