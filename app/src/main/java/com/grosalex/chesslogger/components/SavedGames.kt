package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import com.grosalex.chesslogger.viewmodels.SavedGamesViewModel


@Composable
fun SavedGames(navController: NavController, savedGamesViewModel: SavedGamesViewModel) {
    val savedGames by savedGamesViewModel.getAllGames().collectAsState(emptyList())
    LazyColumn() {
        items(items = savedGames) { item ->
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                onClick = {
                    navController.navigate("gameDetail/${item.uid}")
                }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterVertically),
                        text = item.title
                    )
                    IconButton(
                        onClick = {
                            savedGamesViewModel.deleteGame(item)
                        },
                        modifier = Modifier.align(Alignment.Bottom),
                    ) {
                        Icon(
                            imageVector = vectorResource(id = R.drawable.ic_delete),
                        )
                    }
                }
            }
            VerticalSeparator()
        }
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
            Spacer(modifier = Modifier.height(8.dp))
            PlayersRow(
                whitePlayerName = savedGame.whitePlayerName,
                blackPlayerName = savedGame.blackPlayerName
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                MovementsList(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    movements = moves,
                    currentMove = emptyList()
                )
            }
        }
    }

}