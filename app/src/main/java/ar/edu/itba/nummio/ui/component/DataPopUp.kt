package ar.edu.itba.nummio.ui.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.DarkPurple
import ar.edu.itba.nummio.ui.theme.NummioTheme

@Composable
fun DataPopUp(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth().height(450.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .blur(16.dp)
                    .background(Color.Black.copy(alpha = 0.5f))
            )
            Box(
                modifier = Modifier.background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(24.dp)

            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp)
                    .align(Alignment.Center).size(400.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.my_data),
                            color = DarkPurple,
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = DarkPurple,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(R.drawable.pfp),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                                .clip(CircleShape)
                                .border(2.dp, DarkPurple, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(id = R.string.profileName),
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
                        CopyableTextInput(stringResource(R.string.user_cvu), false)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Text(
                            text = stringResource(R.string.alias_title),
                            color = DarkPurple
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        CopyableTextInput(stringResource(R.string.user_alias), true)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.copy_all_data),
                            style = TextStyle(
                                color = DarkPurple,
                                textDecoration = TextDecoration.Underline
                            ),
                            modifier = Modifier.clickable {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CopyableTextInput(cvu : String, editable: Boolean) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 12.dp)
        ) {
            BasicTextField(
                value = cvu,
                onValueChange = {},
                readOnly = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            if(editable) {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp)
                        .clickable {
                            copyToClipboard(context, clipboardManager, cvu)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.pencil),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(2.dp))
            }
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp)
                    .clickable {
                        copyToClipboard(context, clipboardManager, cvu)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.copy),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }

        }
    }
}


fun copyToClipboard(context: Context, clipboardManager: ClipboardManager, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = android.content.ClipData.newPlainText("copiar_dos_valores", text)
    clipboard.setPrimaryClip(clip)
}


@Preview(locale = "es")
@Composable
fun DataPopUpPreview() {
    NummioTheme {
        DataPopUp({})
    }
}