package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.VeryLightPurple

@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit,
    actionIcon: Pair<(@Composable () -> Unit), () -> Unit>? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { onBackClick() }) {
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
                    text = title,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = DarkPurple
                )
                if (actionIcon != null) {
                    IconButton(
                        onClick = actionIcon.second,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        actionIcon.first()
                    }
                } else {
                    Spacer(modifier = Modifier.size(48.dp))
                }
            }

        }

        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(VeryLightPurple))
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(
        title = "Mi Título",
        onBackClick = {},
        actionIcon = Pair(
            {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = DarkPurple
                )
            },
            { /* función onClick */ }
        )
    )
}
