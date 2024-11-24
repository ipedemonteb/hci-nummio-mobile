import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.component.Contact
import ar.edu.itba.nummio.ui.component.SearchBar
import androidx.compose.ui.res.stringResource
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.home.HomeViewModel

data class ContactData(
    val name: String,
    val bank: String,
    val profileImage: Any,
)

@Composable
fun TransferScreen(
    recipients: List<ContactData>,
    onRecipientClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onNavigateToSendScreen: (String) -> Unit,
    viewModel: HomeViewModel
) {
    var searchText by remember { mutableStateOf("") }
    var cvuText by remember { mutableStateOf("") }
    val uiState = viewModel.uiState
    val emailHasErrors by remember(cvuText) {
        derivedStateOf {
            if (cvuText.isNotEmpty()) {
                !android.util.Patterns.EMAIL_ADDRESS.matcher(cvuText).matches()
            } else {
                false
            }
        }
    }
    Column (modifier = Modifier
        .padding(start = 10.dp, end = 10.dp)
        ,
    ){

        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(R.string.transfer_option),
                    onBackClick = onBackClick,
                    viewModel = viewModel)
            }
        )
        { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = if(uiState.isLandscape) 76.dp else 20.dp)
                    .verticalScroll(
                        enabled = uiState.isLandscape,
                        state = rememberScrollState()
                    )
                    .heightIn(max = 600.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = cvuText,
                        onValueChange = { cvuText = it },
                        placeholder = { Text(text = stringResource(R.string.searchAliasOrCVU_msg)) },
                        maxLines = 1,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                        ),
                        //isError = emailHasErrors,
                        //supportingText = {if (emailHasErrors) Text(stringResource(R.string.invalid_mail_format))}
                    )
                    IconButton(
                        onClick = { onNavigateToSendScreen(cvuText) },
                        modifier = Modifier
                            .size(48.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_right),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = DarkPurple
                        )
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 15.dp)
                ) {
                    Text(
                        text = stringResource(R.string.recents_msg),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    SearchBar(
                        onSearchClicked = {}, modifier = Modifier
                            .width(160.dp)
                            .height(30.dp),
                        input = searchText,
                        onInputChange = { searchText = it }
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(1f, false)
                        .fillMaxHeight()
                        .width(360.dp)
                        .padding(bottom = 20.dp)
                ) {
                    items(recipients) { recipient ->
                        Contact(
                            name = recipient.name,
                            bank = recipient.bank,
                            profileImage = Icons.Default.Person,
                            modifier = Modifier.clickable { onRecipientClick(recipient.name) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransferScreenPreview() {
    val recipients = listOf(
        ContactData(
            name = "Franco Colapinto",
            bank = "Cuenta Galicia",
            profileImage = Icons.Default.Person,
        ),
        ContactData(
            name = "Fernando Alonso",
            bank = "Cuenta Santander",
            profileImage = Icons.Default.Person,
        ),
        ContactData(
            name = "Carlos Sainz",
            bank = "Cuenta Nummio",
            profileImage = Icons.Default.Person,
        ),
        ContactData(
            name = "Ayrton Senna",
            bank = "Cuenta Nummio",
            profileImage = Icons.Default.Person,
        ),
        ContactData(
            name = "Michael Schumacher",
            bank = "Cuenta Galicia",
            profileImage = Icons.Default.Person,
        ),
        ContactData(
            name = "Lewis Hamilton",
            bank = "Cuenta Santander",
            profileImage = Icons.Default.Person,
        ),
        ContactData(
            name = "Sebastian Vettel",
            bank = "Cuenta Nummio",
            profileImage = Icons.Default.Person,
        )
    )

/*
    TransferScreen(
        recipients = recipients,
        onRecipientClick = {},
        onBackClick = {},
        onNavigateToSendScreen = {}
    )*/
}
