import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.component.Contact
import ar.edu.itba.nummio.ui.component.SearchBar
import androidx.compose.ui.res.stringResource
import ar.edu.itba.nummio.R

data class ContactData(
    val name: String,
    val bank: String,
    val profileImage: Any,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransferScreen(
    recipients: List<ContactData>,
    onRecipientClick: (String) -> Unit,
    onBackClick: () -> Unit,
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var cvuText by remember { mutableStateOf("") }
    Column (modifier = Modifier.padding(start = 10.dp, end = 10.dp)){
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(R.string.transfer_option),
                    onBackClick = onBackClick,
                    actionIcon = Pair(
                        {
                            Icon(
                                imageVector = Icons.Default.Add,
                                    contentDescription = "Settings",
                                tint = DarkPurple
                            )
                        },
                        onBackClick
                    )
                )
            }
        )
        { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                TextField(
                    value = cvuText,
                    onValueChange = { cvuText = it },
                    placeholder = { Text(text = stringResource(R.string.searchAliasOrCVU_msg)) },
                    modifier = Modifier

                        .padding(16.dp)
                        .width(300.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        ),

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 10.dp, end = 15.dp)
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


    TransferScreen(
        recipients = recipients,
        onRecipientClick = {},
        onBackClick = {},
        currentRoute = "transfer",
        onNavigateToRoute = {}
    )
}
