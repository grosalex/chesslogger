package com.grosalex.chesslogger.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.grosalex.chesslogger.models.Key

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val title: String,
    @ColumnInfo(name = "white_player_name") val whitePlayerName: String,
    @ColumnInfo(name = "black_player_name") val blackPlayerName: String,
    val whiteMoves: List<List<Key>?>?,
    val blackMoves: List<List<Key>?>?
)

class Converters {
    @TypeConverter
    fun toList(value: String): List<List<Key>?>? {
        val type = object : TypeToken<List<List<Key>>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(value: List<List<Key>?>?): String {
        return Gson().toJson(value)
    }
}

fun Game.moves(): List<Pair<List<Key>?, List<Key>?>> {
    val list = mutableListOf<Pair<List<Key>?, List<Key>?>>()
    whiteMoves?.forEachIndexed { index, key ->
        list.add(Pair(key, blackMoves?.get(index)))
    }
    return list
}