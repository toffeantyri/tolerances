package ru.tolerances.app

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {



    onWasmReady {
        CanvasBasedWindow(canvasElementId = "canvasBody") {
            RootApp()
        }
    }

}