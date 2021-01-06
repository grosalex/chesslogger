package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grosalex.chesslogger.ChessLoggerApplication
import com.grosalex.chesslogger.viewmodels.NewGameViewModel
import com.grosalex.chesslogger.viewmodels.SavedGamesViewModel

@ExperimentalLayout
@Composable
fun MainScaffold(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val navController = rememberNavController()
    val savedGamesViewModel = SavedGamesViewModel(ChessLoggerApplication.app)
    val newGameViewModel = NewGameViewModel(ChessLoggerApplication.app)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                scaffoldState = scaffoldState,
                navController,
                newGameViewModel = newGameViewModel
            )
        },
        drawerContent = { MainDrawerContent(scaffoldState, navController) }
    ) {
        NavHost(navController = navController, startDestination = Screen.NewGame.route) {
            composable(Screen.NewGame.route) { NewGame(newGameViewModel) }
            composable(Screen.SavedGames.route) {
                SavedGames(
                    navController = navController,
                    savedGamesViewModel
                )
            }
            composable(Screen.SavedGameDetail.route) { navBackStackEntry ->
                SavedGame(savedGamesViewModel, navBackStackEntry.arguments?.getString("uid"))
            }
        }
    }
}