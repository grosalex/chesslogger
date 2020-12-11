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
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.grosalex.chesslogger.ui.primaryDark

@Composable
fun mainScaffold(scaffoldState: ScaffoldState = rememberScaffoldState()) = Scaffold(
    scaffoldState = scaffoldState,
    topBar = { topAppBar(scaffoldState = scaffoldState) },
    drawerContent = { mainDrawerContent() },
    drawerBackgroundColor = primaryDark,

    ) {
    Row(
        Modifier.padding(16.dp).fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        pastMovements()
        controls()
    }
}

@Preview
@Composable
fun previewMainScaffold() = mainScaffold()