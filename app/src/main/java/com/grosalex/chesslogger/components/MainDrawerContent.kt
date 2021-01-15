package com.grosalex.chesslogger.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.ui.textOnPrimary
import com.grosalex.chesslogger.viewmodels.ConfigViewModel

sealed class Screen(val route: String, @StringRes var resourceId: Int) {

    object NewGame : Screen("newGame", R.string.new_game)
    object SavedGames : Screen("savedGames", R.string.saved_game)
    object SavedGameDetail : Screen("gameDetail/{uid}", R.string.saved_game)

    companion object {
        val list: List<Screen> = listOf(NewGame, SavedGames)
    }
}

@ExperimentalMaterialApi
@Composable
fun MainDrawerContent(scaffoldState: ScaffoldState, navController: NavHostController) {
    Column() {
        Screen.list.forEach {
            DrawerItem(text = stringResource(id = it.resourceId)) {
                navController.navigate(it.route)
                scaffoldState.drawerState.close()
            }
        }
        TextButton(
            modifier = Modifier.padding(8.dp),
            onClick = {
                ConfigViewModel.switchDarkTheme()
            }) {
            val forceDarkTheme by ConfigViewModel.forceDarkTheme.observeAsState(false)
            Text(text = stringResource(id = R.string.force_dark_theme))
            Switch(
                checked = forceDarkTheme, onCheckedChange = {
                    ConfigViewModel.switchDarkTheme()
                }
            )
        }
    }
}

@Composable
fun DrawerItem(text: String, onClick: () -> Unit = {}) {
    Column {
        TextButton(modifier = Modifier.padding(8.dp), onClick = onClick) {
            Text(text = text, color = MaterialTheme.colors.onSurface)
        }
        VerticalSeparator()
    }
}