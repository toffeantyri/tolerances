package ru.tolerances.app.components

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.tolerances.app.read_csv_repository.ICsvReader

class RootComponentImpl(componentContext: ComponentContext) : IRootComponent,
    ComponentContext by componentContext, KoinComponent {
    override val csvReader: ICsvReader = get()




}