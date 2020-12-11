package com.grosalex.chesslogger.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.ui.textOnPrimary

@Composable
fun TopAppBar(scaffoldState: ScaffoldState) = TopAppBar(
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
    }
)
