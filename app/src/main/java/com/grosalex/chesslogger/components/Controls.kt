package com.grosalex.chesslogger.components

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.actions.CurrentGameActions
import com.grosalex.chesslogger.models.*
import com.grosalex.chesslogger.models.Number
import com.grosalex.chesslogger.states.AppState
import org.rekotlin.StoreType

@ExperimentalLayout
@Composable
fun Controls(store: StoreType<AppState>) =
    ScrollableColumn(modifier = Modifier.padding(start = 8.dp)) {
        FlowRow() {
            FullLineText(text = stringResource(id = R.string.piece))
            Piece.values().forEach {
                ControlButton(store = store, it)
            }

            FullLineText(text = stringResource(id = R.string.letter))
            LETTER.values().forEach {
                ControlButton(store = store, it)
            }

            FullLineText(text = stringResource(id = R.string.number))
            Number.values().forEach {
                ControlButton(store = store, it)
            }

            FullLineText(text = stringResource(id = R.string.special))
            Special.values().forEach {
                ControlButton(store = store, it)
            }

            FullLineButton(
                text = stringResource(id = R.string.add_annotation),
                onClick = { store.dispatch(CurrentGameActions.AddMove()) })
        }
    }

@Composable
fun ControlButton(store: StoreType<AppState>, key: Key) = Button(
    modifier = Modifier.padding(2.dp),
    onClick = {
        store.dispatch(CurrentGameActions.KeyPressed(key = key))
    }) {
    Text(text = stringResource(id = key.notationStringRes))
}

@Composable
fun FullLineText(text: String) =
    Text(modifier = Modifier.fillMaxWidth(), text = text, fontWeight = FontWeight.Bold)

@Composable
fun FullLineButton(text: String, onClick: () -> Unit) =
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = text)
    }