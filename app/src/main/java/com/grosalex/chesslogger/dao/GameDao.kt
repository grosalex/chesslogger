package com.grosalex.chesslogger.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grosalex.chesslogger.entities.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAll(): Flow<List<Game>>

    @Insert
    suspend fun addGame(game: Game)
}