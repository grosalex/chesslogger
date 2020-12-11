package com.grosalex.chesslogger.actions

import org.rekotlin.Action

class CurrentGameActions {
    data class AddMove(val move: String) : Action
}