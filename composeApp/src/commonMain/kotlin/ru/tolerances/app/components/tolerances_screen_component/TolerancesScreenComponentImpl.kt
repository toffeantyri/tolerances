package ru.tolerances.app.components.tolerances_screen_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.tolerances.app.read_csv_repository.ICsvReaderViewModel

class TolerancesScreenComponentImpl(componentContext: ComponentContext) :
    ITolerancesScreenComponent, ComponentContext by componentContext, KoinComponent {

    override val csvReader = componentContext.instanceKeeper.getOrCreate {
        get<ICsvReaderViewModel>()
    }

}