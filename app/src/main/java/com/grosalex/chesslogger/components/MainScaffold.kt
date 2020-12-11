package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grosalex.chesslogger.ui.primaryDark

@Composable
fun MainScaffold(scaffoldState: ScaffoldState = rememberScaffoldState()) = Scaffold(
    scaffoldState = scaffoldState,
    topBar = { TopAppBar(scaffoldState = scaffoldState) },
    drawerContent = { MainDrawerContent() },
    drawerBackgroundColor = primaryDark,

    ) {
    Row(
        Modifier.padding(16.dp).fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        PastMovements()
        Controls()
    }
}

@Preview
@Composable
fun PreviewMainScaffold() = MainScaffold()