package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.LightPurple

@Composable
fun HighContrastBtn(
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().size(45.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkPurple,
            contentColor = Color.White,
            disabledContainerColor = DarkPurple,
            disabledContentColor = LightPurple
        ),
        enabled = enabled
    ) { Text( text ) }
}