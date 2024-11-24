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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.LightPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun MakePayment(
    viewModel: HomeViewModel
) {
    var link by remember { mutableStateOf("") }
    var found by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedMethod by remember { mutableStateOf(false) }
    val options = listOf("Balance") + (viewModel.uiState.cards?.map { stringResource(R.string.card_ending_in)+ " " + it.number.takeLast(4) } ?: emptyList())
    var cardId by remember { mutableIntStateOf(0) }
    var type by remember { mutableStateOf("") }

    if (viewModel.uiState.cards == null) {
        viewModel.getCards()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Column {
                Row {
                    Text(
                        text = stringResource(R.string.enter_link),
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    OutlinedTextField(
                        value = link,
                        onValueChange = { link = it },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.link_name),
                                color = Color.Gray
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        singleLine = true
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        if(!found) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (viewModel.uiState.isOver600dp) 200.dp else 40.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                HighContrastBtn( onClick = {found = true;viewModel.getPaymentByLink(link)}, stringResource(R.string.search_payment) )
            }
        }
        else {
            Row {
                Column {
                    Row {
                        Text(
                            text = stringResource(R.string.amount_to_pay),
                            color = DarkPurple,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    //Text(viewModel.uiState.currentPayment?.toString()?:"no")
                    Row {
                        OutlinedTextField(
                            value = "$"+(viewModel.uiState.currentPayment?.amount?:0.0).toString(),
                            onValueChange = { },
                            readOnly = true,
                            placeholder = { Text(text = "$${stringResource(R.string.user_cvu)}") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            singleLine = true
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
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
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
                            text = "Balance: "+viewModel.uiState.currentBalance?.toString(),
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
                                    onClick = { found = false },
                                    stringResource(R.string.cancel_button)
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))
                            Box(modifier = Modifier.width(150.dp)) {
                                HighContrastBtn(
                                    onClick = { found = true
                                              viewModel.payByLink(link, type) },
                                    stringResource(R.string.confirm_button)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true, locale = "es")
@Composable
fun MakePaymentPreview() {
    NummioTheme {
        MakePayment(
            viewModel = TODO()
        )
    }
}