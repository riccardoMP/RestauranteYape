package com.restaunrateyape.app.feature.recipe.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData

@Composable
fun RecipeItem(
    data: RecipeData,
    onClick: (Int) -> Unit,
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Card(
            modifier = Modifier
                .animateContentSize()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable { onClick(data.id) },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = data.imageUrl,
                    modifier = Modifier.fillMaxWidth(0.25f),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                )
                RecipeInfo(
                    data = data,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                )
            }
        }
    }
}

@Composable
fun RecipeInfo(
    data: RecipeData,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = alignment,
    ) {
        with(data) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Status", fontSize = 13.sp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(color = Color.Red, shape = CircleShape),
                )
                Text(text = "status" + " - " + "species")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeItemPreview() {
    RecipeItem(
        data = RecipeData(
            id = 1,
            name = "Lomo Saltado",
            imageUrl = "https://example.com/pancakes.jpg"
        ),
        onClick = { }
    )
}