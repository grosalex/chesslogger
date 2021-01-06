package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.ui.black


@Composable
fun DefaultTextField(modifier: Modifier = Modifier, onValueChange: (TextFieldValue) -> Unit = {}) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
            onValueChange.invoke(it)
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(id = R.string.title))
        },
        backgroundColor = MaterialTheme.colors.background,
    )
}

@Composable
fun PlayerTextField(
    modifier: Modifier = Modifier,
    label: Int,
    onValueChange: (String) -> Unit,
    playerName:String
) {
    TextField(
        value = playerName,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = label)) },
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier.padding(8.dp)
    )
}
