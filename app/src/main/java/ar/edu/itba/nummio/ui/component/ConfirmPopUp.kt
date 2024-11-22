package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun ConfirmPopUp(
    action: String = stringResource(R.string.current_action),
    onConfirm: (Boolean) -> Unit,
    onCancel: () -> Unit
) {
    Surface(shape = RoundedCornerShape(8.dp),
        color = Color.White,
        modifier = Modifier.width(400.dp).offset(y = (-180).dp)
    ) {
        Column(modifier = Modifier
            .background(Color.White)
            .padding(30.dp)
        ) {
            Row(modifier = Modifier.background(Color.White)) {
                Text(
                    text = stringResource(R.string.confirm_action),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = DarkPurple,
                    modifier = Modifier.background(Color.White)
                )
            }
            Row(modifier = Modifier.background(Color.White).padding(top = 16.dp)) {
                Text(
                    text = stringResource(R.string.action_text, action),
                    fontSize = 18.sp,
                )
            }
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
fun ConfirmPopUpPreview() {
    NummioTheme {
        ConfirmPopUp(onConfirm = {}, onCancel = {})
    }
}