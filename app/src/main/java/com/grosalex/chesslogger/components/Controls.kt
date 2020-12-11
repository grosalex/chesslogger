package com.grosalex.chesslogger.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.grosalex.chesslogger.actions.CurrentGameActions
import com.grosalex.chesslogger.states.AppState
import org.rekotlin.StoreType

@Composable
fun Controls(store: StoreType<AppState>) = Button(onClick = {
    store.dispatch(CurrentGameActions.AddMove("a7"))
}) {
    Text(text = "Test")
}