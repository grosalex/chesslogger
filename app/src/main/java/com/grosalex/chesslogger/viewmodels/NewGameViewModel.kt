package com.grosalex.chesslogger.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.grosalex.chesslogger.models.Key

class NewGameViewModel(application: Application) : AndroidViewModel(application) {

    val whitePlayer: MutableLiveData<String> = MutableLiveData<String>("")
    val blackPlayer: MutableLiveData<String> = MutableLiveData<String>("")
    val lastMoves: MutableList<Pair<List<Key>?, List<Key>?>> = mutableListOf(Pair(null, null))
    val currentMove: MutableLiveData<List<Key>> = MutableLiveData<List<Key>>(
        emptyList()
    )

    fun onWhitePlayerChanges(newValue: String) {
        whitePlayer.value = newValue
    }

    fun onBlackPlayerChanges(newValue: String) {
        blackPlayer.value = newValue
    }

    fun keyPressed(key: Key) {
        currentMove.value = currentMove.value?.toMutableList()?.apply { this.add(key) }
    }

    fun erased() {
        currentMove.value = emptyList()
    }

    fun removeLastMove() {
        if (currentMove.value.isNullOrEmpty()) {
            val lastMove = lastMoves.lastOrNull()
            if (lastMove?.first.isNullOrEmpty() && lastMove?.second.isNullOrEmpty()) {
                //remove last and second of the one before
                lastMoves.removeLast()
                val last = lastMoves.removeLastOrNull()
                lastMoves.add(last?.copy(second = null) ?: Pair(null, null))
            } else if (!lastMove?.first.isNullOrEmpty() && lastMove?.second.isNullOrEmpty()) {
                // remove last first
                val last = lastMoves.removeLast()
                lastMoves.add(last.copy(first = null))
            } else if (!lastMove?.first.isNullOrEmpty() && !lastMove?.second.isNullOrEmpty()) {
                val last = lastMoves.removeLast()
                lastMoves.add(last.copy(second = null))
            }
        } else {
            currentMove.value = emptyList()
        }
    }

    fun addMove() {
        val lastMove = lastMoves.lastOrNull()
        if (lastMove != null && lastMove.first == null) {
            lastMoves.removeLast()
            lastMoves.add(Pair(currentMove.value, null))
        } else if (lastMove != null && lastMove.second == null) {
            val previous = lastMoves.removeLast()
            lastMoves.addAll(
                listOf(
                    Pair(previous.first, currentMove.value),
                    Pair(null, null)
                )
            )
        } else {
            lastMoves.add(Pair(currentMove.value, null))
        }
        currentMove.value = emptyList()
    }

    fun save(text: String) {

    }
}