package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.fakeStore
import com.grosalex.chesslogger.ui.primaryDark
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
            composable(Screen.SavedGames.route) { SavedGames(store = store) }
        }
    }
}

@Composable
fun SavedGames(store: StoreType<AppState>) {
    val savedGames = store.state.savedGamesState.savedGames.collectAsState(initial = emptyList())

    LazyColumnFor(items = savedGames.value) {
        Text(text = it.title)
    }
}

@ExperimentalLayout
@Composable
fun NewGame(store: StoreType<AppState>) {
    Column() {
        PlayersRow(store = store)
        Row(
            Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PastMovements(store)
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
        PlayerTextField(
            store = store,
            modifier = Modifier.weight(1f),
            label = R.string.white_player,
            isWhitePlayer = true
        )
        PlayerTextField(
            store = store,
            modifier = Modifier.weight(1f),
            label = R.string.black_player,
            isWhitePlayer = false
        )
    }
}

@ExperimentalLayout
@Preview
@Composable
fun PreviewMainScaffold() {
    MainScaffold(fakeStore())
}

