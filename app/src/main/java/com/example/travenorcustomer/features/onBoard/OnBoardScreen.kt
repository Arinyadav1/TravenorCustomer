package com.example.travenorcustomer.features.onBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travenorcustomer.R
import com.example.travenorcustomer.features.components.TravenorTextButton
import com.example.travenorcustomer.ui.theme.AppColors

@Composable
fun OnBoardScreen(
    modifier: Modifier = Modifier,
    navigateToSignInScreen : () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.boat),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(30.dp)
                )
        )

        Spacer(Modifier.height(60.dp))

        Column(
            modifier = Modifier.padding(horizontal = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Life is short and the",
                fontFamily = FontFamily(
                    Font(R.font.geometr415_blk_bt)
                ),
                fontWeight = FontWeight(400),
                fontSize = 30.sp,
                lineHeight = 36.sp
            )

            Row {
                Text(
                    text = "world is ",
                    fontFamily = FontFamily(
                        Font(R.font.geometr415_blk_bt)
                    ),
                    fontWeight = FontWeight(400),
                    fontSize = 30.sp,
                )
                Box(
                    modifier = Modifier.width(IntrinsicSize.Min),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "wide",
                            fontFamily = FontFamily(
                                Font(R.font.geometr415_blk_bt)
                            ),
                            fontWeight = FontWeight(400),
                            fontSize = 30.sp,
                            color = AppColors.orange
                        )
                        Image(
                            painter = painterResource(R.drawable.text_under),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = "At Friends tours and travel, we customize reliable and trutworthy educational tours to destinations all over the world",
                fontFamily = FontFamily(
                    Font(R.font.gill_sans_mt)
                ),
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = AppColors.lightSub,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }

        Spacer(Modifier.height(80.dp))

        TravenorTextButton(
            modifier = Modifier.padding(horizontal = 20.dp),
            onClick = {
                navigateToSignInScreen()
            },
            btnText = "Get Started"
        )

    }
}