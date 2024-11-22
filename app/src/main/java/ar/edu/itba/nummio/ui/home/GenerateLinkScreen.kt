package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.GeneratePayment
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun GenerateLinkScreen(
    onBackClick: () -> Unit
) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_left),
                            contentDescription = null,
                            tint = DarkPurple,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .size(40.dp)
                                .offset(x = (-10).dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.generate_page),
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = DarkPurple
                    )
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 30.dp)
            ) {
                GeneratePayment()
            }
        }
    }
}

@Preview
@Composable
fun GenerateLinkScreenPreview() {
    NummioTheme {
        GenerateLinkScreen({})
    }
}