package com.grosalex.chesslogger.reducers

import androidx.compose.runtime.setValue
import com.grosalex.chesslogger.actions.CurrentGameActions
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.CurrentGameState
import org.rekotlin.Action

fun currentGameReducer(action: Action, state: AppState): CurrentGameState {

    var state = state.currentGameState

    when (action) {
        is CurrentGameActions.AddMove -> {
            val lastMove = state.lastMoves.lastOrNull()
            if (lastMove != null && lastMove.second == null) {
                val oldMoves = state.lastMoves.toMutableList()
                val previous = oldMoves.removeLast()
                state = state.copy(lastMoves = oldMoves + Pair(previous.first, action.move))
            } else {
                state = state.copy(lastMoves = state.lastMoves + Pair(action.move, null))
            }
        }
    }

    return state
}