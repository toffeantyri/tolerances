package ru.tolerances.app.components.tolerances_screen_component

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import ru.tolerances.app.components.tolerances_screen_component.dialogs.ITolerancesResultDialogComponent

interface ITolerancesScreenComponent {

    val viewModel: TolerancesViewModel

    val dialog: Value<ChildSlot<*, ITolerancesScreenComponent.DialogChild>>

    fun showDialogToleranceResult(rangeIndex: Int, toleranceIndex: Int)

    @Stable
    sealed interface DialogChild {
        data class ToleranceResult(val component: ITolerancesResultDialogComponent) : DialogChild
    }

}