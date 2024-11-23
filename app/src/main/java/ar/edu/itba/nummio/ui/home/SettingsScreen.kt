package ar.edu.itba.nummio.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit
) {
    Scaffold(topBar = { TopBar(title = stringResource(R.string.settings), onBackClick = {onBackClick()})})
    {
        paddingValues ->
        Column(modifier = Modifier.padding(horizontal = 70.dp).padding(paddingValues))
        {
            Box(modifier = Modifier.padding(top = 100.dp)) {
                HighContrastBtn(text = stringResource(R.string.logout), onClick = {})
            }
        }
    }
}


@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen({})
}