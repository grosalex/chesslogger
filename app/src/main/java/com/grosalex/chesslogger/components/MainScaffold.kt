package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.actions.CurrentGameActions
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.fakeStore
import com.grosalex.chesslogger.ui.black
import com.grosalex.chesslogger.ui.primaryDark
import org.rekotlin.BlockSubscriber
import org.rekotlin.StoreType

@ExperimentalLayout
@Composable
fun MainScaffold(
    store: StoreType<AppState>,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) =
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(scaffoldState = scaffoldState) },
        drawerContent = { MainDrawerContent() },
        drawerBackgroundColor = primaryDark,

        ) {
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

@Composable
fun PlayerTextField(
    store: StoreType<AppState>,
    modifier: Modifier = Modifier,
    label: Int,
    isWhitePlayer: Boolean
) {
    var textState by remember { mutableStateOf(if (isWhitePlayer) store.state.currentGameState.players.first else store.state.currentGameState.players.second) }
    store.subscribe(BlockSubscriber { state ->
        textState = if (isWhitePlayer)
            store.state.currentGameState.players.first
        else store.state.currentGameState.players.second
    })
    TextField(
        value = textState,
        onValueChange = {
            if (isWhitePlayer) {
                store.dispatch(CurrentGameActions.SetWhitePlayerName(it))
            } else {
                store.dispatch(CurrentGameActions.SetBlackPlayerName(it))
            }
        },
        label = { Text(text = stringResource(id = label)) },
        inactiveColor = black,
        modifier = modifier.padding(8.dp)
    )
}

@ExperimentalLayout
@Preview
@Composable
fun PreviewMainScaffold() {
    MainScaffold(fakeStore())
}

