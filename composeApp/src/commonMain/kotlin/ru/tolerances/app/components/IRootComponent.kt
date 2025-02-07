package ru.tolerances.app.components

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandler
import ru.tolerances.app.components.cutting_speed_screen_component.ICuttingSpeedScreenComponent
import ru.tolerances.app.components.tolerances_screen_component.ITolerancesScreenComponent

interface IRootComponent {

    val backHandler: BackHandler

    val childStackBottom: Value<ChildStack<*, Child>>

    fun onExitClicked()

    sealed class Child {
        data class OnTolerancesScreenChild(val component: ITolerancesScreenComponent) : Child()

        data class OnCuttingSpeedScreenChild(val component: ICuttingSpeedScreenComponent) : Child()

    }


}