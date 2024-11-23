package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.component.WalletComponent
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme
import kotlinx.coroutines.Job

@Composable
fun WalletScreen(
    wasConfirmed: Boolean = false,
    onBackClick: () -> Unit,
    onNavigateToAddCard: () -> Unit,
    onNavigateToConfirmScreen: (String, Job) -> Unit,
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState
    var deleteCard by remember { mutableStateOf(false) }
    val actionIcon: Pair<@Composable () -> Unit, () -> Unit> = Pair(
        {
            Icon(
                painter = painterResource(R.drawable.delete),
                contentDescription = null,
                tint = DarkPurple,
                modifier = Modifier.size(32.dp)
            )
        },
        { deleteCard = !deleteCard }
    )

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        topBar = { TopBar(title = stringResource(R.string.wallet_title), onBackClick = { onBackClick() }, actionIcon = actionIcon)}
    ) {
        paddingValues->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(paddingValues)
            .background(Color.White)
            .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.padding(vertical = 0.dp)
            ) {
                WalletComponent(deleteCard = deleteCard, onNavigateToAddCard = onNavigateToAddCard, onNavigateToConfirmScreen = onNavigateToConfirmScreen, viewModel)
            }
        }
    }
}

/*
@Preview
@Composable
fun WalletScreenPreview() {
    NummioTheme {
        WalletScreen(onBackClick = {}, onNavigateToAddCard = {}, onNavigateToConfirmScreen = {}, viewModel = viewModel(factory = HomeViewModel.provideFactory(
            LocalContext.current.applicationContext as MyApplication))
        )
    }
}*/
