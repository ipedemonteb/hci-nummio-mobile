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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
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
    var amount by remember { mutableStateOf("") }
    if(viewModel.uiState.cards == null)
        viewModel.getCards()
    if(viewModel.uiState.currentBalance == null)
        viewModel.getBalance()
    val options = listOf("Balance") + (viewModel.uiState.cards?.map { stringResource(R.string.card_ending_in)+ " " + it.number.takeLast(4) } ?: emptyList())

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
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                HighContrastBtn( onClick = {found = true}, stringResource(R.string.search_payment) )
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
                    Row {
                        OutlinedTextField(
                            value = "$${stringResource(R.string.user_cvu)}",
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
                    if(selectedOption == "Balance") {
                        Text (
                            text = "${stringResource(R.string.my_balance)}: ${viewModel.uiState.currentBalance.toString()}",
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
                                    onClick = { found = true },
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
        MakePayment(viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication)))
    }
}