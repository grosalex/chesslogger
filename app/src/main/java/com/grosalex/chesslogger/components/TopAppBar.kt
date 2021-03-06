package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.ui.textOnPrimary
import com.grosalex.chesslogger.viewmodels.NewGameViewModel

@Composable
fun TopAppBar(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    newGameViewModel: NewGameViewModel
) {
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
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
            when (currentRoute) {
                Screen.NewGame.route -> {
                    IconButton(onClick = {
                        setShowDialog(true)
                    }) {
                        Icon(vectorResource(id = R.drawable.ic_save))
                    }
                }
                Screen.SavedGames.route -> {

                }
                Screen.SavedGameDetail.route -> {
                    Text(text = "Previous")
                    Text(text = "Next")
                }
            }
        }
    )
    SaveDialog(
        newGameViewModel = newGameViewModel,
        showDialog = showDialog,
        setShowDialog = setShowDialog
    )
}

@Composable
fun SaveDialog(
    newGameViewModel: NewGameViewModel,
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit
) {
    if (showDialog) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
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
                        newGameViewModel.save(textState.value.text)
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
                DefaultTextField(modifier = Modifier.padding(8.dp)) {
                    textState.value = it
                }
            },
        )
    }
}