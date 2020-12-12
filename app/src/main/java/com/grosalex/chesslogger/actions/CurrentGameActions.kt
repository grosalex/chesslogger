package com.grosalex.chesslogger.actions

import com.grosalex.chesslogger.models.Key
import org.rekotlin.Action

class CurrentGameActions {
    class AddMove : Action
    class RemoveLastMove : Action
    data class KeyPressed(val key: Key) : Action
    class Erased : Action
}