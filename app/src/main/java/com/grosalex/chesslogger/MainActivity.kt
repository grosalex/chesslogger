package com.grosalex.chesslogger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.grosalex.chesslogger.components.MainScaffold
import com.grosalex.chesslogger.ui.ChessLoggerTheme
import com.grosalex.chesslogger.viewmodels.ConfigViewModel

class MainActivity : AppCompatActivity() {
    @ExperimentalLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme by ConfigViewModel.forceDarkTheme.observeAsState(false)
            ChessLoggerTheme(darkTheme = darkTheme) {
                MainScaffold()
            }
        }
    }
}
