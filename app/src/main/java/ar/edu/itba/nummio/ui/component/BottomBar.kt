package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.LightPurple

@Composable
fun BottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.border(width = 1.dp, color = Color.LightGray, shape = TopBorderShape(1.dp))
                .padding(vertical = 18.dp)
                ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = painterResource(R.drawable.home),
                label = stringResource(R.string.bottom_home),
                onClick = { onNavigateToRoute("home") }
            )

            Spacer(modifier = Modifier.width(0.dp))

            BottomBarItem(
                icon = painterResource(R.drawable.settings),
                label = stringResource(R.string.bottom),
                onClick = { onNavigateToRoute("other/42") }
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(80.dp)
                .offset(y = (-4).dp)
                .background(LightPurple, CircleShape)
                .align(Alignment.TopCenter)
                .clickable(onClick = { onNavigateToRoute("other/33") })
        ) {
            Icon(
                painter = painterResource(R.drawable.qr_icon),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}


@Composable
fun BottomBarItem(
    icon: Painter,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

