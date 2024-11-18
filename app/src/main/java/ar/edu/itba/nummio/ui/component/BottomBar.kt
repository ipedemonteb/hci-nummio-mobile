package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu

import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun BottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = Icons.Outlined.Home,
                label = "Inicio",
                onClick = { onNavigateToRoute("home") }
            )

            Spacer(modifier = Modifier.width(32.dp))

            BottomBarItem(
                icon = Icons.Default.Menu,
                label = "Más",
                onClick = { onNavigateToRoute("other/42") }
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(90.dp)
                .offset(y = (-24).dp)
                .background(Color(0xFFD8CBF7), CircleShape)
                .align(Alignment.TopCenter)
                .clickable(onClick = { onNavigateToRoute("other/33") })
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "QR Code",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}


@Composable
fun BottomBarItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

