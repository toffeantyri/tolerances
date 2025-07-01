import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import ru.tolerances.app.RootApp
import ru.tolerances.app.components.RootComponentImpl
import ru.tolerances.app.di.initKoin
import ru.tolerances.app.runOnUiThread

fun main() {

    initKoin()

    val lifecycle = LifecycleRegistry()
    val stateKeeper = StateKeeperDispatcher()

    val rootComponent = runOnUiThread {
        RootComponentImpl(
            componentContext =
            DefaultComponentContext(
                lifecycle,
                stateKeeper
            )
        )
    }

    application {
        val windowState = rememberWindowState(width = 500.dp, height = 700.dp)

        Window(
            state = windowState,
            onCloseRequest = ::exitApplication,
            title = "TolerancesTableData",
        ) {
            RootApp(component = rootComponent)
        }
    }
}