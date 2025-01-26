package ru.tolerances.app.components.tolerances_screen_component

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.navigate
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.tolerances.app.components.tolerances_screen_component.dialogs.TolerancesResultDialogComponentImpl
import ru.tolerances.app.utils.componentCoroutineScope

class TolerancesScreenComponentImpl(componentContext: ComponentContext) :
    ITolerancesScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val coroutineScope = componentContext.componentCoroutineScope()

    override val viewModel = componentContext.instanceKeeper.getOrCreate {
        TolerancesViewModel(viewModelScope = coroutineScope, csvReader = get())
    }

    private val slotNavigation = SlotNavigation<DialogConfig>()


    override val dialog: Value<ChildSlot<*, ITolerancesScreenComponent.DialogChild>> = childSlot(
        source = slotNavigation,
        serializer = DialogConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChildDialog,
    )

    override fun showDialogToleranceResult() {
        slotNavigation.navigate {
            DialogConfig.ToleranceResultDialogConfig("") //todo
        }
    }


    @Stable
    private fun createChildDialog(
        config: DialogConfig,
        childComponentContext: ComponentContext
    ): ITolerancesScreenComponent.DialogChild = when (config) {
        is DialogConfig.ToleranceResultDialogConfig -> {
            ITolerancesScreenComponent.DialogChild.ToleranceResult(
                TolerancesResultDialogComponentImpl(
                    childComponentContext,
                    onDismiss = { slotNavigation.navigate { null } })
            )
        }
    }


    @Serializable
    private sealed class DialogConfig {
        @Serializable
        data class ToleranceResultDialogConfig(val any: String) : DialogConfig()

    }


}