package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R

@Composable
fun WalletComponent(
    deleteCard: Boolean = false
) {
    val itemList = List(8) { index -> "323${index+1}" }
    Column (
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
    ) {
        Row (
            modifier = Modifier.padding(bottom = 20.dp),
        ) {
            Text(
                text = stringResource(R.string.credit_cards),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        }
        Row {
            LazyColumn(
                modifier = Modifier.heightIn(max = 450.dp)
            ) {
                items(itemList) { lastDigits ->
                    Row(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        CardComponent(
                            digits = lastDigits,
                            bank = R.string.bank,
                            card = R.drawable.mastercard,
                            delete = deleteCard
                        )
                    }
                }
            }
        }
        Row(modifier = Modifier.padding(horizontal = 80.dp, vertical = 40.dp)) {
            HighContrastBtn( onClick = {}, "Add Card")
        }

    }
}

@Preview()
@Composable()
fun WalletComponentPreview() {
    WalletComponent()
}
