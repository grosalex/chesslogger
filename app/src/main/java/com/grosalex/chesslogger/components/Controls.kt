package com.grosalex.chesslogger.components

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.models.LETTER
import com.grosalex.chesslogger.models.Number
import com.grosalex.chesslogger.models.Piece
import com.grosalex.chesslogger.models.Special
import com.grosalex.chesslogger.states.AppState
import org.rekotlin.StoreType

@ExperimentalLayout
@Composable
fun Controls(store: StoreType<AppState>) =
    ScrollableColumn(modifier = Modifier.padding(start = 8.dp)) {
        FlowRow() {
            FullLineText(text = stringResource(id = R.string.piece))
            Piece.values().forEach {
                ControlButton(notationStringRes = it.notationStringRes)
            }

            FullLineText(text = stringResource(id = R.string.letter))
            LETTER.values().forEach {
                ControlButton(notationStringRes = it.notationStringRes)
            }

            FullLineText(text = stringResource(id = R.string.number))
            Number.values().forEach {
                ControlButton(notationStringRes = it.notationStringRes)
            }

            FullLineText(text = stringResource(id = R.string.special))
            Special.values().forEach {
                ControlButton(notationStringRes = it.notationStringRes)
            }
        }
    }

@Composable
fun ControlButton(notationStringRes: Int) = Button(
    modifier = Modifier.padding(2.dp),
    onClick = {
    }) {
    Text(text = stringResource(id = notationStringRes))
}

@Composable
fun FullLineText(text: String) =
    Text(modifier = Modifier.fillMaxWidth(), text = text, fontWeight = FontWeight.Bold)
