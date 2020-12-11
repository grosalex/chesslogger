package com.grosalex.chesslogger.states

import org.rekotlin.StateType

data class CurrentGameState(val lastMoves: List<Pair<String, String?>> = emptyList()) : StateType