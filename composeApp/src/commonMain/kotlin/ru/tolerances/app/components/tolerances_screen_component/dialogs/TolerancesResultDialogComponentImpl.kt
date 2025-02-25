package ru.tolerances.app.components.tolerances_screen_component.dialogs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.tolerances.app.read_csv_repository.ICsvReader

class TolerancesResultDialogComponentImpl(
    componentContext: ComponentContext,
    private val result: ICsvReader.UnitData,
    private val userInputedSize: Float,
    private val onDismiss: () -> Unit
) : ITolerancesResultDialogComponent, ComponentContext by componentContext {

    override val resultValue: Value<ICsvReader.UnitData> = MutableValue(result)
    override val inputedSize: Value<Float> = MutableValue(userInputedSize)

    override fun onDismissAction() {
        onDismiss()
    }

}