package com.grosalex.chesslogger.states

import com.grosalex.chesslogger.middleware.databaseMiddleware
import com.grosalex.chesslogger.reducers.appStateReducer
import org.rekotlin.StateType
import org.rekotlin.Store

data class AppState(
    val currentGameState: CurrentGameState = CurrentGameState()
) : StateType


fun fakeStore(): Store<AppState> = Store(
    reducer = ::appStateReducer,
    state = AppState(
        currentGameState = CurrentGameState()
    ),
    middleware = listOf(databaseMiddleware)
)
