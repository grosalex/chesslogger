package com.grosalex.chesslogger.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grosalex.chesslogger.entities.Game
import com.grosalex.chesslogger.repositories.SavedGameRepository
import kotlinx.coroutines.launch

class SavedGamesViewModel(application: Application) : AndroidViewModel(application) {

    private val savedGamesRepository: SavedGameRepository = SavedGameRepository(application)
    private val allSavedGame = savedGamesRepository.getAllSavedGames()

    fun getAllGames() = allSavedGame

    fun addGame(game: Game){
        viewModelScope.launch {
            savedGamesRepository.addGame(game = game)
        }
    }

    fun deleteGame(game: Game){
        viewModelScope.launch {
            savedGamesRepository.deleteGame(game =game)
        }
    }
}