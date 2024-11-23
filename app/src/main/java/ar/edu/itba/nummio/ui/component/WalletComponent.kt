package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.home.HomeViewModel
import kotlinx.coroutines.Job

@Composable
fun WalletComponent(
    deleteCard: Boolean = false,
    onNavigateToAddCard: () -> Unit,
    onNavigateToConfirmScreen: (String, Int) -> Unit,
    viewModel: HomeViewModel
) {
    var openPopUp by remember { mutableStateOf(false) }
    var isConfirmed by remember { mutableStateOf(false) }

    // @TODO: ver por que cuando se hace llama a la API 2 veces
    if(viewModel.uiState.cards == null)
        viewModel.getCards()
    var cardToDelete: Int? = null
    Box(/*modifier = Modifier.fillMaxSize()*/) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 20.dp),
            ) {
                Text(
                    text = stringResource(R.string.credit_cards),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            }
            LazyColumn(
                modifier = Modifier.heightIn(max = 450.dp)
            ) {
                if(viewModel.uiState.cards != null) {
                    items(viewModel.uiState.cards!!) { card ->
                        Row(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            CardComponent(
                                digits = card.number.substring(card.number.length - 4, card.number.length),
                                bank = R.string.bank,
                                card = R.drawable.mastercard,
                                modifier = Modifier.weight(1f)
                            )

                            if (deleteCard) {
                                IconButton(
                                    onClick = { cardToDelete=card.id
                                        onNavigateToConfirmScreen("Deleting a card", cardToDelete?:0)

                                              }, //@TODO: ver cómo reemplazar por un string de strings.xml (no le gusta que no sea composable)
                                    modifier = Modifier
                                        .size(60.dp)
                                        .align(Alignment.CenterVertically)
                                        .padding(horizontal = 16.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.trash),
                                        contentDescription = null,
                                        tint = Color.Red,
                                    )
                                }
                            }
                        }
                    }
                }
            }/*if (openPopUp) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
                    //.background(Color.Black.copy(alpha = 0.5f))
            ) {
                ConfirmPopUp(
                    action = "Delete Card",
                    onConfirm = { isConfirmed = true; openPopUp = false ; viewModel.deleteCard(cardToDelete!!) },
                    onCancel = { openPopUp = false }
                )
            }
        }*/
            Row(modifier = Modifier.padding(horizontal = 80.dp, vertical = 40.dp)) {
                HighContrastBtn(onClick = {onNavigateToAddCard()}, stringResource(R.string.add_card))
            }
        }


    }
}
/*

@Preview()
@Composable()
fun WalletComponentPreview() {
    WalletComponent(true, {}, {}, viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication)))
}
*/
