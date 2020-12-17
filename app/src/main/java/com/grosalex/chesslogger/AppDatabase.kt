package com.grosalex.chesslogger

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grosalex.chesslogger.dao.GameDao
import com.grosalex.chesslogger.entities.Converters
import com.grosalex.chesslogger.entities.Game

@Database(entities = arrayOf(Game::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "game_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}