package com.grosalex.chesslogger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.ui.platform.setContent
import com.grosalex.chesslogger.components.MainScaffold
import com.grosalex.chesslogger.ui.ChessLoggerTheme

class MainActivity : AppCompatActivity(), ChessKoin {
    @ExperimentalLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChessLoggerTheme() {
                MainScaffold(store)
            }
        }
    }
}
