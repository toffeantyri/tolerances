package ru.tolerances.app.components.tolerances_screen_component.dialogs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class TolerancesResultDialogComponentImpl(
    componentContext: ComponentContext,
    private val result: String,
    private val onDismiss: () -> Unit
) : ITolerancesResultDialogComponent, ComponentContext by componentContext {

    override val resultValue: Value<String> = MutableValue(result)

    override fun onDismissAction() {
        onDismiss()
    }

}