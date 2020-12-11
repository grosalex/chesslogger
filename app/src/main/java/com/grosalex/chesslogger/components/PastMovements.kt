package com.grosalex.chesslogger.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.ui.black


val list = listOf(
    Pair("e4", "e5"),
    Pair("d3", "d6")
)

@Composable
fun pastMovements(moves: List<Pair<String, String>> = list) =
    LazyColumnFor(moves) { item ->
        pairedMovement(moves = item)
    }


@Composable
fun pairedMovement(moves: Pair<String?, String?>) {
    Row {
        movement(movement = moves.first)
        movement(movement = moves.second)
    }
}

@Composable
fun movement(movement: String?) = Box(
    modifier = Modifier.border(1.dp, black).padding(4.dp)
) { Text(modifier = Modifier.padding(8.dp), text = movement.orEmpty()) }


@Composable
@Preview
fun previewPastMovement() = pastMovements(list)