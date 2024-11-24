package ar.edu.itba.nummio

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.nummio.ui.component.BottomBar
import ar.edu.itba.nummio.ui.theme.NummioTheme
import ar.edu.itba.nummio.ui.component.Header
import ar.edu.itba.nummio.ui.navigation.AppDestinations
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.home.windowScreenWidth
import ar.edu.itba.nummio.ui.navigation.AppNavHost


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NummioApp(
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val configuration = LocalConfiguration.current
    val isLandscape = remember(configuration.orientation) {
        configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
    }

    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val windowSizeClass = when {
        screenWidth < 600 -> windowScreenWidth.COMPACT
        else -> windowScreenWidth.EXPANDED

    }
    viewModel.setFormFactor(configuration.screenWidthDp >= 600 && configuration.screenHeightDp >= 600)

    LaunchedEffect(isLandscape) {
        viewModel.updateOrientation(isLandscape)
    }

    NummioTheme {
        Scaffold(
            topBar = { if (currentRoute == AppDestinations.HOME.route) {
                Header(pfp = R.drawable.pfp, profileName = R.string.profileName, onNavigateToSettings = {navController.navigate(AppDestinations.SETTINGS.route)}, viewModel = viewModel)
            }},
            bottomBar = {
                if (currentRoute == AppDestinations.HOME.route || currentRoute == AppDestinations.DATA_SCREEN.route) {
                    BottomBar(modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
                        onNavigateToHome = {navController.navigate(AppDestinations.HOME.route){
                            popUpTo(AppDestinations.HOME.route)
                            launchSingleTop = true
                            restoreState = true //Sacar?
                        }},
                        onNavigateToData = {navController.navigate(AppDestinations.DATA_SCREEN.route){
                            popUpTo(AppDestinations.HOME.route)
                            launchSingleTop = true
                            restoreState = true
                        }},
                        onNavigateToQRScan = {}, //@TODO
                        viewModel = viewModel
                        )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel,
                windowSizeClass = windowSizeClass
            )
        }
    }
}

//@PreviewScreenSizes
@Preview(locale = "es")
@Composable
fun NummioAppPreview() {
    NummioApp()
}