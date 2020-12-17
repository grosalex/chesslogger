package com.grosalex.chesslogger

import android.app.Application
import com.grosalex.chesslogger.reducers.appStateReducer
import com.grosalex.chesslogger.states.AppState
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.rekotlin.Store
import org.rekotlin.StoreType

class ChessLoggerApplication : Application(), ChessKoin {

    val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()

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
                state = AppState()
            )
        }
    }

    companion object{
        lateinit var app: ChessLoggerApplication
    }
}