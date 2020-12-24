package com.grosalex.chesslogger.middleware

import com.grosalex.chesslogger.ChessLoggerApplication
import com.grosalex.chesslogger.actions.GameActions
import com.grosalex.chesslogger.entities.Game
import com.grosalex.chesslogger.states.AppState
import org.rekotlin.Middleware

val databaseMiddleware: Middleware<AppState> = { dispatch, getState ->
    { next ->
        { action ->
            when (action) {
                is GameActions.Save -> {
                    val currentGameState = getState()?.currentGameState!!
                    ChessLoggerApplication.app.addGame(
                        Game(
                            title = action.title,
                            whitePlayerName = currentGameState.players.first,
                            blackPlayerName = currentGameState.players.second,
                            whiteMoves =  currentGameState.lastMoves.map { it.first },
                            blackMoves = currentGameState.lastMoves.map { it.second }
                        )
                    )
                }
                is GameActions.Delete -> {
                    ChessLoggerApplication.app.deleteGame(action.game)
                }
            }
            next(action)
        }
    }
}
