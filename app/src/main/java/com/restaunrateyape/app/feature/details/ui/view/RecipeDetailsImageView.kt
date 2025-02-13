package com.restaunrateyape.app.feature.details.ui.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.restaunrateyape.styles.theme.RecipeYapeAppTheme

@Composable
fun RecipeDetailImageView(imageUrl: String) {
    var imageLoading by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 16.dp, bottom = 16.dp)
            .animateContentSize(),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .listener(
                    onSuccess = { _, _ -> imageLoading = false },
                    onError = { _, _ -> imageLoading = false },
                )
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(size = 16.dp)),
            alpha = if (imageLoading) 0f else 1f,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailHeaderItemViewPreview() {
    RecipeYapeAppTheme {
        RecipeDetailImageView(imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg")
    }
}
