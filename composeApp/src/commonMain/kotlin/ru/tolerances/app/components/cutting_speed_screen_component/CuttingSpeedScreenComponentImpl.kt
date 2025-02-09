package ru.tolerances.app.components.cutting_speed_screen_component

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.navigate
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.serialization.Serializable
import ru.tolerances.app.components.cutting_speed_screen_component.dialog.CuttingTypeCalcResultComponentImpl

class CuttingSpeedScreenComponentImpl(
    componentContext: ComponentContext
) : ICuttingSpeedScreenComponent, ComponentContext by componentContext {

    override val viewModel: CuttingSpeedViewModel = instanceKeeper.getOrCreate {
        CuttingSpeedViewModel()
    }

    private val slotNavigation = SlotNavigation<DialogConfig>()


    override val dialog: Value<ChildSlot<*, ICuttingSpeedScreenComponent.DialogChild>> = childSlot(
        source = slotNavigation,
        serializer = DialogConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChildDialog,
    )


    @Stable
    private fun createChildDialog(
        config: DialogConfig,
        childComponentContext: ComponentContext
    ): ICuttingSpeedScreenComponent.DialogChild = when (config) {
        is DialogConfig.CuttingCalcResultDialogConfig -> ICuttingSpeedScreenComponent.DialogChild.CalculateResult(
            CuttingTypeCalcResultComponentImpl(
                componentContext = childComponentContext,
                onDismiss = { slotNavigation.navigate { null } })
        )
    }


    @Serializable
    private sealed class DialogConfig {
        @Serializable
        data object CuttingCalcResultDialogConfig : DialogConfig()

    }
}