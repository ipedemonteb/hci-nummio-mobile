package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun AddCardComponent(
    cardNumber: MutableState<String>,
    cardHolder: MutableState<String>,
    expiryMonth: MutableState<String>,
    expiryYear: MutableState<String>,
    cvv: MutableState<String>
) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(

        ) {
            Row {
                Text(
                    text = stringResource(R.string.insert_number),
                    color = DarkPurple,
                    fontSize = 16.sp
                )
            }
            Row {
                OutlinedTextField(
                    value = cardNumber.value,
                    onValueChange = {
                        cardNumber.value = it.filter { char -> char.isDigit() }
                    },
                    label = { Text(stringResource(R.string.card_number))},
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                    )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(R.string.insert_holder),
                    color = DarkPurple,
                    fontSize = 16.sp
                )
            }
            Row {
                OutlinedTextField(
                    value = cardHolder.value,
                    onValueChange = { cardHolder.value = it },
                    label = { Text(stringResource(R.string.holder)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(R.string.insert_expiry_date),
                    color = DarkPurple,
                    fontSize = 16.sp
                )
            }
            Row {
                OutlinedTextField(
                    value = expiryMonth.value,
                    onValueChange = {
                        expiryMonth.value = it.filter { char -> char.isDigit() }.take(2)
                    },
                    label = { Text(stringResource(R.string.mm)) },
                    modifier = Modifier.width(70.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                    )
                Text(
                    text = stringResource(R.string.slash),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium
                )

                OutlinedTextField(
                    value = expiryYear.value,
                    onValueChange = {
                        expiryYear.value = it.filter { char -> char.isDigit() }.take(4)
                    },
                    label = { Text(stringResource(R.string.yyyy)) },
                    modifier = Modifier.width(80.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(R.string.insert_cvv),
                    color = DarkPurple,
                    fontSize = 16.sp
                )
            }
            Row {
                OutlinedTextField(
                    value = cvv.value,
                    onValueChange = {
                        cvv.value = it.filter { char -> char.isDigit() }.take(4)
                    },
                    label = { Text(stringResource(R.string.cvv)) },
                    modifier = Modifier.width(80.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                )
            }
        }
    }
}

@Preview(locale = "es")
@Composable
fun AddCardComponentPreview() {
    val cardNumber = remember { mutableStateOf("") }
    val cardHolder = remember { mutableStateOf("") }
    val expiryMonth = remember { mutableStateOf("") }
    val expiryYear = remember { mutableStateOf("") }
    val cvv = remember { mutableStateOf("") }
    NummioTheme {
        AddCardComponent(
            cardNumber = cardNumber,
            cardHolder = cardHolder,
            expiryMonth = expiryMonth,
            expiryYear = expiryYear,
            cvv = cvv
        )
    }
}