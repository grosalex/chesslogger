package com.grosalex.chesslogger.actions

import com.grosalex.chesslogger.models.Key
import org.rekotlin.Action

class CurrentGameActions {
    data class AddMove(val move: String) : Action
    data class KeyPressed(val key: Key) : Action
}