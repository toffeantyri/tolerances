package ru.tolerances.app.components

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import ru.tolerances.app.components.cutting_speed_screen_component.CuttingSpeedScreenComponentImpl
import ru.tolerances.app.components.tolerances_screen_component.TolerancesScreenComponentImpl
import ru.tolerances.app.utils.componentCoroutineScope

class RootComponentImpl(
    componentContext: ComponentContext,
    private val onExitClicked: () -> Unit = {}
) : IRootComponent,
    ComponentContext by componentContext, KoinComponent, BackHandlerOwner {

    private val rootCoroutineScope = componentContext.componentCoroutineScope()


    private val rootNavStack = StackNavigation<ConfigBottom>()

    private val _childStackBottom =
        childStack(
            source = rootNavStack,
            serializer = ConfigBottom.serializer(),
            initialConfiguration = ConfigBottom.TolerancesTableScreen,
            handleBackButton = false,
            childFactory = ::createChildBottom,
            key = "root"
        )

    override val childStackBottom: Value<ChildStack<*, IRootComponent.Child>>
        get() = _childStackBottom

    private val _selectedBottomTab = MutableStateFlow(0)

    override val selectedBottomTab: StateFlow<Int>
        get() = _selectedBottomTab

    override fun onExitClicked() {
        onExitClicked()
    }

    override fun onTolerancesScreen() {
        _selectedBottomTab.value = 0
        rootNavStack.bringToFront(ConfigBottom.TolerancesTableScreen)
    }

    override fun onCuttingSpeedScreen() {
        _selectedBottomTab.value = 1
        rootNavStack.bringToFront(ConfigBottom.CuttingScreenScreen)
    }


    @Stable
    private fun createChildBottom(
        config: ConfigBottom,
        componentContext: ComponentContext
    ): IRootComponent.Child =
        when (config) {
            ConfigBottom.TolerancesTableScreen -> IRootComponent.Child.OnTolerancesScreenChild(
                component = TolerancesScreenComponentImpl(
                    componentContext = componentContext
                )
            )

            ConfigBottom.CuttingScreenScreen -> IRootComponent.Child.OnCuttingSpeedScreenChild(
                component = CuttingSpeedScreenComponentImpl(
                    componentContext = componentContext
                )
            )
        }

    @Serializable
    private sealed class ConfigBottom {

        @Serializable
        data object TolerancesTableScreen : ConfigBottom()

        @Serializable
        data object CuttingScreenScreen : ConfigBottom()


    }


}