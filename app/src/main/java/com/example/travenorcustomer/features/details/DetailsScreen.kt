package com.example.travenorcustomer.features.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.travenorcustomer.R
import com.example.travenorcustomer.features.components.TravenorAsyncImage
import com.example.travenorcustomer.features.components.TravenorExpandableText
import com.example.travenorcustomer.features.components.TravenorIconButton
import com.example.travenorcustomer.features.components.TravenorTextButton
import com.example.travenorcustomer.ui.theme.AppColors


@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    onNavigationBack : () -> Unit
) {

    Box(modifier = modifier.fillMaxSize()) {

        TravenorAsyncImage(
            image = "https://pwbxzqxqkksrnlibjrnt.supabase.co/storage/v1/object/public/destination-image/kotkata_cover.jpg",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .statusBarsPadding()
        ) {

            TravenorIconButton(
                iconColor = Color.White,
                onClick = { },
                bgColor = Color(0xFF1B1E28).copy(alpha = 0.2f),
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Text(
                text = "Details", color = Color.White, fontFamily = FontFamily(
                    Font(R.font.sf_ui_display_semibold)
                ), fontSize = 18.sp, modifier = Modifier.align(Alignment.Center)
            )
        }


        DestinationBottomCard(
            onBookNowClick = { }, modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun DestinationBottomCard(
    onBookNowClick: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(Color.White)
            .padding(20.dp)
    ) {

        Box(
            modifier = Modifier
                .size(width = 36.dp, height = 5.dp)
                .clip(RoundedCornerShape(50))
                .background(AppColors.lightSub.copy(alpha = 0.3f))
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "Kotkata Reservoid", fontFamily = FontFamily(
                        Font(R.font.sf_pro_rounded_medium)
                    ), fontSize = 24.sp, lineHeight = 32.sp
                )
                Text(
                    "Kolkata, India", color = AppColors.lightSub, fontFamily = FontFamily(
                        Font(R.font.sf_pro_rounded_medium)
                    ), fontSize = 15.sp, lineHeight = 20.sp
                )
            }
            Image(
                painter = painterResource(R.drawable.user),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(
                        CircleShape
                    )
            )
        }

        Spacer(Modifier.height(25.dp))

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.location),
                    contentDescription = null,
                    tint = AppColors.lightSub,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = "Kotkata, India", fontFamily = FontFamily(
                        Font(R.font.sf_pro_rounded_medium)
                    ), fontSize = 15.sp, color = AppColors.lightSub
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = AppColors.lightYellow,
                    modifier = Modifier.size(17.dp)
                )

                Spacer(modifier.height(5.dp))

                Text(
                    text = "4.7",
                    fontFamily = FontFamily(
                        Font(R.font.sf_ui_display_regular)
                    ),
                    fontSize = 15.sp,
                )

                Text(
                    text = "(3456)", fontFamily = FontFamily(
                        Font(R.font.sf_ui_display_regular)
                    ), fontSize = 15.sp, color = AppColors.lightSub
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$65/", fontFamily = FontFamily(
                        Font(R.font.sf_ui_display_regular)
                    ), fontSize = 15.sp, color = AppColors.primaryBlue
                )

                Text(
                    text = "Person", fontFamily = FontFamily(
                        Font(R.font.sf_ui_display_regular)
                    ), fontSize = 15.sp, color = AppColors.lightSub
                )
            }
        }

        Spacer(Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(5) { index ->
                if (index <= 4) {
                    Box {

                        TravenorAsyncImage(
                            image = "https://pwbxzqxqkksrnlibjrnt.supabase.co/storage/v1/object/public/destination-image/kotkata_cover.jpg",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )

                        if (index == 4) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Black.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "+" + (20 - 5), fontFamily = FontFamily(
                                        Font(R.font.sf_ui_display_regular)
                                    ), fontSize = 14.sp, color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(25.dp))

        Text(
            "About Destination", fontSize = 20.sp, fontFamily = FontFamily(
                Font(R.font.sf_ui_display_semibold)
            )
        )

        Spacer(Modifier.height(10.dp))

        TravenorExpandableText("Kerala Backwaters provide a peaceful and rejuvenating experience through scenic waterways, houseboat stays, and lush green surroundings. This destination is ideal for relaxation, wellness retreats, and romantic getaways.")

        Spacer(Modifier.height(25.dp))

        TravenorTextButton(
            onClick = {}, btnText = "Book Now"
        )

    }
}


