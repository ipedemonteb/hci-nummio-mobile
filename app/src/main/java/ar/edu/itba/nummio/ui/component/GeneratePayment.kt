package ar.edu.itba.nummio.ui.component

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.data.model.PaymentRequest
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun GeneratePayment(viewModel:HomeViewModel) {
    var amount by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var description = remember { mutableStateOf("") }
    val clicked = remember { mutableStateOf(false) }
    val current = LocalContext.current
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
                        text = stringResource(R.string.send_description),
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    /*OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = stringResource(R.string.description)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        singleLine = true,
                    )*/

                    OutlinedTextField(
                        value =description.value,
                        onValueChange = { description.value = it },
                        label = { Text(text = stringResource(R.string.description)) },modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        singleLine = true

                    )


                }
            }
        }
        fun shareText(context: android.content.Context, text: String) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, viewModel.uiState.latestGeneratedLink)
            }

            // Show the chooser to the user
            context.startActivity(Intent.createChooser(intent, "Share via"))
        }
        if(clicked.value){
        Row (modifier = Modifier.padding(top=30.dp)){
            CopyableTextInput(viewModel.uiState.latestGeneratedLink, editable = false)
        }}
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.padding(horizontal = if (viewModel.uiState.isOver600dp) 200.dp else 60.dp)) {
                HighContrastBtn(onClick = {viewModel.makePayment(PaymentRequest(amount=amount.toDouble(), type="LINK", description = description.value));
                    clicked.value=true
                    shareText(current, viewModel.uiState.latestGeneratedLink)}, stringResource(R.string.generate_link))
            }
        }

    }
}

@Preview(showBackground = true, locale = "es")
@Composable
fun GeneratePaymentPreview() {
    NummioTheme {
        GeneratePayment(
            viewModel = TODO()
        )
    }
}