package com.example.travenorcustomer.features.onBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.travenorcustomer.R

@Composable
fun OnBoardScreen(
    modifier: Modifier = Modifier
) {
    Column{

            Image(
                painter = painterResource(R.drawable.boat),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight(.5f)
                    .clip(
                        shape = RoundedCornerShape(30.dp)
                    )
            )

        Text(
            text = "Life is short and the"
        )

        Row{
            Text(
                text = "world is "
            )
            Column {
                Text(
                    text = "Life is short and the"
                )
                Image(
                    painter = painterResource(R.drawable.boat),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight(.5f)
                        .clip(
                            shape = RoundedCornerShape(30.dp)
                        )
                )
            }
        }

    }
}