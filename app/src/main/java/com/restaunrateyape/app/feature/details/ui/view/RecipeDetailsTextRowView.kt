package com.restaunrateyape.app.feature.details.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData
import com.restaunrateyape.styles.theme.CardBackgroundColor
import com.restaunrateyape.styles.theme.RecipeYapeAppTheme
import com.restaunrateyape.app.R

@Composable
fun RecipeDetailsTextRow(information: RecipeDetailData.RowInformation) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBackgroundColor)
            .padding(12.dp),
    ) {
        val (title, value) = createRefs()

        Text(
            text = stringResource(id = information.title),
            maxLines = 1,
            overflow = TextOverflow.Visible,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
        )

        Text(
            text = information.value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.constrainAs(value) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
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
fun DetailTextRowPreview() {
    RecipeYapeAppTheme {
        val data = RecipeDetailData.RowInformation(title = R.string.details_origin, value = "Origen")
        RecipeDetailsTextRow(information = data)
    }
}
