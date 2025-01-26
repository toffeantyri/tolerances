package ru.tolerances.app.ui.tolerances_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.tolerances.app.components.tolerances_screen_component.dialogs.ITolerancesResultDialogComponent

@Composable
fun ToleranceResultDialogView(component: ITolerancesResultDialogComponent) {

    Dialog(onDismissRequest = component::onDismissAction) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            backgroundColor = Color.White
        ) {
            Text("Result")
        }
    }
}