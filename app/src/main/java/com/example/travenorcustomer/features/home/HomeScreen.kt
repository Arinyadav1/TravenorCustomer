package com.example.travenorcustomer.features.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.travenorcustomer.R
import com.example.travenorcustomer.ui.theme.AppColors
import org.koin.androidx.compose.koinViewModel
import java.nio.file.WatchEvent


@SuppressLint("ContextCastToActivity")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetailScreen: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val activity = LocalContext.current as? Activity

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is HomeEvent.NavigateToDetailScreen -> navigateToDetailScreen(event.destinationId)
            }
        }
    }

    BackHandler {
        activity?.finish()
    }

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        Card(
            modifier = Modifier.height(44.dp),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppColors.grey
            )
        ) {
            Row(
                modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            shape = CircleShape
                        )
                        .size(36.dp)
                        .background(
                            Color(0xFFFFEADF)
                        ),
                ) {
                    Image(
                        painter = painterResource(R.drawable.profileimage),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxSize(),
                    )
                }

                Text(
                    text = state.userName,
                    fontFamily = FontFamily(
                        Font(R.font.sf_pro_rounded_medium)
                    ),
                    fontSize = 16.sp,
                    color = Color(0xFF1B1E28),
                    modifier = Modifier.padding(end = 10.dp)
                )

            }
        }

        Spacer(Modifier.height(50.dp))


        Text(
            text = "Explore the",
            fontFamily = FontFamily(
                Font(R.font.sf_ui_display_light)
            ),
            fontSize = 38.sp,
        )

        Row {
            Text(
                text = "Beautiful ",
                fontFamily = FontFamily(
                    Font(R.font.sf_ui_display_bold)
                ),
                fontSize = 38.sp,
                lineHeight = 50.sp
            )
            Box(
                modifier = Modifier.width(IntrinsicSize.Min),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy((-7).dp)
                ) {
                    Text(
                        text = "world!",
                        fontFamily = FontFamily(Font(R.font.sf_ui_display_bold)),
                        fontSize = 38.sp,
                        color = AppColors.orange,
                        lineHeight = 38.sp
                    )

                    Image(
                        painter = painterResource(R.drawable.world_txt_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }

        Spacer(Modifier.height(40.dp))

        Text(
            text = "Best Destination",
            fontFamily = FontFamily(
                Font(R.font.sf_ui_display_semibold)
            ),
            fontSize = 20.sp,
        )

        Spacer(Modifier.height(20.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                10
            ) {
                ElevatedCard(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 0.5.dp
                    ),
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                viewModel.onAction(HomeAction.OnNavigateToDetailScreen)
                            }
                        )
                        .size(height = 384.dp, width = 268.dp),
                ) {

                    Box {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.boat),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(width = 240.dp, height = 286.dp)
                                    .clip(
                                        RoundedCornerShape(20.dp)
                                    ),
                                contentScale = ContentScale.Crop
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Kotkata Reservoir",
                                    fontFamily = FontFamily(
                                        Font(R.font.sf_pro_rounded_medium)
                                    ),
                                    fontSize = 18.sp,
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = null,
                                        tint = AppColors.lightYellow,
                                        modifier = Modifier.size(17.dp)
                                    )

                                    Text(
                                        text = "4.7",
                                        fontFamily = FontFamily(
                                            Font(R.font.sf_ui_display_regular)
                                        ),
                                        fontSize = 15.sp,
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
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
                                        text = "Kotkata, India",
                                        fontFamily = FontFamily(
                                            Font(R.font.sf_pro_rounded_medium)
                                        ),
                                        fontSize = 15.sp,
                                        color = AppColors.lightSub
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy((-10).dp)
                                ) {
                                    repeat(4) {
                                        if (it <= 2) {
                                            Image(
                                                painter = painterResource(R.drawable.user),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(25.dp)
                                                    .clip(
                                                        CircleShape
                                                    )
                                            )
                                        } else {
                                            Box(
                                                modifier = Modifier
                                                    .size(25.dp)
                                                    .clip(
                                                        CircleShape
                                                    )
                                                    .background(AppColors.lightFrame),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "+50",
                                                    fontFamily = FontFamily(
                                                        Font(R.font.sf_ui_display_medium)
                                                    ),
                                                    fontSize = 11.sp,
                                                    color = Color(0xFF1B1E28)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }


                        Box(
                            modifier = Modifier
                                .padding(28.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .background(
                                        color = Color(0xFF1B1E28).copy(alpha = 0.2f),
                                        shape = CircleShape
                                    )
                                    .size(40.dp),
                                onClick = {}
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp),
                                    painter = painterResource(R.drawable.bookmark),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}