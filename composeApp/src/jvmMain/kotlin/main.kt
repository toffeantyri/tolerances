import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
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
        Window(
            onCloseRequest = ::exitApplication,
            title = "TolerancesTableData",
        ) {
            RootApp(rootComponent)
        }
    }
}