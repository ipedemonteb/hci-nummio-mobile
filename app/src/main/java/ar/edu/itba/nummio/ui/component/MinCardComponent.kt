package ar.edu.itba.nummio.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme
import ar.edu.itba.nummio.ui.theme.Purple

@Composable
fun CardComponent(
    digits: String,
    @StringRes bank: Int,
    @DrawableRes card: Int
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Purple,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)

        ) {
            Column {
                Row {
                    Text(
                        text = stringResource(bank),
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = DarkPurple
                    ) {
                        Text(
                            text = "**** $digits",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }
                }
            }
            Column {
                Image(
                    painter = painterResource(card),
                    contentDescription = null,
                    modifier = Modifier.size(70.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CardComponentPreview() {
    NummioTheme {
        CardComponent("1234", R.string.bank, R.drawable.mastercard)
    }
}
