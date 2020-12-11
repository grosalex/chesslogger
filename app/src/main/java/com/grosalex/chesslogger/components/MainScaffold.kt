package com.grosalex.chesslogger.components

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.grosalex.chesslogger.ui.primaryDark

@Composable
fun mainScaffold(scaffoldState: ScaffoldState = rememberScaffoldState()) = Scaffold(
    scaffoldState = scaffoldState,
    topBar = { topAppBar(scaffoldState = scaffoldState) },
    drawerContent = { mainDrawerContent() },
    drawerBackgroundColor = primaryDark
) {}