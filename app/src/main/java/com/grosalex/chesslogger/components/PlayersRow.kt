package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.viewmodels.NewGameViewModel


@Composable
fun EditablePlayersRow(newGameViewModel: NewGameViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        val whitePlayerName: String by newGameViewModel.whitePlayer.observeAsState("")
        val blackPlayerName: String by newGameViewModel.blackPlayer.observeAsState(initial = "")
        PlayerTextField(
            modifier = Modifier.weight(1f),
            label = R.string.white_player,
            onValueChange = {
                newGameViewModel.onWhitePlayerChanges(it)
            },
            whitePlayerName
        )
        PlayerTextField(
            modifier = Modifier.weight(1f),
            label = R.string.black_player,
            onValueChange = {
                newGameViewModel.onBlackPlayerChanges(it)
            },
            blackPlayerName
        )
    }
}

@Composable
fun PlayersRow(whitePlayerName: String, blackPlayerName: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = whitePlayerName
        )
        Text(
            text = blackPlayerName
        )
    }
}
