import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ru.tolerances.app.RootApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TolerancesTableData",
    ) {
        RootApp()
    }
}