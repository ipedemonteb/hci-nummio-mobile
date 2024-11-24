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
fun VerifyScreen(
    onBackClick: () -> Unit,
    mailAndPassword: String,
    separator: String,
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState
    var codeSent by remember { mutableStateOf(false) }
    var canEdit by remember { mutableStateOf(true) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var date = remember { mutableStateOf(TextFieldValue("")) }
    var dateString = remember { mutableStateOf("") }

    var firstNameError by remember { mutableStateOf("") }
    var showFirstNameError by remember { mutableStateOf(false) }
    var lastNameError by remember { mutableStateOf("") }
    var showLastNameError by remember { mutableStateOf(false) }
    var birthdateError by remember { mutableStateOf("") }
    var showBirthdateError by remember { mutableStateOf(false) }
    val MANDATORY_INPUT_ERROR = stringResource(R.string.mandatory_input_error)
    val BIRTHDATE_INPUT_ERROR = stringResource(R.string.invalid_birthdate)

    val (email, password) = mailAndPassword.split(separator, limit = 2).let {
        it.getOrElse(0) { "" } to it.getOrElse(1) { "" }
    }

    fun checkFirstName(): Boolean {
        showFirstNameError = false
        if(firstName.isEmpty()) {
            firstNameError = MANDATORY_INPUT_ERROR
            showFirstNameError = true
            return false
        }
        return true
    }

    fun checkLastName(): Boolean {
        showLastNameError = false
        if(lastName.isEmpty()) {
            lastNameError = MANDATORY_INPUT_ERROR
            showLastNameError = true
            return false
        }
        return true
    }

    fun checkBirthdate(): Boolean {
        showBirthdateError = false
        if(date.value.text.length < 10) {
            birthdateError = BIRTHDATE_INPUT_ERROR
            showBirthdateError = true
            return false
        }
        return true
    }

    fun handleSignup() {
        canEdit = !canEdit

        val firstNameValidation = checkFirstName()
        val lastNameValidation = checkLastName()
        val birthdateValidation = checkBirthdate()

        if(firstNameValidation && lastNameValidation && birthdateValidation) {
            viewModel.register(
                firstName = firstName,
                lastName = lastName,
                birthDate = date.value.toString(),
                email = email,
                password = password
            )
            codeSent = !codeSent
        }
    }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        topBar = { TopBar(title = "Verify Account", onBackClick = {onBackClick()}, viewModel = viewModel) }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .padding(
                horizontal = if(uiState.isLandscape) 76.dp else 30.dp
            )
            .verticalScroll(
                enabled = uiState.isLandscape,
                state = rememberScrollState()
            )
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
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text(stringResource(R.string.name)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        readOnly = !canEdit,
                        isError = showFirstNameError,
                        supportingText = { if(showFirstNameError) Text(firstNameError) }
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
                        readOnly = !canEdit,
                        isError = showLastNameError,
                        supportingText = { if(showLastNameError) Text(lastNameError) }
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
                            color = Color.DarkGray,
                        )
                        // @TODO: agregar el snackbar con el error de birthdate
                    }
                }
                val (day, mon, yr) =date.value.text.split("/", limit = 3).let {
                    Triple(
                        it.getOrElse(0) { "" },
                        it.getOrElse(1) { "" },
                        it.getOrElse(2) { "" }
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
                Row(modifier = Modifier.padding(horizontal = 40.dp)) {
                    HighContrastBtn(
                        onClick = { handleSignup() },
                        text = stringResource(R.string.continue_btn)
                    )
                }
            } else {
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
                        value = code,
                        onValueChange = { code=it },
                        label = { Text(stringResource(R.string.code)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(modifier = Modifier.padding(horizontal = 40.dp)) {
                    HighContrastBtn(onClick = {viewModel.verifyUser(code)}, text = stringResource(R.string.continue_btn))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.padding(horizontal = 40.dp)) {
                    LowContrastBtn(onClick = { codeSent = !codeSent
                        canEdit = !canEdit
                        firstName = ""
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
        VerifyScreen(
            {}, "", "",
            viewModel = TODO()
        )
    }
}