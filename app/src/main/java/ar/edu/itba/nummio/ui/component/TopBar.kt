package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.VeryLightPurple

@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit,    // Acción al presionar la flecha de atrás
    actionIcon: Pair<(@Composable () -> Unit), () -> Unit>? = null // Par de (icono, onClick)
) {
    // Barra superior personalizada
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(45.dp), // Altura de la barra
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono de navegación (flecha hacia atrás)
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = DarkPurple,
            )
        }

        // Título centrado
        Text(
            text = title,
            color = DarkPurple, // Usar DarkPurple para el texto del título
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f), // Esto hace que el texto se centre
            textAlign = TextAlign.Center
        )

        // Espacio equivalente al IconButton para mantener el título centrado
        if (actionIcon != null) {
            IconButton(onClick = actionIcon.second) {
                actionIcon.first()
            }
        } else {
            // Placeholder para mantener el centrado cuando no hay ícono
            Spacer(modifier = Modifier.size(48.dp)) // 48.dp es el tamaño estándar de IconButton
        }

    }

    // Fondo de la barra
    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(VeryLightPurple))
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
