package com.grosalex.chesslogger.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.fakeStore
import com.grosalex.chesslogger.ui.black
import org.rekotlin.BlockSubscriber
import org.rekotlin.StoreType

@Composable
fun PastMovements(store: StoreType<AppState>) {
    var lastMoves by remember { mutableStateOf(store.state.currentGameState.lastMoves) }

    store.subscribe(BlockSubscriber<AppState> { appState ->
        lastMoves = appState.currentGameState.lastMoves
    })
    return LazyColumnFor(lastMoves) { item ->
        PairedMovement(moves = item)
    }
}


@Composable
fun PairedMovement(moves: Pair<String?, String?>) {
    Row {
        Movement(movement = moves.first)
        Movement(movement = moves.second)
    }
}

@Composable
fun Movement(movement: String?) = Box(
    modifier = Modifier.border(1.dp, black).padding(4.dp)
) { Text(modifier = Modifier.padding(8.dp), text = movement.orEmpty()) }


@Composable
@Preview
fun PreviewPastMovement() = PastMovements(fakeStore())