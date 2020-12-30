package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.entities.moves
import com.grosalex.chesslogger.ui.separator
import com.grosalex.chesslogger.viewmodels.SavedGamesViewModel


@Composable
fun SavedGames(navController: NavController, savedGamesViewModel: SavedGamesViewModel) {
    val savedGames by savedGamesViewModel.getAllGames().collectAsState(emptyList())
    LazyColumnFor(items = savedGames) {
        TextButton(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
            onClick = {
                navController.navigate("gameDetail/${it.uid}")
            }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = it.title
                )
                IconButton(
                    onClick = {
                        savedGamesViewModel.deleteGame(it)
                    },
                    modifier = Modifier.align(Alignment.Bottom),
                ) {
                    Icon(
                        imageVector = vectorResource(id = R.drawable.ic_delete),
                    )
                }
            }
        }
        Surface(
            modifier = Modifier.fillMaxWidth().height(1.dp).padding(start = 16.dp, end = 16.dp),
            color = separator
        ) {}
    }
}

@Composable
fun SavedGame(savedGamesViewModel: SavedGamesViewModel, gameId: String?) {
    val savedGames = savedGamesViewModel.getAllGames().collectAsState(emptyList())
    val savedGame = savedGames.value.find { it.uid.toString() == gameId }
    val moves = savedGame?.moves()
    if (moves.isNullOrEmpty()) {
        Text(text = "It's empty")
    } else {
        Column() {
            Row {
                MovementsList(movements = moves, currentMove = emptyList())
            }
        }
    }

}