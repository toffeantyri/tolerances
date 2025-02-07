package ru.tolerances.app.components.cutting_speed_screen_component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import ru.tolerances.app.utils.EMPTY

class CuttingSpeedViewModel : InstanceKeeper.Instance {


    val uiModel: Value<UiModel> = MutableValue(UiModel())

    fun onDiameterChanged(value: String) {
        uiModel.value.diameterField.value = value
    }


    data class UiModel(
        val diameterField: MutableState<String> = mutableStateOf(EMPTY)
    )

}