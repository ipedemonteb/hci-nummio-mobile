package ar.edu.itba.nummio

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.nummio.ui.component.BottomBar
import ar.edu.itba.nummio.ui.theme.NummioTheme
import ar.edu.itba.nummio.ui.component.Header
import ar.edu.itba.nummio.ui.home.HomeUiState
import ar.edu.itba.nummio.ui.navigation.AppDestinations
import ar.edu.itba.nummio.ui.navigation.AppNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NummioApp() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NummioTheme {
        Scaffold(
            topBar = { if (currentRoute == AppDestinations.HOME.route) {
                Header(pfp = R.drawable.pfp, profileName = R.string.profileName)
            }},
            bottomBar = {
                if (currentRoute != AppDestinations.START.route && currentRoute != AppDestinations.LOGIN.route && currentRoute != AppDestinations.SIGNUP.route) {
                    BottomBar(
                        modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
                        onNavigateToHome = {navController.navigate(AppDestinations.HOME.route){popUpTo(AppDestinations.HOME.route)} },
                        onNavigateToNotifications = {}, //@TODO
                        onNavigateToQRScan = {} //@TODO
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            AppNavHost(navController = navController,
                modifier = Modifier.padding(innerPadding),
                isAuthenticated = HomeUiState().isAuthenticated)
        }
    }
}

//@PreviewScreenSizes
@Preview(locale = "es")
@Composable
fun NummioAppPreview() {
    NummioApp()
}