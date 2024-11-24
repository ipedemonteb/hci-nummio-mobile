package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.data.model.Card
import ar.edu.itba.nummio.data.model.CardType
import ar.edu.itba.nummio.ui.component.AddCardComponent
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme
import ar.edu.itba.nummio.ui.component.TopBar

@Composable
fun AddCardScreen(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel
) {
    val cardNumber = remember { mutableStateOf("") }
    val cardHolder = remember { mutableStateOf("") }
    val expiryMonth = remember { mutableStateOf("") }
    val expiryYear = remember { mutableStateOf("") }
    val cvv = remember { mutableStateOf("") }

    var cardNumberError by remember { mutableStateOf("") }
    var showCardNumberError by remember { mutableStateOf(false) }
    var cardHolderError by remember { mutableStateOf("") }
    var showCardHolderError by remember { mutableStateOf(false) }
    var monthError by remember { mutableStateOf("") }
    var showMonthError by remember { mutableStateOf(false) }
    var yearError by remember { mutableStateOf("") }
    var showYearError by remember { mutableStateOf(false) }
    var cvvError by remember { mutableStateOf("") }
    var showCvvError by remember { mutableStateOf(false) }
    val MANDATORY_INPUT_ERROR = stringResource(R.string.mandatory_input_error)
    val NUMBER_ERROR = stringResource(R.string.invalid_card_number)

    fun addCardHandler() {
        val number = cardNumber.value
        val fullName = cardHolder.value
        val expirationDate = "${expiryMonth.value}/${expiryYear.value}"
        val cvvParam = cvv.value
        showCardNumberError = false
        showCardHolderError = false
        showMonthError = false
        showYearError = false
        showCvvError = false
        if(number.isEmpty()) {
            showCardNumberError = true
            cardNumberError = MANDATORY_INPUT_ERROR
        } else if(number.length != 15 && number.length != 16 && number.length != 19) {
            showCardNumberError = true
            cardNumberError = NUMBER_ERROR
        }
        if(fullName.isEmpty()) {
            showCardHolderError = true
            cardHolderError = MANDATORY_INPUT_ERROR
        }
        if(expiryMonth.value.isEmpty()) {
            showMonthError = true
            monthError = MANDATORY_INPUT_ERROR
        }
        if(expiryYear.value.isEmpty()) {
            showYearError = true
            yearError = MANDATORY_INPUT_ERROR
        }
        if(cvvParam.isEmpty()) {
            showCvvError = true
            cvvError = MANDATORY_INPUT_ERROR
        }

        if(!showCardNumberError && !showCardHolderError && !showMonthError && !showYearError && !showCvvError) {
            viewModel.addCard(
                Card(
                    number = number,
                    expirationDate = expirationDate,
                    fullName = fullName,
                    cvv = cvvParam,
                    type = CardType.CREDIT,
                    createdAt = null,
                    updatedAt = null
                )
            )
            onBackClick()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(stringResource(R.string.add_card_title), onBackClick = {onBackClick()}, viewModel = viewModel) }
        ) {
        paddingValues ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if(viewModel.uiState.isLandscape) 76.dp else {if (viewModel.uiState.isOver600dp) 50.dp else 30.dp})
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)
            ) {
                AddCardComponent(
                    cardNumber = cardNumber,
                    cardNumberError = cardNumberError,
                    showCardNumberError = showCardNumberError,
                    cardHolder = cardHolder,
                    showCardHolderError = showCardHolderError,
                    cardHolderError = cardHolderError,
                    expiryMonth = expiryMonth,
                    monthError = monthError,
                    showMonthError = showMonthError,
                    expiryYear = expiryYear,
                    yearError = yearError,
                    showYearError = showYearError,
                    cvv = cvv,
                    showCvvError = showCvvError,
                    cvvError = cvvError
                )
            }
            Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = if (viewModel.uiState.isOver600dp) 200.dp else  20.dp)) {
                HighContrastBtn({ addCardHandler() }, stringResource(R.string.add_card_title))
            }
            Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = if (viewModel.uiState.isOver600dp) 200.dp else 20.dp)) {
                LowContrastBtn({ onBackClick() }, stringResource(R.string.cancel_button))
            }
        }
    }
}

@Preview(locale = "es")
@Composable
fun AddCardScreenPreview() {
    NummioTheme {
        AddCardScreen({}, viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication)))
    }
}