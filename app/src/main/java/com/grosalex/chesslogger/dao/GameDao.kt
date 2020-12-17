package com.grosalex.chesslogger.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grosalex.chesslogger.entities.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAll(): List<Game>

    @Insert
    fun addGame(game: Game)
}