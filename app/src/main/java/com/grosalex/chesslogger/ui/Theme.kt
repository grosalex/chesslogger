package com.grosalex.chesslogger.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val lightColors = lightColors(
    primary = primary,
    primaryVariant = primaryDark,
    secondary = secondary,
    secondaryVariant = secondaryDark,
    onPrimary = textOnPrimary,
    onSecondary = textOnSecondary,
    surface = primaryDark,
    onSurface = primary
)

val darkColors = darkColors(
    primary = primary,
    primaryVariant = primaryDark,
    secondary = secondary,
    onPrimary = textOnPrimary,
    onSecondary = textOnSecondary,
    onSurface = primary
)

@Composable
fun ChessLoggerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors
    } else {
        lightColors
    }
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}