package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.AddCardComponent
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.NummioTheme


@Composable
fun AddCardScreen(
    onBackClick: () -> Unit
) {
    val cardNumber = remember { mutableStateOf("") }
    val cardHolder = remember { mutableStateOf("") }
    val expiryMonth = remember { mutableStateOf("") }
    val expiryYear = remember { mutableStateOf("") }
    val cvv = remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(stringResource(R.string.add_card_title), onBackClick = {onBackClick()}) }
        ) {
        paddingValues ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)
            ) {
                AddCardComponent(
                    cardNumber = cardNumber,
                    cardHolder = cardHolder,
                    expiryMonth = expiryMonth,
                    expiryYear = expiryYear,
                    cvv = cvv
                )
            }
            Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
                HighContrastBtn( {}, stringResource(R.string.add_card_title))
            }
            Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
                LowContrastBtn( {}, stringResource(R.string.cancel_button))
            }
        }
    }
}

@Preview(locale = "es")
@Composable
fun AddCardScreenPreview() {
    NummioTheme {
        AddCardScreen({})
    }
}