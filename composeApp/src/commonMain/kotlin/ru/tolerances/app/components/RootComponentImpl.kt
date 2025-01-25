package ru.tolerances.app.components

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
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

    override fun onExitClicked() {
        onExitClicked()
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
        }

    @Serializable
    private sealed class ConfigBottom {

        @Serializable
        data object TolerancesTableScreen : ConfigBottom()


    }


}