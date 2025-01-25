package ru.tolerances.app.components.tolerances_screen_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.tolerances.app.utils.componentCoroutineScope

class TolerancesScreenComponentImpl(componentContext: ComponentContext) :
    ITolerancesScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val coroutineScope = componentContext.componentCoroutineScope()

    override val viewModel = componentContext.instanceKeeper.getOrCreate {
        TolerancesViewModel(viewModelScope = coroutineScope, csvReader = get())
    }

}