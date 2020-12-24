package com.grosalex.chesslogger

import android.app.Application
import com.grosalex.chesslogger.entities.Game
import com.grosalex.chesslogger.middleware.databaseMiddleware
import com.grosalex.chesslogger.reducers.appStateReducer
import com.grosalex.chesslogger.states.AppState
import com.grosalex.chesslogger.states.SavedGamesState
import kotlinx.coroutines.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.rekotlin.Store
import org.rekotlin.StoreType
import kotlin.coroutines.CoroutineContext

class ChessLoggerApplication : Application(), ChessKoin, CoroutineScope {

    private lateinit var job: Job
    val database by lazy { AppDatabase.getDatabase(this) }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate() {
        super.onCreate()

        job = Job()

        startKoin {
            androidContext(this@ChessLoggerApplication)
            modules(myModules())
        }
        app = this
    }

    private fun myModules() = module {
        single<StoreType<AppState>> {
            Store(
                reducer = ::appStateReducer,
                state = loadAppState(),
                middleware = listOf(databaseMiddleware)
            )
        }
    }

    private fun loadAppState(): AppState {
        var state = AppState()
        state = state.copy(savedGamesState = SavedGamesState(database.gameDao().getAll()))
        return state
    }

    fun addGame(game: Game) {
        launch {
            database.gameDao().addGame(game)
        }
    }

    fun deleteGame(game: Game) {
        launch {
            database.gameDao().deleteGame(game)
        }
    }

    companion object {
        lateinit var app: ChessLoggerApplication
    }
}