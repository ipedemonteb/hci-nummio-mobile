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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun GeneratePayment() {
    var amount by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val date = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Column {
                Row {
                    Text(
                        text = stringResource(R.string.enter_amount),
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { input ->
                            val sanitizedInput = input.replace("[^\\d,]".toRegex(), "")
                            if (sanitizedInput.count { it == '.' } <= 1) {
                                amount = sanitizedInput
                            }
                        },
                        placeholder = { Text(text = stringResource(R.string.amount), color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions.Default
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Column {
                Row {
                    Text(
                        text = stringResource(R.string.enter_expiry_date),
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

                    ) {
                        DatePicker(date = date.value, onInputChange = {input -> date.value = input})
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Column {
                Row {
                    Text(
                        text = stringResource(R.string.check_cvu),
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
            Box(modifier = Modifier.padding(horizontal = 60.dp)) {
                HighContrastBtn(onClick = {}, stringResource(R.string.generate_link))
            }
        }

    }
}

@Preview(showBackground = true, locale = "es")
@Composable
fun GeneratePaymentPreview() {
    NummioTheme {
        GeneratePayment()
    }
}