package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpace(height: Dp) = Box(modifier = Modifier.fillMaxWidth().height(height))

@Composable
fun HorizontalSpace(width: Dp) = Box(modifier = Modifier.fillMaxHeight().width(width))