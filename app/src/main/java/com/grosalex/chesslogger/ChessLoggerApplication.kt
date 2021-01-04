package com.grosalex.chesslogger

import android.app.Application

class ChessLoggerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        lateinit var app: ChessLoggerApplication
    }
}