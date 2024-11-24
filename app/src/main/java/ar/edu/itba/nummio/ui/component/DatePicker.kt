package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.itba.nummio.R

@Composable
fun DatePicker(
    date: TextFieldValue,
    onInputChange: (TextFieldValue) -> Unit,
    enabled: Boolean = true,
    color: Color = Color.Gray,
    isError: Boolean,
    supportingText:  @Composable() (() -> Unit)? = null
) {
    val placeholder = stringResource(R.string.date_placeholder)
    val maxLength = stringResource(R.string.date_placeholder).length

    OutlinedTextField(
        value = date,
        onValueChange = { input ->
            if(enabled) {
                val sanitizedInput = input.text.replace(Regex("[^0-9]"), "")

                val formattedInput = buildString {
                    sanitizedInput.forEachIndexed { index, char ->
                        if ((index == 2 || index == 4) && length < maxLength) append("/")
                        append(char)
                    }
                }

                val finalInput = formattedInput.take(maxLength)
                val cursorPosition = input.selection.start + (finalInput.length - input.text.length)

                onInputChange(
                    TextFieldValue(
                        text = finalInput,
                        selection = TextRange(cursorPosition.coerceIn(0, finalInput.length))
                    )
                )
            }
        },
        supportingText = supportingText,
        isError = isError,
        modifier = Modifier
            .fillMaxWidth().background(Color.Transparent),
        placeholder = { Text(text = placeholder.toString(), color = color) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        singleLine = true,
        readOnly = !enabled,
        colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
    )
}
/*
@Preview
@Composable
fun DatePickerPreview() {
    var dateAux= remember { mutableStateOf(TextFieldValue("")) }
    DatePicker(date = dateAux.value, onInputChange = {input -> dateAux.value = input})
}
*/