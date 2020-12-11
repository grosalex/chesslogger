package com.grosalex.chesslogger.states

import org.rekotlin.StateType

data class CurrentGameState(val lastMoves: List<Pair<String?, String?>> = listOf(Pair(null, null))) : StateType