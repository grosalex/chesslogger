package com.grosalex.chesslogger

import com.grosalex.chesslogger.states.AppState
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.rekotlin.StoreType

interface ChessKoin : KoinComponent {

    val store: StoreType<AppState>
        get() = get()
}