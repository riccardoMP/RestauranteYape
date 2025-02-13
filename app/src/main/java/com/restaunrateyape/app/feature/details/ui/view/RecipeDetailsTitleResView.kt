package com.restaunrateyape.app.feature.details.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.restaunrateyape.app.R
import com.restaunrateyape.styles.theme.RecipeYapeAppTheme

@Composable
fun RecipeDetailsTitleRes(title: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = title),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
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
fun DetailsTitleResPreview() {
    RecipeYapeAppTheme {
        RecipeDetailsTitleRes(title = R.string.details_information)
    }
}
