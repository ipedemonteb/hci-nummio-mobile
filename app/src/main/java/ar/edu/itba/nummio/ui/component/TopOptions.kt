package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun TopOptions(
    viewModel: HomeViewModel,
    onNavigateToTransfer: () -> Unit,
    onNavigateToDeposit: () -> Unit
) {
    val uiState = viewModel.uiState
    Box(modifier = Modifier.padding(horizontal = if(uiState.isLandscape) 80.dp else 12.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoundOption(
                icon = painterResource(id = R.drawable.arrow_right),
                label = stringResource(id = R.string.transfer_option),
                onClick = {onNavigateToTransfer()}
            )
            RoundOption(
                icon = painterResource(id = R.drawable.id),
                label = stringResource(id = R.string.data_option),
                onClick = { }//@TODO: popup
            )
            RoundOption(
                icon =painterResource(id = R.drawable.history),
                label = stringResource(id = R.string.deposit_screen),
                onClick = {onNavigateToDeposit()},
            )

        }
    }
}

@Composable
fun RoundOption(
    icon: Painter,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(
            width = 100.dp,
            height = 110.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(64.dp)
                .background(DarkPurple, CircleShape)
            .clickable(onClick = { onClick() })
        ) {
            Icon(
                painter = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
        Text(
            text = label,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TopOptionsPreview() {
    NummioTheme {
        TopOptions(onNavigateToTransfer = {}, onNavigateToDeposit = {}, viewModel = viewModel())
    }
}


