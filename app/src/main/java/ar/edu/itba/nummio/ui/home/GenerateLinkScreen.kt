package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.GeneratePayment
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun GenerateLinkScreen(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        topBar = { TopBar(title = stringResource(R.string.generate_page), onBackClick = {onBackClick()}) }
    ) {
        paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.padding(vertical = 30.dp)
            ) {
                GeneratePayment(viewModel)
            }
        }
    }
}

/*
@Preview
@Composable
fun GenerateLinkScreenPreview() {
    NummioTheme {
        GenerateLinkScreen(
            {},
            viewModel = TODO()
        )
    }
}*/
