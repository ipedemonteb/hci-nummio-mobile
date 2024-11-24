package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.DepositComponent
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun DepositScreen(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel
){
    val uiState = viewModel.uiState
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        ,
        containerColor = Color.White,
        topBar = { TopBar(title = stringResource(R.string.deposit_screen), onBackClick = {onBackClick()}, viewModel = viewModel) }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if(uiState.isLandscape) 76.dp else {if (viewModel.uiState.isOver600dp) 50.dp else 30.dp}, vertical = 20.dp)
            .padding(paddingValues)
            .background(Color.White)
            .verticalScroll(
                enabled = uiState.isLandscape,
                state = rememberScrollState()
            )) {
            DepositComponent(onBackClick, viewModel)
        }
    }
}


@Preview(locale = "es")
@Composable
fun DepositScreenPreview() {
    NummioTheme {
        DepositScreen({}, viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication)))
    }
}