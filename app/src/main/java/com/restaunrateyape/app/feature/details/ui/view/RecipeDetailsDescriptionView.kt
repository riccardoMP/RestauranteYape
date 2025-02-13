package com.restaunrateyape.app.feature.details.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.restaunrateyape.styles.theme.RecipeYapeAppTheme
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.Description

@Composable
fun RecipeDetailsDescription(description: Description) {
    Text(
        text = description.value,
        overflow = TextOverflow.Visible,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(16.dp),
    )
}

@Preview(
    showBackground = true,
    name = "Light Mode",
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
)
@Composable
fun DetailDescriptionPreview() {
    RecipeYapeAppTheme {
        val data =
            Description(value = "El ceviche es un plato tradicional peruano que consiste en pescado crudo marinado en jugo de limón, acompañado de cebolla, ají y cilantro.")
        RecipeDetailsDescription(description = data)
    }
}
