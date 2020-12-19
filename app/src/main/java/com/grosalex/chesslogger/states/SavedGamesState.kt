package com.grosalex.chesslogger.states

import com.grosalex.chesslogger.entities.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SavedGamesState(val savedGames: Flow<List<Game>> = emptyFlow())