package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun ResultScreen(
    onNavigateToRoute: () -> Unit,
    success: Boolean = true,
    viewModel: HomeViewModel,
    msg: Int,
    btnMsg: Int
) {
    Scaffold(
        topBar = { TopBar(title = stringResource(R.string.operation_result_title),
            onBackClick = { },
            viewModel = viewModel,
            arrowEnable = false
            )}
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(horizontal = if(viewModel.uiState.isLandscape) 76.dp else {if (viewModel.uiState.isOver600dp) 50.dp else 30.dp})
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text (
                    text = stringResource(if(success) R.string.operation_success else R.string.operation_mistake, stringResource(msg)),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row(modifier = Modifier.padding(top = 30.dp)) {
                LowContrastBtn(onClick = { onNavigateToRoute() }, text = stringResource(btnMsg))
            }
        }
    }
}

@Preview
@Composable
fun ResultScreenPreview() {
    NummioTheme {
        ResultScreen(
            {},
            true,
            viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication)),
            R.string.recover_confirmed,
            R.string.go_to_login
        )
    }
}
