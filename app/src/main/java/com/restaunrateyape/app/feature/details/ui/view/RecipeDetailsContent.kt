package com.restaunrateyape.app.feature.details.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.Description
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.Divider
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.ImageUrl
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.RowInformation
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.TitleRes
import com.restaunrateyape.styles.component.divider.YapeDivider
import com.restaunrateyape.styles.theme.RecipeYapeAppTheme
import com.restaunrateyape.app.R
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.Bullet
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.TitleString

@Composable
fun RecipeDetailsContent(list: List<RecipeDetailData>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = list) { data ->
            CharacterDetailItem(data)
        }
    }
}

@Composable
fun CharacterDetailItem(data: RecipeDetailData) {
    when (data) {
        is ImageUrl -> RecipeDetailImageView(imageUrl = data.imageUrl)
        is TitleRes -> RecipeDetailsTitleRes(title = data.title)
        is TitleString -> RecipeDetailsTitleString(title = data.title)
        is RowInformation -> RecipeDetailsTextRow(information = data)
        is Description -> RecipeDetailsDescription(description = data)
        is Bullet -> RecipeDetailsBullet(bulletList = data.bullets)
        is Divider -> YapeDivider()
        else -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    RecipeYapeAppTheme {
        val list = listOf(
            ImageUrl("image"),
            TitleRes(R.string.details_information),
            RowInformation(R.string.details_name, "Ceviche"),
            Divider,
            Description("El ceviche es un plato tradicional peruano que consiste en pescado crudo marinado en jugo de limón, acompañado de cebolla, ají y cilantro."),
            Divider,
            TitleRes(R.string.details_ingredients),
            Divider,
            Bullet(bullets = listOf()),
            TitleRes(R.string.details_location),
        )
        RecipeDetailsContent(list = list)


    }
}
