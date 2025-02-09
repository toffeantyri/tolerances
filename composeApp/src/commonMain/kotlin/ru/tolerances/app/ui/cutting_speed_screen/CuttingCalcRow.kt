package ru.tolerances.app.ui.cutting_speed_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.tolerances.app.components.cutting_speed_screen_component.CuttingSpeedViewModel
import ru.tolerances.app.domain.models.CuttingCalcType
import ru.tolerances.app.ui.generals.InputTextField

@Composable
fun CuttingCalcRow(
    selectedCuttingType: State<CuttingCalcType>,
    uiModel: State<CuttingSpeedViewModel.UiModel>,
    onInputV: (String) -> Unit,
    onInputN: (String) -> Unit
) {


    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(selectedCuttingType.value == CuttingCalcType.CalcV) {
            Spacer(modifier = Modifier.fillMaxWidth(0.5f))
        }
        InputTextField(
            modifier = Modifier.fillMaxWidth(if (selectedCuttingType.value == CuttingCalcType.CalcV) 1f else 0.5f),
            valueState = if (selectedCuttingType.value == CuttingCalcType.CalcV) uiModel.value.inputFieldV else uiModel.value.inputFieldN,
            onValueChange = if (selectedCuttingType.value == CuttingCalcType.CalcV) onInputV else onInputN,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            label = {
                Text(
                    text = "Введите " + selectedCuttingType.value.getShortName(),
                    maxLines = 1
                )
            },
            error = if (selectedCuttingType.value == CuttingCalcType.CalcV) uiModel.value.inputFieldErrorV.value
            else uiModel.value.inputFieldErrorN.value
        )
        AnimatedVisibility(selectedCuttingType.value == CuttingCalcType.CalcN) {
            Spacer(modifier = Modifier.fillMaxWidth(1f))
        }
    }
}

