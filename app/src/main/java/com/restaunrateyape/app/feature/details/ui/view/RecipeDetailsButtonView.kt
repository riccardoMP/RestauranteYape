package com.restaunrateyape.app.feature.details.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.ButtonData
import com.restaunrateyape.styles.theme.RecipeYapeAppTheme
import com.restaunrateyape.app.R

@Composable
fun RecipeDetailButtonView(buttonData: ButtonData, onButtonClick: (Pair<Double, Double>) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        with(buttonData) {
            Button(onClick = { onButtonClick(Pair(latitude, longitude)) }
            ) {
                Text(text = stringResource(value))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailButtonPreview() {
    RecipeYapeAppTheme {
        val data = ButtonData(R.string.details_see_location, 0.0, 0.0)
        RecipeDetailButtonView(buttonData = data, {})
    }
}
