package com.grosalex.chesslogger.entities

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.grosalex.chesslogger.models.Key

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val title:String,
    @ColumnInfo(name = "white_player_name") val whitePlayerName: String,
    @ColumnInfo(name = "black_player_name") val blackPlayerName: String,
    val moves: List<Pair<List<Key>?, List<Key>?>>
)

class Converters {
    @TypeConverter
    fun toList(value: String): List<Pair<List<Key>?, List<Key>?>> {
        val type = object : TypeToken<List<Pair<List<Key>?, List<Key>?>>>(){}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(value: List<Pair<List<Key>?, List<Key>?>>): String {
        return Gson().toJson(value)
    }
}