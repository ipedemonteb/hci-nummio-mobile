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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.data.model.Amount
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme
import androidx.compose.foundation.lazy.items

@Composable
fun DepositComponent(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel
) {
    var amount by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedMethod by remember { mutableStateOf(false) }
    if(viewModel.uiState.cards == null)
        viewModel.getCards()
    val options = listOf(stringResource(R.string.other_bank)) + (viewModel.uiState.cards?.map { stringResource(R.string.card_ending_in)+ " " + it.number.takeLast(4) } ?: emptyList())
    val decimalSeparator = stringResource(R.string.decimal_separator)

    var showAmountError by remember { mutableStateOf(false) }
    var amountError by remember { mutableStateOf("") }
    val MANDATORY_INPUT_ERROR = stringResource(R.string.mandatory_input_error)
    val INVALID_AMOUNT = stringResource(R.string.invalid_amount)
    val AMOUNT_POSITIVE = stringResource(R.string.amount_positive)

    fun depositHandler() {
        var amountString = amount
        amountString = amountString.replace(',', '.')
        showAmountError = false
        if(amountString.isEmpty()) {
            showAmountError = true
            amountError = MANDATORY_INPUT_ERROR
        } else {
            var amountDouble: Double = -1.0
            try {
                amountDouble = amountString.toDouble()
            } catch (e: Exception) {
                showAmountError = true
                amountError = INVALID_AMOUNT
            }
            if(amountDouble == 0.0) {
                showAmountError = true
                amountError = AMOUNT_POSITIVE
            }
        }
        if(!showAmountError) {
            viewModel.recharge(
                Amount(
                    amount = amountString.toDouble()
                )
            )
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Column {
                Row {
                    Text(
                        text = stringResource(R.string.recieve_amount),
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Row {
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it.filter{char -> char.isDigit() || char.toString() == decimalSeparator } },
                        label = { Text(text = stringResource(R.string.amount))},
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = showAmountError,
                        supportingText = { if (showAmountError) Text(amountError) }
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
                        text = selectedOption.ifEmpty { stringResource(R.string.method) },
                        color = if (selectedOption.isEmpty()) Color.Gray else Color.Black
                    )
                }
                if (expanded) {
                    Popup(
                        alignment = Alignment.TopStart,
                        onDismissRequest = { expanded = false }
                    ) {
                        LazyColumn (
                            modifier = Modifier
                                .background(Color.White)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(8.dp)
                                .width(160.dp)
                                .heightIn(max=210.dp)
                        ) {
                            items(options) { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOption = option
                                        expanded = false
                                        selectedMethod = true
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
                if(selectedMethod) {
                    Spacer(modifier = Modifier.height(42.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(modifier = Modifier.width(150.dp)) {
                            LowContrastBtn(
                                onClick = { onBackClick() },
                                stringResource(R.string.cancel_button)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))
                        Box(modifier = Modifier.width(150.dp)) {
                            HighContrastBtn(
                                onClick = { depositHandler() },
                                text = stringResource(R.string.confirm_button)
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun DepositComponentPreview() {
    NummioTheme {
        DepositComponent({}, viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication)))
    }
}