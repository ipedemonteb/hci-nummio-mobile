package ar.edu.itba.nummio.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.nummio.R
import ar.edu.itba.nummio.ui.theme.NummioTheme
import ar.edu.itba.nummio.ui.theme.Purple

@Composable
fun AddCardComponent() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Purple,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            Row {
                Text(
                    text = stringResource(R.string.add_card),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))

                        .padding(horizontal = 10.dp, vertical = 12.dp)
                ) {
                    BasicTextField(
                        value = stringResource(R.string.card_number),
                        onValueChange = {},
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 14.sp,
                            color = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 12.dp)
                ) {
                    BasicTextField(
                        value = stringResource(R.string.holder),
                        onValueChange = {},
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 14.sp,
                            color = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.width(160.dp))
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.width(140.dp)
                ) {
                    Row {
                        Text(
                            text = stringResource(R.string.expiration_date),
                            fontSize = 12.sp
                        )
                    }
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                                .width(100.dp)
                        ) {
                            BasicTextField(
                                value = stringResource(R.string.mm),
                                onValueChange = {},
                                textStyle = androidx.compose.ui.text.TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.White
                                ),
                                modifier = Modifier.width(40.dp)
                            )
                        }
                        Text (
                            text = stringResource(R.string.slash),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                                .width(100.dp)
                        ) {
                            BasicTextField(
                                value = stringResource(R.string.yyyy),
                                onValueChange = {},
                                textStyle = androidx.compose.ui.text.TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.White
                                ),
                                modifier = Modifier.width(40.dp)
                            )
                        }
                    }
                }
                Column {
                    Image(
                        painter = painterResource(R.drawable.mastercard),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp)
                    )
                }
            }
            Row {

            }
        }
    }
}

@Preview(locale = "es")
@Composable
fun AddCardComponentPreview() {
    NummioTheme {
        AddCardComponent()
    }
}