package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.nummio.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Transaction(
    message: String,
    destination: String,
    date: String,
    amount: Double
)
{
    //val dateFormat = SimpleDateFormat("d MMM yy", Locale("es", "ES"))
    //val formattedDate = dateFormat.format(date) // Usando el timestamp para convertirlo en fecha

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Esto hace que el fondo sea blanco
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TransactionArrow(modifier = Modifier.size(50.dp), message== stringResource(R.string.received_payment)) //@TODO: mejorar lógica

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (message==stringResource(R.string.received_payment))"$destination" else "$destination", //@TODO: mejorar lógica
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column() {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = if (message==stringResource(R.string.received_payment))"+ $$amount" else "- $$amount", //@TODO: mejorar lógica
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (message==stringResource(R.string.received_payment)) Color(0xFF228B22) else Color.Black
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun TransactionPreview(){

    Transaction(
        message = "Transferencia recibida",
        destination = "Lewis Hamilton",
        date = Date().toString(),
        amount = 5000.0)
}