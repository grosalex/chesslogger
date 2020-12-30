package com.grosalex.chesslogger.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grosalex.chesslogger.entities.Game
import com.grosalex.chesslogger.models.Key
import com.grosalex.chesslogger.repositories.SavedGameRepository
import kotlinx.coroutines.launch

class NewGameViewModel(application: Application) : AndroidViewModel(application) {

    private val savedGamesRepository: SavedGameRepository = SavedGameRepository(application)

    val whitePlayer: MutableLiveData<String> = MutableLiveData<String>("")
    val blackPlayer: MutableLiveData<String> = MutableLiveData<String>("")
    val lastMoves: MutableLiveData<List<Pair<List<Key>?, List<Key>?>>> =
        MutableLiveData<List<Pair<List<Key>?, List<Key>?>>>(
            listOf(
                Pair(
                    null, null
                )
            )
        )
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
            val lastMove = lastMoves.value?.lastOrNull()
            if (lastMove?.first.isNullOrEmpty() && lastMove?.second.isNullOrEmpty()) {
                //remove last and second of the one before
                val newLastMoves = lastMoves.value?.toMutableList()
                newLastMoves?.removeLast()
                val last = newLastMoves?.removeLastOrNull()
                newLastMoves?.add(last?.copy(second = null) ?: Pair(null, null))
                this.lastMoves.value = newLastMoves
            } else if (!lastMove?.first.isNullOrEmpty() && lastMove?.second.isNullOrEmpty()) {
                // remove last first
                val newLastMoves = lastMoves.value?.toMutableList()
                val last = newLastMoves?.removeLast()
                newLastMoves?.add(last!!.copy(first = null))
                lastMoves.value = newLastMoves
            } else if (!lastMove?.first.isNullOrEmpty() && !lastMove?.second.isNullOrEmpty()) {
                val newLastMoves = lastMoves.value?.toMutableList()
                val last = newLastMoves?.removeLast()
                newLastMoves?.add(last!!.copy(second = null))
                lastMoves.value = newLastMoves
            }
        } else {
            currentMove.value = emptyList()
        }
    }

    fun addMove() {
        val newLastMoves = lastMoves.value?.toMutableList()
        val lastMove = newLastMoves?.lastOrNull()
        if (lastMove != null && lastMove.first == null) {
            newLastMoves.removeLast()
            newLastMoves.add(Pair(currentMove.value, null))
            lastMoves.value = newLastMoves
        } else if (lastMove != null && lastMove.second == null) {
            val previous = newLastMoves.removeLast()
            newLastMoves.addAll(
                listOf(
                    Pair(previous.first, currentMove.value),
                    Pair(null, null)
                )
            )
            lastMoves.value = newLastMoves
        } else {
            newLastMoves?.add(Pair(currentMove.value, null))
            lastMoves.value = newLastMoves
        }
        currentMove.value = emptyList()
    }

    fun save(text: String) {
        viewModelScope.launch {
            savedGamesRepository.addGame(Game(
                title = text,
                whitePlayerName = whitePlayer.value.orEmpty(),
                blackPlayerName = blackPlayer.value.orEmpty(),
                whiteMoves = lastMoves.value?.map { it.first },
                blackMoves = lastMoves.value?.map { it.second }
            ))
        }
    }
}