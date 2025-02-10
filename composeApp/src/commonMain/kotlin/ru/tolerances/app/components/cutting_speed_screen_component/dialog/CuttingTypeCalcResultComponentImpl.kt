package ru.tolerances.app.components.cutting_speed_screen_component.dialog

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class CuttingTypeCalcResultComponentImpl(
    componentContext: ComponentContext,
    private val resultStringValue: String,
    private val onDismiss: () -> Unit
) : ICuttingTypeCalcResultComponent, ComponentContext by componentContext {
    override fun onDismissAction() {
        onDismiss()
    }

    override val resultValue: Value<String> = MutableValue(resultStringValue)

}