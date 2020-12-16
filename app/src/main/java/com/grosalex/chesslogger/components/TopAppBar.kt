package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.ui.textOnPrimary

@Composable
fun TopAppBar(scaffoldState: ScaffoldState) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = textOnPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = { scaffoldState.drawerState.open() }) {
                Icon(vectorResource(id = R.drawable.ic_burger_menu))
            }
        },
        actions = {
            IconButton(onClick = {
                setShowDialog(true)
            }) {
                Icon(vectorResource(id = R.drawable.ic_save))
            }
        }
    )
    SaveDialog(showDialog = showDialog, setShowDialog = setShowDialog)
}

@Composable
fun SaveDialog(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(stringResource(id = R.string.save))
            },
            confirmButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                    },
                ) {
                    Text(stringResource(id = R.string.save))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                    },
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
            },
            text = {
                DefaultTextField(modifier = Modifier.padding(8.dp))
            },
        )
    }
}