package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.BalanceBox
import ar.edu.itba.nummio.ui.component.BottomOptions
import ar.edu.itba.nummio.ui.component.TopOptions
import ar.edu.itba.nummio.ui.component.WalletMain
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun HomeScreen(
    onNavigateToOtherScreen: (id: Int) -> Unit,
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState
    Surface {
        Box(modifier = Modifier.background(Color.White)) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(horizontal = if (uiState.isLandscape) 76.dp else 26.dp)
                            .background(Color.White)
                    ) {

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(R.string.welcome_msg),
                                fontWeight = FontWeight.Medium,
                                fontSize = 28.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            BalanceBox()
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            TopOptions(viewModel = viewModel)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier.fillMaxWidth().background(Color.White)) {
                    WalletMain(viewModel = viewModel)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier.fillMaxWidth().background(Color.White)) {
                    BottomOptions(viewModel = viewModel)
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    NummioTheme {
        HomeScreen(onNavigateToOtherScreen = {}, viewModel)
    }
}
*/