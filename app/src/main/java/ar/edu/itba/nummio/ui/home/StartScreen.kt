package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.LightPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme
import ar.edu.itba.nummio.ui.theme.Purple

@Composable
fun StartScreen (
    onNavigateToRoute: (route: String) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState
    Surface {
        val modifier = Modifier.fillMaxWidth()
        Column(
            modifier = modifier
        ) {
            if (!uiState.isLandscape) {
                Row {
                    Column(
                        modifier = modifier.fillMaxWidth()
                            .clip(
                                shape = RoundedCornerShape(
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp
                                )
                            )
                            .background(Purple)
                            .height(360.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = modifier.padding(top = 230.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(R.string.welcome_start_msg),
                            fontWeight = FontWeight.Medium,
                            fontSize = 25.sp,
                            lineHeight = 35.sp,
                            color = Color.White
                        )

                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    HighContrastBtn(
                        { onNavigateToRoute("login") },
                        stringResource(R.string.login_button)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    LowContrastBtn(
                        { onNavigateToRoute("signup") },
                        stringResource(R.string.register_button)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.instagram),
                        contentDescription = null,
                        tint = DarkPurple,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(R.drawable.facebook),
                        contentDescription = null,
                        tint = DarkPurple,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(R.drawable.twitter),
                        contentDescription = null,
                        tint = DarkPurple,
                        modifier = Modifier.size(20.dp).offset(y = 3.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun StartScreenPreview() {
    NummioTheme {
        StartScreen( {} )
    }
}
