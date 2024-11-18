package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun BalanceBox(modifier: Modifier = Modifier.fillMaxWidth()) {
    var saldoVisible by remember { mutableStateOf(true) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
            Box(modifier = Modifier
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 16.dp))
            {
                Column(
                    modifier = Modifier,
                ) {
                    Text(
                        text = "Mi Saldo",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (saldoVisible) "$1000.00" else "$********",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium,
                        )

                        IconButton(onClick = { saldoVisible = !saldoVisible }) {
                            Icon(
                                imageVector = Icons.Outlined.Check,
                                contentDescription = if (saldoVisible) "Ocultar saldo" else "Mostrar saldo",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
    }
}

@Preview
@Composable
fun BalanceBoxPreview() {
    NummioTheme {
        BalanceBox()
    }
}
