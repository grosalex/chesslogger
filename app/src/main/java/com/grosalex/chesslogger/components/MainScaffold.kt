package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.actions.GameActions
import com.grosalex.chesslogger.entities.moves
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.fakeStore
import com.grosalex.chesslogger.ui.primaryDark
import com.grosalex.chesslogger.ui.separator
import org.rekotlin.BlockSubscriber
import org.rekotlin.StoreType

@ExperimentalLayout
@Composable
fun MainScaffold(
    store: StoreType<AppState>,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(scaffoldState = scaffoldState, store) },
        drawerContent = { MainDrawerContent(scaffoldState, navController) },
        drawerBackgroundColor = primaryDark,
    ) {
        NavHost(navController = navController, startDestination = Screen.NewGame.route) {
            composable(Screen.NewGame.route) { NewGame(store = store) }
            composable(Screen.SavedGames.route) {
                SavedGames(
                    store = store,
                    navController = navController
                )
            }
            composable("gameDetail/{uid}") { navBackStackEntry ->
                SavedGame(store, navBackStackEntry.arguments?.getString("uid"))
            }
        }
    }
}

@Composable
fun SavedGame(store: StoreType<AppState>, gameId: String?) {
    val savedGames = store.state.savedGamesState.savedGames.collectAsState(initial = emptyList())
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
fun SavedGames(store: StoreType<AppState>, navController: NavController) {
    val savedGames = store.state.savedGamesState.savedGames.collectAsState(initial = emptyList())
    LazyColumnFor(items = savedGames.value) {
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
                        store.dispatch(GameActions.Delete(it))
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
fun NewGame(store: StoreType<AppState>) {
    var lastMoves by remember { mutableStateOf(store.state.currentGameState.lastMoves) }
    var currentMove by remember { mutableStateOf(store.state.currentGameState.currentMove) }
    store.subscribe(BlockSubscriber { appState ->
        lastMoves = appState.currentGameState.lastMoves
        currentMove = appState.currentGameState.currentMove
    })
    Column() {
        PlayersRow(store = store)
        Row(
            Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MovementsList(lastMoves, currentMove)
            Controls(store = store)
        }
    }
}

@Composable
fun PlayersRow(store: StoreType<AppState>) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        var whitePlayerName by remember { mutableStateOf(store.state.currentGameState.players.first) }
        var blackPlayerName by remember { mutableStateOf(store.state.currentGameState.players.second) }
        store.subscribe(BlockSubscriber { state ->
            whitePlayerName = store.state.currentGameState.players.first
            blackPlayerName = store.state.currentGameState.players.second
        })
        PlayerTextField(
            modifier = Modifier.weight(1f),
            label = R.string.white_player,
            onValueChange = {
                store.dispatch(GameActions.SetWhitePlayerName(it))
            },
            whitePlayerName
        )
        PlayerTextField(
            modifier = Modifier.weight(1f),
            label = R.string.black_player,
            onValueChange = {
                store.dispatch(GameActions.SetBlackPlayerName(it))
            },
            blackPlayerName
        )
    }
}

@ExperimentalLayout
@Preview
@Composable
fun PreviewMainScaffold() {
    MainScaffold(fakeStore())
}

