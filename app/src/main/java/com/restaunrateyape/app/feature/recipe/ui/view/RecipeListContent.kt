package com.restaunrateyape.app.feature.recipe.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData

@Composable
fun RecipeListContent(
    list: List<RecipeData>,
    modifier: Modifier = Modifier,
    onRecipeClick: (Int) -> Unit,
    listState: LazyListState = rememberLazyListState(),
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(items = list, key = { it.id }) { data ->
                RecipeItem(data = data, onClick = onRecipeClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListContentPreview() {
    val sampleData = listOf(
        RecipeData(id = 1, name = "Lomo Saltado", imageUrl = "https://example.com/pancakes.jpg"),
        RecipeData(id = 2, name = "Cebiche", imageUrl = "https://example.com/waffles.jpg"),
        RecipeData(id = 3, name = "Patasca", imageUrl = "https://example.com/omelette.jpg")
    )

    RecipeListContent(
        list = sampleData,
        onRecipeClick = {}
    )
}