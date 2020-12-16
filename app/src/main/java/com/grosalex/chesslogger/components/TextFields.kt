package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.actions.CurrentGameActions
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.ui.black
import org.rekotlin.BlockSubscriber
import org.rekotlin.StoreType


@Composable
fun DefaultTextField(modifier: Modifier = Modifier) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(id = R.string.title))
        },
        inactiveColor = black,
    )
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
