package com.grosalex.chesslogger.models

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.grosalex.chesslogger.R

@JsonAdapter(Key.Adapter::class)
sealed class Key(val notationStringRes: Int, val type:String) {

    class KING : Key(R.string.king_notation, type = "king")
    class QUEEN : Key(R.string.queen_notation, type = "queen")
    class BISHOP : Key(R.string.bishop_notation, type = "bishop")
    class KNIGHT : Key(R.string.knight_notation, type = "knight")
    class ROOK : Key(R.string.rook_notation, type = "rook")

    class A : Key(R.string.A_notation, type = "a")
    class B : Key(R.string.B_notation, type = "b")
    class C : Key(R.string.C_notation, type = "c")
    class D : Key(R.string.D_notation, type = "d")
    class E : Key(R.string.E_notation, type = "e")
    class F : Key(R.string.F_notation, type = "f")
    class G : Key(R.string.G_notation, type = "g")
    class H : Key(R.string.H_notation, type = "h")

    class ONE : Key(R.string.one_notation, type = "1")
    class TWO : Key(R.string.two_notation, type = "2")
    class THREE : Key(R.string.three_notation, type = "3")
    class FOUR : Key(R.string.four_notation, type = "4")
    class FIVE : Key(R.string.five_notation, type = "5")
    class SIX : Key(R.string.six_notation, type = "6")
    class SEVEN : Key(R.string.seven_notation, type = "7")
    class EIGHT : Key(R.string.eight_notation, type = "8")

    class TAKE : Key(R.string.take_notation, type = "take")
    class TO_CHECK : Key(R.string.check_notation, type = "check")
    class CHECKMATE : Key(R.string.checkmate_notation, type = "checkmate")
    class OO : Key(R.string.small_castling_notation, type = "OO")
    class OOO : Key(R.string.large_castling_notation, type = "OOO")

    companion object {
        val pieces = listOf(KING(), QUEEN(), BISHOP(), KNIGHT(), ROOK())

        val letters = listOf(A(), B(), C(), D(), E(), F(), G(), H())

        val numbers = listOf(
            ONE(),
            TWO(),
            THREE(),
            FOUR(),
            FIVE(),
            SIX(),
            SEVEN(),
            EIGHT()
        )

        val specials = listOf(
            TAKE(),
            TO_CHECK(),
            CHECKMATE(),
            OO(),
            OOO()
        )
    }

    class Adapter(private val field: String = "type") : TypeAdapter<Key>() {
        override fun read(reader: JsonReader): Key? {
            val parser = JsonParser()
            val jsonObject = try {
                parser.parse(reader)?.asJsonObject ?: return null
            } catch (e: IllegalStateException) {
                throw Exception()
            }
            val type = try {
                jsonObject.get(field).asJsonPrimitive.asString
            } catch (e: IllegalStateException) {
                throw Exception()
            }
            return readObject(jsonObject, type)
        }

        override fun write(writer: JsonWriter, value: Key?) {
            Log.e("JSON ADAPTER WRITE", value.toString())
            when (value) {
                null -> writer.nullValue()
                else -> Gson().toJson(value, value.javaClass, writer)
            }
        }

        fun readObject(jsonObject: JsonObject, type: String): Key {
            Log.e("JSON ADAPTER READ", jsonObject.toString())
            return when (type) {
                "king" -> Gson().fromJson(jsonObject, KING::class.java)
                "queen" -> Gson().fromJson(jsonObject, QUEEN::class.java)
                "bishop" -> Gson().fromJson(jsonObject, BISHOP::class.java)
                "knight" -> Gson().fromJson(jsonObject, KNIGHT::class.java)
                "rook" -> Gson().fromJson(jsonObject, ROOK::class.java)
                "a" -> Gson().fromJson(jsonObject, A::class.java)
                "b" -> Gson().fromJson(jsonObject, B::class.java)
                "c" -> Gson().fromJson(jsonObject, C::class.java)
                "d" -> Gson().fromJson(jsonObject, D::class.java)
                "e" -> Gson().fromJson(jsonObject, E::class.java)
                "f" -> Gson().fromJson(jsonObject, F::class.java)
                "g" -> Gson().fromJson(jsonObject, G::class.java)
                "h" -> Gson().fromJson(jsonObject, H::class.java)
                "1" -> Gson().fromJson(jsonObject, ONE::class.java)
                "2" -> Gson().fromJson(jsonObject, TWO::class.java)
                "3" -> Gson().fromJson(jsonObject, THREE::class.java)
                "4" -> Gson().fromJson(jsonObject, FOUR::class.java)
                "5" -> Gson().fromJson(jsonObject, FIVE::class.java)
                "6" -> Gson().fromJson(jsonObject, SIX::class.java)
                "7" -> Gson().fromJson(jsonObject, SEVEN::class.java)
                "8" -> Gson().fromJson(jsonObject, EIGHT::class.java)
                "take" -> Gson().fromJson(jsonObject, TAKE::class.java)
                "check" -> Gson().fromJson(jsonObject, TO_CHECK::class.java)
                "checkmate" -> Gson().fromJson(jsonObject, CHECKMATE::class.java)
                "OO" -> Gson().fromJson(jsonObject, OO::class.java)
                "OOO" -> Gson().fromJson(jsonObject, OOO::class.java)
                else -> throw Exception()
            }
        }
    }

}