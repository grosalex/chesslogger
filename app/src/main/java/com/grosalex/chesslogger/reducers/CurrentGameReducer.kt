package com.grosalex.chesslogger.reducers

import com.grosalex.chesslogger.actions.GameActions
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.CurrentGameState
import org.rekotlin.Action

fun currentGameReducer(action: Action, state: AppState): CurrentGameState {

    var state = state.currentGameState

    when (action) {
        is GameActions.SetWhitePlayerName -> {
            state = state.copy(players = state.players.copy(first = action.name))
        }
        is GameActions.SetBlackPlayerName -> {
            state = state.copy(players = state.players.copy(second = action.name))
        }
        is GameActions.AddMove -> {
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

        is GameActions.KeyPressed -> {
            state = state.copy(currentMove = state.currentMove + action.key)
        }

        is GameActions.Erased -> {
            val oldCurrentMove = state.currentMove.toMutableList()
            oldCurrentMove.removeLastOrNull()
            state = state.copy(currentMove = oldCurrentMove)
        }
        is GameActions.RemoveLastMove -> {
            if (state.currentMove.isEmpty()) {
                val lastMove = state.lastMoves.lastOrNull()
                if (lastMove?.first.isNullOrEmpty() && lastMove?.second.isNullOrEmpty()) {
                    //remove last and second of the one before
                    val lastMoves = state.lastMoves.toMutableList()
                    lastMoves.removeLast()
                    val last = lastMoves.removeLastOrNull()
                    lastMoves.add(last?.copy(second = null) ?: Pair(null, null))
                    state = state.copy(lastMoves = lastMoves)
                } else if (!lastMove?.first.isNullOrEmpty() && lastMove?.second.isNullOrEmpty()) {
                    // remove last first
                    val lastMoves = state.lastMoves.toMutableList()
                    val last = lastMoves.removeLast()
                    lastMoves.add(last.copy(first = null))
                    state = state.copy(lastMoves = lastMoves)
                } else if (!lastMove?.first.isNullOrEmpty() && !lastMove?.second.isNullOrEmpty()) {
                    val lastMoves = state.lastMoves.toMutableList()
                    val last = lastMoves.removeLast()
                    lastMoves.add(last.copy(second = null))
                    state = state.copy(lastMoves = lastMoves)
                }
            } else {
                state = state.copy(currentMove = emptyList())
            }
        }
    }

    return state
}