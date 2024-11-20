package ar.edu.itba.nummio.ui.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.ui.component.CardComponent
import ar.edu.itba.nummio.R

@Composable
fun WalletsScreen() {
    val itemList = List(8) { index -> "323${index+1}" }
    Column (
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row (
            modifier = Modifier.padding(bottom = 10.dp),
        ) {
            Text(
                text = "Tarjetas",
                fontSize = 25.sp,
                color = Color.Black,
            )
        }
        LazyColumn (
            modifier = Modifier.heightIn(max = 440.dp)
        ) {
            items(itemList) {
                lastDigits ->
                    Row(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        CardComponent(
                            digits = lastDigits,
                            bank = R.string.bank,
                            card = R.drawable.mastercard
                        )
                    }
            }
        }
    }
}

@Preview()
@Composable()
fun WalletsScreenPreview() {
    WalletsScreen()
}
