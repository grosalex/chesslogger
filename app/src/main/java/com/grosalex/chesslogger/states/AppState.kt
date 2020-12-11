package com.grosalex.chesslogger.states

import com.grosalex.chesslogger.reducers.appStateReducer
import org.rekotlin.StateType
import org.rekotlin.Store

data class AppState(
    val currentGameState: CurrentGameState = CurrentGameState()
) : StateType


fun fakeStore(): Store<AppState> = Store(
    ::appStateReducer, AppState(
        currentGameState = CurrentGameState(listOf(Pair("e5", "e6"), Pair("d4", "d7")))
    )
)
