package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun HomeScreen(
    onNavigateToOtherScreen: (id: Int) -> Unit
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome_msg)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    NummioTheme {
        HomeScreen(onNavigateToOtherScreen = {})
    }
}