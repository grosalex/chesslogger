package com.grosalex.chesslogger.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val lightColors = lightColors(
    primary = primary,
    primaryVariant = primaryLight,
    secondary = secondary,
    secondaryVariant = secondaryLight,
    onPrimary = textOnPrimary,
    onSecondary = textOnSecondary,
    onSurface = transparent,
    /* Other default colors to override
  background = Color.White,
  surface = Color.White,
  onBackground = Color.Black,
  onSurface = Color.Black,
  */
)
@Composable
fun ChessLoggerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    /*val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }*/

    MaterialTheme(
            colors = lightColors,
            typography = typography,
            shapes = shapes,
            content = content
    )
}