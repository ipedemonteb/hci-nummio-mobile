package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun BottomOptions(
    onNavigateToInvestments: () -> Unit,
    onNavigateToMakePayment: () -> Unit,
    onNavigateToGenerateLink: () -> Unit,
    onNavigateToPromotions: () -> Unit,
    onNavigateToContacts: () -> Unit,
    onNavigateToHelp: () -> Unit
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        ) {
            SquaredOption(
                icon = painterResource(id = R.drawable.stock),
                label = stringResource(id = R.string.investments_option),
                onClick = {onNavigateToInvestments()}
            )
            SquaredOption(
                icon = painterResource(id = R.drawable.receipt),
                label = stringResource(id = R.string.payment_option),
                onClick = {onNavigateToMakePayment()}
            )
            SquaredOption(
                icon = painterResource(id = R.drawable.link),
                label = stringResource(id = R.string.generate_link_option),
                onClick = {onNavigateToGenerateLink()}
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        ) {
            SquaredOption(
                icon = painterResource(id = R.drawable.promotion),
                label = stringResource(id = R.string.promotions_option),
                onClick = {onNavigateToPromotions()}
            )
            SquaredOption(
                icon = painterResource(id = R.drawable.contacts),
                label = stringResource(id = R.string.contacts_option),
                onClick = {onNavigateToContacts()}
            )
            SquaredOption(
                icon = painterResource(id = R.drawable.more),
                label = stringResource(id = R.string.help_option),
                onClick = {onNavigateToHelp()}
            )
        }
    }
}

@Composable
fun SquaredOption(
    icon: Painter,
    label: String,
    onClick: () -> Unit
){
    Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(8.dp))
                            .background(Color.White, RoundedCornerShape(8.dp))
        .clickable(onClick = {onClick()}),
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.size(
                width = 110.dp,
                height = 130.dp)
                .padding(horizontal = 12.dp)

        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Row {
                Icon(
                    painter = icon,
                    contentDescription = label,
                    tint = DarkPurple,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = label,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp,
                    fontSize = 14.sp

                )
            }
        }
    }
}

@Preview
@Composable
fun SquaredOptionPreview() {
    NummioTheme {
        SquaredOption(
            icon = painterResource(id = R.drawable.arrow_right),
            label = "Transfer",
            onClick = {}
        )
    }
}

@Preview
@Composable
fun BottomOptionsPreview() {
    NummioTheme {
        BottomOptions({}, {}, {}, {}, {}, {})
    }
}