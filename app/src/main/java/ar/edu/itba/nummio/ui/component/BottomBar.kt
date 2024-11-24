package ar.edu.itba.nummio.ui.component

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.home.HomeViewModel
import ar.edu.itba.nummio.ui.theme.LightPurple

const val CAMERA_REQUEST_CODE = 1001

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit,
    onNavigateToData: () -> Unit,
    onNavigateToQRScan: () -> Unit,
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState
    val context = LocalContext.current
    Box(modifier = modifier.background(Color.White)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.border(width = 1.dp, color = Color.LightGray, shape = TopBorderShape(1.dp))
                .padding(vertical = if(uiState.isLandscape) 6.dp else 18.dp, horizontal = if(uiState.isLandscape) 30.dp else 0.dp)
                ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = painterResource(R.drawable.home),
                label = stringResource(R.string.bottom_home),
                onClick = { onNavigateToHome() }
            )

            Spacer(modifier = Modifier.width(0.dp))

            BottomBarItem(
                icon = painterResource(R.drawable.id),
                label = stringResource(R.string.my_data),
                onClick = { onNavigateToData() }
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(80.dp)
                .offset(y = (-4).dp)
                .background(LightPurple, CircleShape)
                .align(Alignment.TopCenter)
                .clickable(onClick = {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if (cameraIntent.resolveActivity(context.packageManager) != null) {
                        (context as? Activity)?.startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
                    } else {
                        Toast.makeText(context, "No se pudo abrir la cámara", Toast.LENGTH_SHORT).show()
                    }
                })
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
            modifier = Modifier.size(40.dp)
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