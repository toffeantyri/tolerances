package ru.tolerances.app.ui.cutting_speed_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.tolerances.app.components.cutting_speed_screen_component.ICuttingSpeedScreenComponent
import ru.tolerances.app.ui.generals.InputTextField
import ru.tolerances.app.ui.generals.TitleText
import ru.tolerances.app.ui.theme.medium16TextStyle

@Composable
fun CuttingSpeedScreenView(component: ICuttingSpeedScreenComponent) {

    val uiModel = component.viewModel.uiModel.subscribeAsState()

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {

            TitleText(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = "Введите обрабатываемый диаметр или диаметр вращающегося инструмента",
                textAlign = TextAlign.Center,
                textStyle = medium16TextStyle()
            )

            InputTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                valueState = uiModel.value.diameterField,
                onValueChange = { component.viewModel.onDiameterChanged(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text = "Диаметр") },
            )


        }

    }

}