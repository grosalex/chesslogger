package com.grosalex.chesslogger.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.grosalex.chesslogger.ChessLoggerApplication
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.entities.moves
import com.grosalex.chesslogger.ui.primaryDark
import com.grosalex.chesslogger.ui.separator
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
        topBar = { TopAppBar(scaffoldState = scaffoldState, newGameViewModel = newGameViewModel) },
        drawerContent = { MainDrawerContent(scaffoldState, navController) },
        drawerBackgroundColor = primaryDark,
    ) {
        NavHost(navController = navController, startDestination = Screen.NewGame.route) {
            composable(Screen.NewGame.route) { NewGame(newGameViewModel) }
            composable(Screen.SavedGames.route) {
                SavedGames(
                    navController = navController,
                    savedGamesViewModel
                )
            }
            composable("gameDetail/{uid}") { navBackStackEntry ->
                SavedGame(savedGamesViewModel, navBackStackEntry.arguments?.getString("uid"))
            }
        }
    }
}