import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ru.tolerances.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TolerancesTableData",
    ) {
        App()
    }
}