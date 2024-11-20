package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.nummio.ui.theme.DarkPurple

@Composable
fun TransactionArrow(modifier: Modifier, received: Boolean) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(Color.Transparent)
            .border(2.dp, DarkPurple, RoundedCornerShape(12.dp))

    ) {
        if (received) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow in square",
                tint = DarkPurple,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
                    .graphicsLayer(rotationZ = 45f)
            )
        }
        else {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow in square",
                tint = DarkPurple,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
                    .graphicsLayer(rotationZ = -45f)
            )
        }
    }
}

@Preview
@Composable
fun BottomRightArrowPreview() {
    TransactionArrow(modifier = Modifier, false)
}