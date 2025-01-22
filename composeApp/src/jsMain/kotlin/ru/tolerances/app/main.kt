package ru.tolerances.app

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import org.jetbrains.skiko.wasm.onWasmReady
import ru.tolerances.app.components.RootComponentImpl

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val lifecycle = LifecycleRegistry()
    val stateKeeper = StateKeeperDispatcher()


    val rootComponent = RootComponentImpl(
        componentContext =
        DefaultComponentContext(
            lifecycle = lifecycle,
            stateKeeper = stateKeeper
        )
    )



    onWasmReady {
        CanvasBasedWindow(canvasElementId = "canvasBody") {
            RootApp(rootComponent)
        }
    }

}