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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.LightPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun GeneratePayment() {
    var amount by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Varios", "Consumo", "Venta")

    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Column {
                Row {
                    Text(
                        text = "Insert the amount to charge:",
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        placeholder = { Text(text = "Amount", color = Color.Gray) },
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
                        text = "Select the concept of the transaction:",
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                            .clickable { expanded = !expanded }
                            .padding(horizontal = 16.dp, vertical = 18.dp)
                    ) {
                        Text(
                            text = if (selectedOption.isEmpty()) "Concept" else selectedOption,
                            color = if (selectedOption.isEmpty()) Color.Gray else Color.Black
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .background(Color.White)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        ) {
                            options.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOption = option
                                        expanded = false
                                    },
                                    text = {
                                        Text(text = option, color = Color.Gray)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Column {
                Row {
                    Text(
                        text = "CVU to deposit:",
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    OutlinedTextField(
                        value =stringResource(R.string.user_cvu),
                        onValueChange = { },
                        readOnly = true,
                        placeholder = { Text(text = stringResource(R.string.user_cvu)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        singleLine = true
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = DarkPurple,
                    disabledContainerColor = DarkPurple,
                    disabledContentColor = LightPurple
                ),
                modifier = Modifier.width(180.dp).border(1.dp, DarkPurple, RoundedCornerShape(30.dp))
            ) {
                Text (
                    text = "Generate Link",
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GeneratePaymentPreview() {
    NummioTheme {
        GeneratePayment()

    }
}