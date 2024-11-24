package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.data.model.PaymentRequest
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.DarkPurple

@Composable
fun TransferComponent(
    enteredEmail: String = "",
    viewModel: HomeViewModel
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedMethod by remember { mutableStateOf(false) }
    if (viewModel.uiState.cards == null) {
        //var cardId by remember { mutableStateOf(viewModel.uiState.cards!!.first().id) }
        viewModel.getCards()
    }
    val options = listOf("Balance") + (viewModel.uiState.cards?.map { stringResource(R.string.card_ending_in)+ " " + it.number.takeLast(4) } ?: emptyList())


    var cardId by remember { mutableIntStateOf(0) }
    var type by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
            Row {
                Column {
                    Row {
                        Text(
                            text = stringResource(R.string.send_amount),
                            color = DarkPurple,
                            fontSize = 16.sp
                        )
                    }

                    Row {
                        OutlinedTextField(
                            value = amount,
                            onValueChange = { amount = it.filter{char -> char.isDigit()} },
                            label = { Text(text = stringResource(R.string.amount))},
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Column {
                    Row {
                        Text(
                            text = stringResource(R.string.send_description),
                            color = DarkPurple,
                            fontSize = 16.sp
                        )
                    }
                    Row {
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text(text = stringResource(R.string.description)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            singleLine = true,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Column {
                    Row {
                        Text(
                            text = stringResource(R.string.select_method),
                            color = DarkPurple,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                            .padding(horizontal = 16.dp, vertical = 18.dp)
                            .clickable { expanded = !expanded },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (selectedOption.isEmpty()) stringResource(R.string.method) else selectedOption,
                            color = if (selectedOption.isEmpty()) Color.Gray else Color.Black
                        )
                    }
                    if (expanded) {
                        Popup(
                            alignment = Alignment.TopStart,
                            onDismissRequest = { expanded = false }
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(Color.White)
                                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                                    .width(160.dp)
                                    .heightIn(max=210.dp)
                            ) {
                                options.forEach { option ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedOption = option
                                            expanded = false
                                            selectedMethod = true
                                            if (option != "Balance") {
                                                cardId = viewModel.uiState.cards!!.find { it.number.takeLast(4) == option.takeLast(4) }!!.id!!
                                                type="CARD"
                                            }
                                            if(option == "Balance") {
                                                type="BALANCE"
                                            }

                                        },
                                        text = {
                                            Text(text = option)
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if(selectedOption == "Balance") {
                        Text (
                            text = "Balance: $"+viewModel.uiState.currentBalance.toString(),
                            color = Color.Gray
                        )
                    }
                    if(selectedMethod) {
                        Spacer(modifier = Modifier.height(42.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(modifier = Modifier.width(150.dp)) {
                                LowContrastBtn(
                                    onClick = { selectedMethod = !selectedMethod
                                              selectedOption = ""
                                              amount = ""
                                              description = ""},
                                    stringResource(R.string.cancel_button)
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))
                            Box(modifier = Modifier.width(150.dp)) {
                                HighContrastBtn(
                                    onClick = {viewModel.makePayment(PaymentRequest(amount = amount.toDouble(), description = description,
                                        type = type, receiverEmail = enteredEmail, cardId = cardId))},
                                    stringResource(R.string.confirm_button)
                                )
                            }
                        }
                    }

            }
        }
    }
}

/*
@Preview
@Composable
fun TransferComponentPreview() {
    NummioTheme {
        TransferComponent()
    }
}*/
