package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.ui.separator

@Composable
fun VerticalSpace(height: Dp) = Box(modifier = Modifier.fillMaxWidth().height(height))

@Composable
fun HorizontalSpace(width: Dp) = Box(modifier = Modifier.fillMaxHeight().width(width))

@Composable
fun VerticalSeparator(height: Dp = 1.dp, color: Color = separator){
    Surface(
        modifier = Modifier.fillMaxWidth().height(height).padding(start = 16.dp, end = 16.dp),
        color = color
    ) {}
}