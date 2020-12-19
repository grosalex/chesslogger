package com.grosalex.chesslogger.middleware

import com.grosalex.chesslogger.ChessLoggerApplication
import com.grosalex.chesslogger.actions.CurrentGameActions
import com.grosalex.chesslogger.entities.Game
import com.grosalex.chesslogger.states.AppState
import org.rekotlin.Middleware

val databaseMiddleware: Middleware<AppState> = { dispatch, getState ->
    { next ->
        { action ->
            when (action) {
                is CurrentGameActions.Save -> {
                    val currentGameState = getState()?.currentGameState!!
                    ChessLoggerApplication.app.addGame(
                        Game(
                            title = action.title,
                            whitePlayerName = currentGameState.players.first,
                            blackPlayerName = currentGameState.players.second,
                            moves = currentGameState.lastMoves
                        )
                    )
                }
            }
            next(action)
        }
    }
}
