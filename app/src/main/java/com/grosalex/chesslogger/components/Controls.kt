package com.grosalex.chesslogger.components

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.models.Key
import com.grosalex.chesslogger.viewmodels.NewGameViewModel

@ExperimentalLayout
@Composable
fun Controls(newGameViewModel: NewGameViewModel) =
    ScrollableColumn(modifier = Modifier.padding(start = 8.dp)) {
        FlowRow {
            FullLineText(text = stringResource(id = R.string.piece))
            Key.pieces.forEach {
                KeyControlButton(newGameViewModel = newGameViewModel, it)
            }

            FullLineText(text = stringResource(id = R.string.special))
            Key.specials.forEach {
                KeyControlButton(newGameViewModel = newGameViewModel, it)
            }

            FullLineText(text = stringResource(id = R.string.letter))
            Key.letters.forEach {
                KeyControlButton(newGameViewModel = newGameViewModel, it)
            }

            FullLineText(text = stringResource(id = R.string.number))
            Key.numbers.forEach {
                KeyControlButton(newGameViewModel = newGameViewModel, it)
            }

            Button(
                modifier = Modifier.padding(2.dp),
                onClick = {
                    newGameViewModel.removeLastMove()
                }) {
                Text(text = stringResource(id = R.string.cancel_last_annotation))
            }

            Button(
                modifier = Modifier.padding(2.dp),
                onClick = {
                    newGameViewModel.erased()
                }) {
                Text(text = stringResource(id = R.string.erased))
            }

            ForceBreakLine()
            FullLineButton(
                text = stringResource(id = R.string.add_annotation),
                onClick = {
                    newGameViewModel.addMove()
                })

        }
    }

@Composable
fun KeyControlButton(newGameViewModel: NewGameViewModel, key: Key) = Button(
    modifier = Modifier.padding(2.dp),
    onClick = {
        newGameViewModel.keyPressed(key)
    }) {
    Text(text = stringResource(id = key.notationStringRes))
}

@Composable
fun FullLineText(text: String) =
    Text(modifier = Modifier.fillMaxWidth(), text = text, fontWeight = FontWeight.Bold)

@Composable
fun ForceBreakLine() {
    Spacer(modifier = Modifier.fillMaxWidth().height(0.dp))
}

@Composable
fun FullLineButton(text: String, onClick: () -> Unit) =
    Button(
        modifier = Modifier.padding(2.dp)
            .fillMaxWidth(if (booleanResource(id = R.bool.is_tablet)) 0.5f else 1f),
        onClick = onClick
    ) {
        Text(text = text)
    }