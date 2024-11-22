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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.MakePayment
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun ConfirmScreen(
    action: String = stringResource(R.string.current_action),
    onBackClick: () -> Unit,
    onConfirm: (Boolean) -> Unit,
    onCancel: () -> Unit
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        containerColor = Color.White,
        topBar = { TopBar(title ="", onBackClick = {onBackClick()}) }
    ) {
            paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 200.dp)
            .padding(paddingValues),
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.background(Color.White).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(R.string.confirm_action),
                    fontWeight = FontWeight.Medium,
                    fontSize = 28.sp,
                    color = DarkPurple,
                    modifier = Modifier.background(Color.White),
                    textAlign = TextAlign.Center
                )
            }
            Row(modifier = Modifier.background(Color.White).padding(top = 16.dp).padding(horizontal = 10.dp)) {
                Text(
                    text = stringResource(R.string.action_text, action),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.width(150.dp)) {
                    LowContrastBtn(
                        onClick = { onCancel() },
                        stringResource(R.string.cancel_button)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.width(150.dp)) {
                    HighContrastBtn(
                        onClick = { onConfirm(true) },
                        stringResource(R.string.confirm_button)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ConfirmScreenPreview() {
    NummioTheme {
        ConfirmScreen(onConfirm = {}, onCancel = {}, onBackClick = {})
    }
}
