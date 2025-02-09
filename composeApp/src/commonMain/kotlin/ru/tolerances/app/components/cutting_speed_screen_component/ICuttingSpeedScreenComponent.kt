package ru.tolerances.app.components.cutting_speed_screen_component

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import ru.tolerances.app.components.cutting_speed_screen_component.dialog.ICuttingTypeCalcResultComponent

interface ICuttingSpeedScreenComponent {

    val viewModel: CuttingSpeedViewModel

    val dialog: Value<ChildSlot<*, ICuttingSpeedScreenComponent.DialogChild>>

    @Stable
    sealed interface DialogChild {
        data class CalculateResult(val component: ICuttingTypeCalcResultComponent) : DialogChild
    }
}