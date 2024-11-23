package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.BalanceBox
import ar.edu.itba.nummio.ui.component.SearchBar
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.component.Transaction
import java.util.Date

data class TransactionData(
    val message: String,
    val destination: String,
    val date: String,
    val amount: Double
)

@Composable
fun MovementsScreen(
    viewModel : HomeViewModel,
    movements: List<TransactionData>,
    onBackClick : ()->Unit,
)
{
    var expanded = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    if (viewModel.uiState.shouldUpdatePaymentHistory) {
        viewModel.getPayments(
            page = 1,
            direction = "ASC",
            pending = null,
            type = null,
            range = null,
            source = null,
            cardId = null
        )
    }
    if (viewModel.uiState.currentUser==null) {
        viewModel.getCurrentUser()
    }

    Scaffold(
        topBar = { TopBar(stringResource(R.string.movements_option), onBackClick) },
    )
    {
        paddingValues ->
        Column(modifier = Modifier.padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally){
            BalanceBox(viewModel=viewModel,modifier = Modifier.width(336.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(top=20.dp, end= 20.dp)

            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                        Button(onClick = { expanded.value = !expanded.value }, modifier = Modifier.height(50.dp).width(150.dp).clip(
                            RoundedCornerShape(7.dp)), colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black)) {
                            Text(text = stringResource(R.string.filter_msg))
                            Icon(

                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Flecha hacia abajo",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }

                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                        modifier = Modifier.background(Color.White).align(Alignment.CenterHorizontally)
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                expanded.value = false
                            },
                            text =  {
                                Text(text = "Opción 1")
                            }
                        )
                        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                        DropdownMenuItem(
                            onClick = {
                                expanded.value = false
                            },
                            text =  {
                                Text(text = "Opción 2")
                            }
                        )
                        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                        DropdownMenuItem(
                            onClick = {
                                expanded.value = false
                            },
                            text =  {
                                Text(text = "Opción 3")
                            }
                        )
                    }
                }
                SearchBar(
                    onSearchClicked = {},
                    modifier = Modifier
                        .width(160.dp)
                        .height(30.dp),
                    input = searchText,
                    onInputChange = {searchText=it}
                )
            }
             LazyColumn(modifier = Modifier.fillMaxHeight().width(368.dp)) {
                items(movements) { transaction ->
                    Transaction(transaction.message, transaction.destination,
                        transaction.date, transaction.amount)
                }

            }


        }

    }
}
/*

@PreviewScreenSizes
@Composable
fun MovementsScreenPreview()
{
    val transactionData = listOf(
        TransactionData(
            message = "Transferencia recibida",
            destination = "Lewis Hamilton",
            date = Date(),
            amount = 5000
        ),
        TransactionData(
            message = "Transferencia enviada",
            destination = "Max Verstappen",
            date = Date(),
            amount = 3000
        ),
        TransactionData(
            message = "Transferencia recibida",
            destination = "Charles Leclerc",
            date = Date(),
            amount = 7000
        ),
        TransactionData(
            message = "Transferencia enviada",
            destination = "Fernando Alonso",
            date = Date(),
            amount = 2500
        ),
        TransactionData(
            message = "Transferencia recibida",
            destination = "Lando Norris",
            date = Date(),
            amount = 4500
        ),
        TransactionData(
            message = "Transferencia recibida",
            destination = "Ayrton Senna",
            date = Date(),
            amount = 4500
        ),
        TransactionData(
            message = "Transferencia recibida",
            destination = "Lando Norris",
            date = Date(),
            amount = 4500
        ),
        TransactionData(
            message = "Transferencia recibida",
            destination = "Dios",
            date = Date(),
            amount = 4500
        )
    )
    MovementsScreen(transactionData, {})
}*/
