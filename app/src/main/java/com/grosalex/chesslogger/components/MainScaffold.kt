package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.grosalex.chesslogger.ChessLoggerApplication
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.entities.moves
import com.grosalex.chesslogger.ui.primaryDark
import com.grosalex.chesslogger.ui.separator
import com.grosalex.chesslogger.viewmodels.NewGameViewModel
import com.grosalex.chesslogger.viewmodels.SavedGamesViewModel

@ExperimentalLayout
@Composable
fun MainScaffold(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val navController = rememberNavController()
    val savedGamesViewModel = SavedGamesViewModel(ChessLoggerApplication.app)
    val newGameViewModel = NewGameViewModel(ChessLoggerApplication.app)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(scaffoldState = scaffoldState, newGameViewModel = newGameViewModel) },
        drawerContent = { MainDrawerContent(scaffoldState, navController) },
        drawerBackgroundColor = primaryDark,
    ) {
        NavHost(navController = navController, startDestination = Screen.NewGame.route) {
            composable(Screen.NewGame.route) { NewGame(newGameViewModel) }
            composable(Screen.SavedGames.route) {
                SavedGames(
                    navController = navController,
                    savedGamesViewModel
                )
            }
            composable("gameDetail/{uid}") { navBackStackEntry ->
                SavedGame(savedGamesViewModel, navBackStackEntry.arguments?.getString("uid"))
            }
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
            Row {
                MovementsList(movements = moves, currentMove = emptyList())
            }
        }
    }

}

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

@ExperimentalLayout
@Composable
fun NewGame(newGameViewModel: NewGameViewModel) {
    /*  store.subscribe(BlockSubscriber { appState ->
          lastMoves = appState.currentGameState.lastMoves
          currentMove = appState.currentGameState.currentMove
      })*/
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
        PlayersRow(newGameViewModel)
        Row(
            Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MovementsList(lastMoves, currentMove)
            Controls(newGameViewModel = newGameViewModel)
        }
    }
}

@Composable
fun PlayersRow(newGameViewModel: NewGameViewModel) {
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