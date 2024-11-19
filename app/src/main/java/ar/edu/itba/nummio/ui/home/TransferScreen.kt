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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar. edu.itba.nummio.ui.component.BottomBar
import ar.edu.itba.nummio.ui.component.Contact
import ar.edu.itba.nummio.ui.theme.VeryLightPurple
import ar.edu.itba.nummio.ui.component.SearchBar

data class ContactData(
    val name: String,
    val bank: String,
    val profileImage: Any,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransferScreen(
    recipients: List<ContactData>, // @TODO: reemplazar por componente "Recipient" o algo similar
    onRecipientClick: (String) -> Unit,
    onSearch: (String) -> Unit,
    onBackClick: () -> Unit,
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    Scaffold (

        bottomBar = { BottomBar(
                        currentRoute = currentRoute,
                        onNavigateToRoute = onNavigateToRoute
        )},
        topBar = {TopBar(
            title = "Transferir",
            onBackClick = onBackClick,
            actionIcon = Pair(
                {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Settings",
                        tint = DarkPurple
                    )
                },
                { /* función onClick */ }
            )
        )}
    )
    {
        paddingValues ->
        Column(modifier = Modifier.fillMaxSize()
                                  .padding(paddingValues)
            .offset(y = (-16).dp)
        ) {
            TextField(
                value = "",
                onValueChange = onSearch,
                placeholder = { Text("Ingrese el Alias o CBU/CVU") },
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Recientes",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 16.dp)
                )
                SearchBar(
                    onSearchClicked = {}, modifier = Modifier
                        .width(160.dp)
                        .height(30.dp)
                )
            }


            LazyColumn(
                modifier = Modifier
                    .weight(1f, false)
                    .heightIn(max = 450.dp)  // @TODO: ver si se puede cambiar este "magic number"
                    .fillMaxWidth()
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

            Button(
                onClick = {  }, //@TODO
                modifier = Modifier
                    .padding(16.dp)
                    .width(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(40.dp), // Altura fija para el botón
                colors = ButtonDefaults.buttonColors(
                    containerColor = VeryLightPurple
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "Ver todos",
                        color = DarkPurple,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Ver todos",
                        tint = DarkPurple,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
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
        onSearch = {},
        onBackClick = {},
        currentRoute = "transfer",
        onNavigateToRoute = {}
    )
}
