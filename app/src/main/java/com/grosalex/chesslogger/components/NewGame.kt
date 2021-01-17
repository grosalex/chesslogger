package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.viewmodels.NewGameViewModel

@ExperimentalLayout
@Composable
fun NewGame(newGameViewModel: NewGameViewModel) {
    val lastMoves by newGameViewModel.lastMoves.observeAsState(
        mutableListOf(
            Pair(
                emptyList(),
                emptyList()
            )
        )
    )
    val currentMove by newGameViewModel.currentMove.observeAsState(mutableListOf())
    Column() {
        //TODO move players name in a collapsable toolbar
        EditablePlayersRow(newGameViewModel)
        Row(
            Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MovementsList(movements = lastMoves, currentMove = currentMove)
            Controls(newGameViewModel = newGameViewModel)
        }
    }
}
