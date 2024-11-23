package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.nummio.MyApplication
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.SocialMedia
import ar.edu.itba.nummio.ui.theme.Purple

@Composable
fun StartScreen (
    onNavigateToLogin: () -> Unit,
    onNavigateToSignup: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState
    Surface(modifier = Modifier.fillMaxSize()) {
        val modifier = Modifier.fillMaxWidth()
        if(!uiState.isLandscape) {
        Column(
            modifier = modifier
        ) {
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
                            .height(450.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            Text(
                                modifier = modifier.padding(top = 150.dp),
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.nummio),
                                fontWeight = FontWeight.Bold,
                                fontSize = 50.sp,
                                lineHeight = 35.sp,
                                color = Color.White
                            )
                        }
                        Row {
                            Text(
                                modifier = modifier.padding(top = 110.dp),
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.welcome_start_msg),
                                fontWeight = FontWeight.Medium,
                                fontSize = 25.sp,
                                lineHeight = 35.sp,
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    HighContrastBtn(
                        { onNavigateToLogin() },
                        stringResource(R.string.login_button)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    LowContrastBtn(
                        { onNavigateToSignup() },
                        stringResource(R.string.register_button)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                SocialMedia()
            }
        }

        else {
            Row(modifier = Modifier.fillMaxWidth()){
                Column(modifier = Modifier
                    .fillMaxWidth(0.4f)
                ) {
                    Surface(modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(
                            bottomEnd = 20.dp,
                            topEnd = 20.dp
                        ),
                        color = Purple
                    ) {
                        Text(
                            modifier = modifier.padding(top = 150.dp, ),
                            textAlign = TextAlign.Center,
                            text = stringResource(R.string.nummio),
                            fontWeight = FontWeight.Bold,
                            fontSize = 50.sp,
                            lineHeight = 35.sp,
                            color = Color.White
                        )
                    }

                }
                Column(modifier = Modifier
                    .fillMaxWidth()) {
                    Text(
                        modifier = modifier.padding(top = 50.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.welcome_start_msg),
                        fontWeight = FontWeight.Medium,
                        fontSize = 25.sp,
                        lineHeight = 35.sp,
                        color = Purple
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 80.dp)
                    ) {
                        HighContrastBtn(
                            { onNavigateToLogin() },
                            stringResource(R.string.login_button)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 80.dp)
                    ) {
                        LowContrastBtn(
                            { onNavigateToSignup() },
                            stringResource(R.string.register_button)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    SocialMedia()
                }
            }
        }
    }
}

/*
@Preview(
    name = "Landscape",
    device = Devices.PIXEL_4,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO,
    widthDp = 731,
    heightDp = 350
)
@Composable
fun StartScreenPreview() {
    NummioTheme {
        StartScreen( {} )
    }
}
*/
