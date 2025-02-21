package ru.tolerances.app.components.cutting_speed_screen_component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import ru.tolerances.app.domain.models.CuttingCalcType
import ru.tolerances.app.utils.EMPTY

class CuttingSpeedViewModel : InstanceKeeper.Instance {


    val uiModel: Value<UiModel> = MutableValue(UiModel())

    fun onDiameterChanged(value: String) {
        with(uiModel.value) {
            diameterField.value = value
            diameterFieldError.validateAsFloat(value, {})
        }
    }

    fun onToggleCuttingCalcType(state: Boolean) {
        println("TOOGLE NEW STATE $state")
        with(uiModel.value) {
            clearCuttingFields()
            selectedCuttingCalcType.value =
                if (state) CuttingCalcType.CalcV(0f) else CuttingCalcType.CalcN(0f)
        }
    }

    private fun clearCuttingFields() {
        with(uiModel.value) {
            inputFieldN.value = EMPTY
            inputFieldV.value = EMPTY
            inputFieldErrorN.value = null
            inputFieldErrorV.value = null
        }
    }

    fun onInputV(value: String) {
        with(uiModel.value) {
            inputFieldV.value = value
            inputFieldErrorV.validateAsFloat(value) { float ->
                selectedCuttingCalcType.value = CuttingCalcType.CalcV(float)
            }

        }
    }

    fun onInputN(value: String) {
        with(uiModel.value) {
            inputFieldN.value = value
            inputFieldErrorN.validateAsFloat(value) { float ->
                selectedCuttingCalcType.value = CuttingCalcType.CalcN(float)
            }
        }
    }

    data class UiModel(
        val diameterField: MutableState<String> = mutableStateOf(EMPTY),
        val diameterFieldError: MutableState<String?> = mutableStateOf(null),
        val selectedCuttingCalcType: MutableState<CuttingCalcType> = mutableStateOf(
            CuttingCalcType.CalcN(0f)
        ),
        val inputFieldV: MutableState<String> = mutableStateOf(EMPTY),
        val inputFieldErrorV: MutableState<String?> = mutableStateOf(null),
        val inputFieldN: MutableState<String> = mutableStateOf(EMPTY),
        val inputFieldErrorN: MutableState<String?> = mutableStateOf(null)
    )

    private fun MutableState<String?>.validateAsFloat(value: String, onSuccess: (Float) -> Unit) {
        value.toFloatOrNull()?.let {
            this.value = null
            if (it <= 0) {
                this.value = "Значение должно быть больше 0"
            }
            onSuccess(it)
        } ?: run {
            this.value = "Введите число"
        }
    }

}