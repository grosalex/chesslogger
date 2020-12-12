package com.grosalex.chesslogger.reducers

import android.util.Log
import com.grosalex.chesslogger.actions.CurrentGameActions
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.CurrentGameState
import org.rekotlin.Action

fun currentGameReducer(action: Action, state: AppState): CurrentGameState {

    var state = state.currentGameState

    when (action) {
        is CurrentGameActions.AddMove -> {
            val currentMove = state.currentMove
            val lastMove = state.lastMoves.lastOrNull()
            if (lastMove != null && lastMove.first == null) {
                val oldMoves = state.lastMoves.toMutableList()
                oldMoves.removeLast()
                state = state.copy(lastMoves = oldMoves + Pair(currentMove, null))
            } else if (lastMove != null && lastMove.second == null) {
                val oldMoves = state.lastMoves.toMutableList()
                val previous = oldMoves.removeLast()
                state = state.copy(
                    lastMoves = oldMoves + Pair(previous.first, currentMove) + (Pair(
                        null,
                        null
                    ))
                )
            } else {
                state = state.copy(lastMoves = state.lastMoves + Pair(currentMove, null))
            }
            state = state.copy(currentMove = emptyList())
        }

        is CurrentGameActions.KeyPressed -> {
            state = state.copy(currentMove = state.currentMove + action.key)
        }

        is CurrentGameActions.Erased -> {
            val oldCurrentMove = state.currentMove.toMutableList()
            oldCurrentMove.removeLastOrNull()
            state = state.copy(currentMove = oldCurrentMove)
        }
        is CurrentGameActions.RemoveLastMove -> {
            if (state.currentMove.isEmpty()) {
                TODO("Remove last move is not implemented")
            } else {
                state = state.copy(currentMove = emptyList())
            }
        }
    }

    return state
}