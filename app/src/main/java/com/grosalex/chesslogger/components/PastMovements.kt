package com.grosalex.chesslogger.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.models.Key

@Composable
fun MovementsList(movements: List<Pair<List<Key>?, List<Key>?>>, currentMove: List<Key>) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        itemsIndexed(movements) { index, item ->
            PairedMovement(moves = item, currentMove = currentMove, index + 1)
        }
    }
}

@Composable
fun PairedMovement(
    moves: Pair<List<Key>?, List<Key>?>,
    currentMove: List<Key>,
    movementCount: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        /*
        TODO add moves count
        Text(
            modifier = Modifier.padding(end = 4.dp),
            fontWeight = FontWeight.Bold,
            text = String.format("%02d", movementCount)
        )*/
        Movement(movement = moves.first?.map { stringResource(id = it.notationStringRes) }
            ?.joinToString(separator = ""), if (moves.first == null) currentMove else null)
        Movement(movement = moves.second?.map { stringResource(id = it.notationStringRes) }
            ?.joinToString(separator = ""), if (moves.first != null) currentMove else null)
    }
}

@Composable
fun Movement(movement: String?, currentMove: List<Key>?) {
    Box(
        modifier = Modifier
            .border(1.dp, MaterialTheme.colors.onSurface)
            .padding(4.dp)
    ) {
        Text(
            modifier = Modifier
                .width(64.dp)
                .padding(8.dp),
            text = movement ?: currentMove?.map { stringResource(id = it.notationStringRes) }
                ?.joinToString(separator = "") ?: " - ",
            textAlign = TextAlign.Center
        )
    }
}
