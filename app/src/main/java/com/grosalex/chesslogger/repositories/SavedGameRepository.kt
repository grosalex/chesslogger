package com.grosalex.chesslogger.repositories

import android.content.Context
import com.grosalex.chesslogger.AppDatabase
import com.grosalex.chesslogger.dao.GameDao
import com.grosalex.chesslogger.entities.Game
import kotlinx.coroutines.flow.Flow

class SavedGameRepository(context: Context) {
    private val gameDao: GameDao
    private val allSavedGame: Flow<List<Game>>

    init {
        val database = AppDatabase.getDatabase(context)
        gameDao = database.gameDao()
        allSavedGame = gameDao.getAll()
    }

    fun getAllSavedGames(): Flow<List<Game>> = allSavedGame

    suspend fun addGame(game: Game) {
        gameDao.addGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }
}