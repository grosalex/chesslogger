package com.grosalex.chesslogger.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.grosalex.chesslogger.R
import com.grosalex.chesslogger.ui.textOnPrimary

sealed class Screen(val route: String, @StringRes var resourceId: Int) {

    object NewGame : Screen("newGame", R.string.new_game)
    object SavedGames : Screen("savedGames", R.string.saved_game)

    companion object {
        val list: List<Screen> = listOf(NewGame, SavedGames)
    }
}

@Composable
fun MainDrawerContent(scaffoldState: ScaffoldState, navController: NavHostController) {
    Column() {
        Screen.list.forEach {
            DrawerItem(text = stringResource(id = it.resourceId)) {
                navController.navigate(it.route)
                scaffoldState.drawerState.close()
            }
        }
    }
}

@Composable
fun DrawerItem(text: String, onClick: () -> Unit = {}) {
    TextButton(modifier = Modifier.padding(8.dp), onClick = onClick) {
        Text(text = text, color = textOnPrimary)
    }
}