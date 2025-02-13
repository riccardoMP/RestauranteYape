package com.restaunrateyape.styles.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val CardDark = Color(0xFF3B3E43)
val CardLight = White

val DividerLight = Color(0xFFE0E0E0)
val DividerDark = Color(0xFF6E6E6E)

val DividerColor: Color
    @Composable get() = if (isSystemInDarkTheme()) DividerDark else DividerLight

val CardBackgroundColor: Color
    @Composable get() = if (isSystemInDarkTheme()) CardDark else CardLight
