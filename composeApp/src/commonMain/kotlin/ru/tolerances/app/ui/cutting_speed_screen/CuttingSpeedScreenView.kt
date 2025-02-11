package ru.tolerances.app.ui.cutting_speed_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.tolerances.app.components.cutting_speed_screen_component.ICuttingSpeedScreenComponent
import ru.tolerances.app.ui.generals.InputTextField
import ru.tolerances.app.ui.generals.MyFilledButton
import ru.tolerances.app.ui.generals.TitleText
import ru.tolerances.app.ui.theme.medium14TextStyle
import ru.tolerances.app.ui.theme.medium16TextStyle

@Composable
fun CuttingSpeedScreenView(component: ICuttingSpeedScreenComponent) {

    val uiModel = component.viewModel.uiModel.subscribeAsState()

    val dialogSlot = component.dialog.subscribeAsState()

    dialogSlot.value.child?.let { child ->
        when (val item = child.instance) {
            is ICuttingSpeedScreenComponent.DialogChild.CalculateResult -> CuttingResultDialogView(
                item.component
            )
        }
    }

    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                item {
                    TitleText(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                            .padding(horizontal = 16.dp),
                        text = "Введите обрабатываемый диаметр или диаметр вращающегося инструмента",
                        textAlign = TextAlign.Center,
                        textStyle = medium16TextStyle()
                    )
                }

                item {
                    InputTextField(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        valueState = uiModel.value.diameterField,
                        onValueChange = { component.viewModel.onDiameterChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text(text = "Введите диаметр") },
                        error = uiModel.value.diameterFieldError.value
                    )
                }

                item {

                    TitleText(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                            .padding(horizontal = 16.dp),
                        text = "Что хотим рассчитать?",
                        textAlign = TextAlign.Center,
                        textStyle = medium14TextStyle()
                    )

                    SwiperView(
                        selectedCuttingType = uiModel.value.selectedCuttingCalcType,
                        onToggleCuttingType = component.viewModel::onToggleCuttingCalcType
                    )

                    CuttingCalcRow(
                        selectedCuttingType = uiModel.value.selectedCuttingCalcType,
                        uiModel = uiModel,
                        onInputN = component.viewModel::onInputN,
                        onInputV = component.viewModel::onInputV
                    )
                }
            }

            MyFilledButton(
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                    .padding(horizontal = 16.dp).padding(8.dp),
                text = "Рассчитать",
                onClick = component::showCalcResultDialog,
                enabled = uiModel.value.inputFieldN.value.isNotBlank() || uiModel.value.inputFieldV.value.isNotBlank()
            )
        }
    }

}