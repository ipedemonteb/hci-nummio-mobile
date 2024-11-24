package ar.edu.itba.nummio.ui.home

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.data.model.AliasRequest
import ar.edu.itba.nummio.ui.component.CopyableTextInput
import ar.edu.itba.nummio.ui.component.HighContrastBtn
import ar.edu.itba.nummio.ui.component.LowContrastBtn
import ar.edu.itba.nummio.ui.component.TopBar
import ar.edu.itba.nummio.ui.theme.DarkPurple

@Composable
fun DataScreen(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel
) {
    if (viewModel.uiState.currentUser == null) {
        viewModel.getCurrentUser()

    }
    if (viewModel.uiState.shouldUpdateWalletDetails) {
        viewModel.getDetails()
    }
    val uiState = viewModel.uiState
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.my_data),
                onBackClick = { onBackClick() },
                viewModel = viewModel
            )
        }
    ) {

            paddingValues ->
        var alias = remember { mutableStateOf(viewModel.uiState.walletDetails?.alias) }
        val context = LocalContext.current
        if (viewModel.uiState.shouldUpdateWalletDetails) {
            viewModel.getDetails()
        }
        Column(
            modifier = Modifier
                .padding(
                    horizontal = if (uiState.isLandscape) 76.dp else {
                        if (viewModel.uiState.isOver600dp) 50.dp else 30.dp
                    }
                )
                .padding(paddingValues)
                .verticalScroll(
                    enabled = uiState.isLandscape,
                    state = rememberScrollState()
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.pfp),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(2.dp, DarkPurple, CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = viewModel.uiState.currentUser?.firstName + " " + viewModel.uiState.currentUser?.lastName,
                    color = DarkPurple,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(R.string.cvu_title),
                    color = DarkPurple
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                CopyableTextInput(viewModel.uiState.walletDetails?.cbu ?: "", false)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(R.string.alias_title),
                    color = DarkPurple
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(bottom = 20.dp)) {
                CopyableTextInput(viewModel.uiState.walletDetails?.alias ?: "", true)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (viewModel.uiState.isOver600dp) 200.dp else 60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                LowContrastBtn(onClick = {
                    val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val combinedText =
                        "CVU: ${viewModel.uiState.walletDetails?.cbu}\nAlias: ${viewModel.uiState.walletDetails?.alias}"
                    val clip =
                        android.content.ClipData.newPlainText("copiar_dos_valores", combinedText)
                    clipboard.setPrimaryClip(clip)
                }, text = stringResource(R.string.copy_all_data))

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (viewModel.uiState.isOver600dp) 200.dp else 60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                LowContrastBtn(onClick = {
                    shareText(
                        context,
                        "CVU: ${viewModel.uiState.walletDetails?.cbu}\nAlias: ${viewModel.uiState.walletDetails?.alias}"
                    )
                }, text = stringResource(R.string.share_all_data))
                Spacer(modifier = Modifier.height(40.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (viewModel.uiState.isOver600dp) 200.dp else 60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                HighContrastBtn(onClick = {
                    viewModel.updateAlias(AliasRequest(alias.value!!))
                }, text = stringResource(R.string.update_alias))
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

fun shareText(context: android.content.Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }

    // Show the chooser to the user
    context.startActivity(Intent.createChooser(intent, "Share via"))
}
/*

@Preview
@Composable
fun DataScreenPreview() {
    DataScreen({})
}*/
