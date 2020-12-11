package com.grosalex.chesslogger.states

import com.grosalex.chesslogger.models.Key
import org.rekotlin.StateType

data class CurrentGameState(
    val lastMoves: List<Pair<List<Key>?, List<Key>?>> = listOf(Pair(null, null)),
    val currentMove: List<Key> = listOf()
) : StateType