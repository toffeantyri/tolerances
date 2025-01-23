package ru.tolerances.app.components

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.tolerances.app.read_csv_repository.ICsvReader
import ru.tolerances.app.utils.componentCoroutineScope

class RootComponentImpl(componentContext: ComponentContext) : IRootComponent,
    ComponentContext by componentContext, KoinComponent {

    private val rootCoroutineScope = componentContext.componentCoroutineScope()

    override val csvReader: ICsvReader = get()

    init {
        rootCoroutineScope.launch {
            csvReader.init()
        }
    }


}