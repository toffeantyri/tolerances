package ru.tolerances.app.ui.cutting_speed_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.tolerances.app.components.cutting_speed_screen_component.ICuttingSpeedScreenComponent
import ru.tolerances.app.domain.models.CuttingCalcType
import ru.tolerances.app.ui.generals.InputTextField
import ru.tolerances.app.ui.generals.TitleText
import ru.tolerances.app.ui.theme.LightGray
import ru.tolerances.app.ui.theme.LiquidBlue
import ru.tolerances.app.ui.theme.MintGreen
import ru.tolerances.app.ui.theme.medium16TextStyle

@Composable
fun CuttingSpeedScreenView(component: ICuttingSpeedScreenComponent) {

    val uiModel = component.viewModel.uiModel.subscribeAsState()

    val toggleSelectedCuttingTypeState = remember(uiModel.value.selectedCuttingCalcType.value) {
        derivedStateOf { uiModel.value.selectedCuttingCalcType.value == CuttingCalcType.CalcV }
    }

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {

            TitleText(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    .padding(horizontal = 16.dp),
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

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 8.dp)
                    .border(
                        BorderStroke(1.dp, LightGray), RoundedCornerShape(22.dp)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                AnimatedVisibility(toggleSelectedCuttingTypeState.value) {
                    Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                }
                OutlinedIconToggleButton(
                    modifier = Modifier.fillMaxWidth(if (toggleSelectedCuttingTypeState.value) 1f else 0.5f)
                        .padding(horizontal = 4.dp).pointerInput(Unit) {
                            detectHorizontalDragGestures { _, dragAmount ->
                                when {
                                    dragAmount > 0 -> {
                                        if (!toggleSelectedCuttingTypeState.value) {
                                            component.viewModel.onToggleCuttingSpeed(true)
                                        }
                                    }// Свайп вправо
                                    dragAmount < 0 -> {
                                        if (toggleSelectedCuttingTypeState.value) {
                                            component.viewModel.onToggleCuttingSpeed(false)
                                        }
                                    } // Свайп влево
                                }
                            }
                        },
                    checked = toggleSelectedCuttingTypeState.value,
                    onCheckedChange = component.viewModel::onToggleCuttingSpeed,
                    colors = IconButtonDefaults.outlinedIconToggleButtonColors(
                        checkedContainerColor = LiquidBlue,
                        containerColor = MintGreen
                    ),
                    border = null
                ) {
                    Text(
                        text = uiModel.value.selectedCuttingCalcType.value.getTypeTitle(),
                        maxLines = 1
                    )
                }

                AnimatedVisibility(toggleSelectedCuttingTypeState.value.not()) {
                    Spacer(modifier = Modifier.fillMaxWidth(1f))
                }
            }

        }

    }

}