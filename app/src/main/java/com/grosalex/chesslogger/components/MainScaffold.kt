package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.fakeStore
import com.grosalex.chesslogger.ui.black
import com.grosalex.chesslogger.ui.primaryDark
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
            PlayersRow()
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
fun PlayersRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        PlayerTextField(modifier = Modifier.weight(1f), label = R.string.white_player)
        PlayerTextField(modifier = Modifier.weight(1f), label = R.string.black_player)
    }
}

@Composable
fun PlayerTextField(modifier: Modifier = Modifier, label: Int) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
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

@Preview("Players")
@Composable
fun PreviewPlayers() = PlayersRow()
