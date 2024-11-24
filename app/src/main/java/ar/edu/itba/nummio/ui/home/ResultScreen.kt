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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar

@Composable
fun ResultScreen(
    onNavigateToRoute: () -> Unit,
    success: Boolean = true,
    viewModel: HomeViewModel,
    msg: String = "",
    btn_msg: String = ""
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
                    text = if(success) stringResource(R.string.operation_success, msg) else stringResource(R.string.operation_mistake, msg),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row {
                LowContrastBtn(onClick = { onNavigateToRoute() }, text = "")
            }
        }
    }
}

/*
@Preview
@Composable
fun ResultScreenPreview() {
    NummioTheme {
        ResultScreen({})

    }
}*/
