package ru.tolerances.app.components.tolerances_screen_component

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.tolerances.app.read_csv_repository.ICsvReader

class TolerancesScreenComponentImpl(componentContext: ComponentContext) :
    ITolerancesScreenComponent, ComponentContext by componentContext, KoinComponent {

    override val csvReader: ICsvReader get() = get()

}