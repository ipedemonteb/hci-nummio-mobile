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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import java.util.Date

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

    fun addCardHandler() {
        val number = cardNumber.value
        val fullName = cardHolder.value
        val expirationDate = "${expiryMonth.value}/${expiryYear.value}"
        val cvvParam = cvv.value

        viewModel.addCard(Card(
            number = number,
            expirationDate = expirationDate,
            fullName = fullName,
            cvv = cvvParam,
            type = CardType.CREDIT, // @TODO: ver como determinar el tipo de tarjeta
            createdAt = null,
            updatedAt = null
        ))
        onBackClick()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_left),
                            contentDescription = null,
                            tint = DarkPurple,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .size(40.dp)
                                .offset(x = (-10).dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.add_card_title),
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = DarkPurple
                    )
                }
            }
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
                HighContrastBtn({ addCardHandler() }, stringResource(R.string.add_card_title))
            }
            Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
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