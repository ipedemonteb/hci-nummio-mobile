package ar.edu.itba.nummio.ui.home

import android.graphics.drawable.shapes.RectShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.LightPurple
import ar.edu.itba.nummio.ui.theme.VeryLightPurple

@Composable
fun StartScreen (
    onNavigateToRoute: (route: String) -> Unit
) {
    Surface {
        val modifier = Modifier.fillMaxWidth()
        Column(
            modifier = modifier
        ) {
            Column(
                modifier = modifier.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(VeryLightPurple)
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = modifier.padding(top = 230.dp),
                    textAlign = TextAlign.Center,
                    text = "¡Bienvenido!\nIngresá a tu cuenta",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }
            Button(
                onClick = { onNavigateToRoute("login") },
                modifier = modifier.fillMaxWidth()
                    .padding(top = 10.dp),
                colors = ButtonColors(DarkPurple, Color.White, Color.White, Color.White)
            ) { Text("Iniciar sesión") }
            Button(
                onClick = { onNavigateToRoute("signup") },
                modifier = modifier.fillMaxWidth()
                    .padding(top = 10.dp),
                colors = ButtonColors(VeryLightPurple, Color.Black, Color.White, Color.White)
            ) { Text("Registrarse") }
        }
    }
}