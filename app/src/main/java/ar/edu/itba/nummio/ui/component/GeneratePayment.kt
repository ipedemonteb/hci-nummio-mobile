package ar.edu.itba.nummio.ui.component

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.data.model.Amount
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
    var showAmountError by remember { mutableStateOf(false) }
    var amountError by remember { mutableStateOf("") }
    var showDescriptionError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf("") }
    val MANDATORY_INPUT_ERROR = stringResource(R.string.mandatory_input_error)
    val INVALID_AMOUNT = stringResource(R.string.invalid_amount)
    val AMOUNT_POSITIVE = stringResource(R.string.amount_positive)

    fun handleGeneratePayment() {
        var amountString = amount
        amountString = amountString.replace(',', '.')
        showAmountError = false
        showDescriptionError = false
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
        if(description.value.isEmpty()){
            showDescriptionError = true
            descriptionError = MANDATORY_INPUT_ERROR
        }

        if(!showAmountError && !showDescriptionError) {
            viewModel.makePayment(
                PaymentRequest(
                    amount = amountString.toDouble(),
                    type = "LINK",
                    description = description.value
                )
            );
            clicked.value = true
        }
    }

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
                        keyboardActions = KeyboardActions.Default,
                        isError = showAmountError,
                        supportingText = { if(showAmountError) Text(amountError) }
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
                        value = description.value,
                        onValueChange = { description.value = it },
                        label = { Text(text = stringResource(R.string.description)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        singleLine = true,
                        isError = showDescriptionError,
                        supportingText = { if(showDescriptionError) Text(descriptionError) }
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
            Row(modifier = Modifier.padding(top=30.dp)){
                Text(
                    text = stringResource(R.string.generated_link),
                    color = DarkPurple,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                SimpleCopyableTextInput(viewModel.uiState.latestGeneratedLink, editable = false)
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.padding(horizontal = if (viewModel.uiState.isOver600dp) 200.dp else {if(viewModel.uiState.isLandscape) 140.dp else 60.dp})) {
                if (!clicked.value) {
                    HighContrastBtn(
                        onClick = { handleGeneratePayment() }, stringResource(R.string.generate_link)
                    )
                }
                else {
                    Text(text = stringResource(R.string.share_link), modifier = Modifier.clickable { shareText(current, viewModel.uiState.latestGeneratedLink) }, color = DarkPurple,
                        textDecoration = TextDecoration.Underline)
                }
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