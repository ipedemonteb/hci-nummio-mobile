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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R

@Composable
fun WalletComponent(
    deleteCard: Boolean = false,
    onNavigateToAddCard: () -> Unit
) {
    val itemList = List(8) { index -> "323${index + 1}" }
    var openPopUp by remember { mutableStateOf(false) }
    var isConfirmed by remember { mutableStateOf(false) }

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
                items(itemList) { lastDigits ->
                    Row(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        CardComponent(
                            digits = lastDigits,
                            bank = R.string.bank,
                            card = R.drawable.mastercard,
                            modifier = Modifier.weight(1f)
                        )

                        if (deleteCard) {
                            IconButton(
                                onClick = { openPopUp = true },
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
            Row(modifier = Modifier.padding(horizontal = 80.dp, vertical = 40.dp)) {
                HighContrastBtn(onClick = {onNavigateToAddCard()}, stringResource(R.string.add_card))
            }
        }

        if (openPopUp) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
                    //.background(Color.Black.copy(alpha = 0.5f))
            ) {
                ConfirmPopUp(
                    action = "Delete Card",
                    onConfirm = { isConfirmed = true; openPopUp = false },
                    onCancel = { openPopUp = false }
                )
            }
        }
    }
}

@Preview()
@Composable()
fun WalletComponentPreview() {
    WalletComponent(true, {})
}
