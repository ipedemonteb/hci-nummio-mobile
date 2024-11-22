package ar.edu.itba.nummio.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.NummioTheme
import ar.edu.itba.nummio.ui.theme.DarkPurple

@Composable
fun Header(
    @DrawableRes pfp: Int,
    @StringRes profileName: Int,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState
    Box(modifier = Modifier
        .background(Color.White))
    {
        Row(
            modifier = modifier.fillMaxWidth()
                .padding(horizontal = if(uiState.isLandscape) 76.dp else 20.dp, vertical = if(uiState.isLandscape) 12.dp else 26.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(pfp),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, DarkPurple, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(profileName),
                color = DarkPurple,
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(R.drawable.settings),
                contentDescription = null,
                tint = DarkPurple,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    NummioTheme {
        Header(
            pfp = R.drawable.pfp,
            profileName = R.string.profileName,

        )
    }
}
*/