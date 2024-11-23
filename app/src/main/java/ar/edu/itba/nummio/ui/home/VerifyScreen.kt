package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.DatePicker
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun VerifyScreen() {
    val landScape = false
    var codeSent by remember { mutableStateOf(false) }
    var canEdit by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var date = remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        topBar = { TopBar(title = "Verify Account", onBackClick = { }) }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = if (landScape/*uiState.isLandscape*/) 100.dp else 30.dp
            )
            .verticalScroll(
                enabled = landScape, //uiState.isLandscape,
                state = rememberScrollState()
            )
            .padding(paddingValues)
        ) {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                Text(
                    text = stringResource(R.string.validate_msg),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
            }
            if(!codeSent) {
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(
                        text = stringResource(R.string.insert_name),
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Row {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(stringResource(R.string.name)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        readOnly = !canEdit
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Text(
                        text = stringResource(R.string.insert_last_name),
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Row {
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text(stringResource(R.string.last_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        readOnly = !canEdit
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Text(
                        text = stringResource(R.string.insert_birth_date),
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))

                    ) {
                        DatePicker(
                            date = date.value,
                            onInputChange = { input -> date.value = input },
                            enabled = canEdit,
                            color = Color.DarkGray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
                Row(modifier = Modifier.padding(horizontal = 40.dp)) {
                    HighContrastBtn(onClick = {
                        codeSent = !codeSent
                        canEdit = !canEdit
                    }, text = stringResource(R.string.continue_btn))
                }
            }
            else {
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(
                        text = stringResource(R.string.enter_code),
                        color = DarkPurple,
                        fontSize = 16.sp
                    )
                }
                Row {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        label = { Text(stringResource(R.string.code)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(modifier = Modifier.padding(horizontal = 40.dp)) {
                    HighContrastBtn(onClick = {  }, text = stringResource(R.string.continue_btn))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.padding(horizontal = 40.dp)) {
                    LowContrastBtn(onClick = { codeSent = !codeSent
                        canEdit = !canEdit
                        name = ""
                         lastName = ""
                         date.value = TextFieldValue("")}, text = stringResource(R.string.cancel_button))
                }
            }
        }
    }

}

@Preview(locale = "es")
@Composable
fun VerifyScreenPreview() {
    NummioTheme {
        VerifyScreen()
    }
}