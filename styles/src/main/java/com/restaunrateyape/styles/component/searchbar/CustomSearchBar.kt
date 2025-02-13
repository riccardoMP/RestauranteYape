package com.restaunrateyape.styles.component.searchbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.restaunrateyape.styles.R
import com.restaunrateyape.styles.component.divider.YapeDivider

@Composable
fun CustomSearchBar(
    value: String,
    placeholder: String,
    navigateUp: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val requester = remember { FocusRequester() }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { navigateUp() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester = requester),
                value = value,
                onValueChange = { name ->
                    onValueChange(name)
                },
                placeholder = {
                    Text(text = placeholder)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                trailingIcon = {
                    if (value.isNotBlank()) {
                        IconButton(onClick = { onValueChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(R.string.content_description_clear_search),
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(20.dp),
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    },
                ),
            )
        }

        YapeDivider(modifier = Modifier.fillMaxWidth())
    }
    SideEffect {
        requester.requestFocus()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomSearchBar() {
    var searchValue by remember { mutableStateOf("") }

    CustomSearchBar(
        value = searchValue,
        placeholder = "Search...",
        navigateUp = { /* Handle navigation */ },
        onValueChange = { newValue ->
            searchValue = newValue
        },
    )
}
