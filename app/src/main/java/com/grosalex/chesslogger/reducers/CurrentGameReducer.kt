package com.grosalex.chesslogger.reducers

import com.grosalex.chesslogger.actions.CurrentGameActions
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.CurrentGameState
import org.rekotlin.Action

fun currentGameReducer(action: Action, state: AppState): CurrentGameState {

    var state = state.currentGameState

    when (action) {
        is CurrentGameActions.AddMove -> {
            val lastMove = state.lastMoves.lastOrNull()
            if (lastMove != null && lastMove.first == null) {
                val oldMoves = state.lastMoves.toMutableList()
                oldMoves.removeLast()
                state = state.copy(lastMoves = oldMoves + Pair(action.move, null))
            } else if (lastMove != null && lastMove.second == null) {
                val oldMoves = state.lastMoves.toMutableList()
                val previous = oldMoves.removeLast()
                state = state.copy(
                    lastMoves = oldMoves + Pair(previous.first, action.move) + (Pair(
                        null,
                        null
                    ))
                )
            } else {
                state = state.copy(lastMoves = state.lastMoves + Pair(action.move, null))
            }
        }

        is CurrentGameActions.KeyPressed -> {
            state = state.copy(currentMove = state.currentMove + action.key)
        }
    }

    return state
}