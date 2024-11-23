package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun BalanceBox(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    var saldoVisible by remember { mutableStateOf(true) }
    val uiState = viewModel.uiState
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
            Box(modifier = Modifier
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 16.dp))
            {
                Column(
                    modifier = Modifier,
                ) {
                    Text(
                        text = stringResource(id = R.string.my_balance),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (saldoVisible) "$" + uiState.currentBalance.toString()
                                else stringResource(id = R.string.hide_balance),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium,
                        )

                        IconButton(onClick = { saldoVisible = !saldoVisible }) {
                            Icon(
                                painter = if(saldoVisible) painterResource(id = R.drawable.eye_open)
                                    else painterResource(id = R.drawable.closed_eye),
                                //@todo: revisar
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
    }
}

@Preview
@Composable
fun BalanceBoxPreview() {
    NummioTheme {
        BalanceBox(viewModel = viewModel())
    }
}
