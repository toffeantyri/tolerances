package ru.tolerances.app.components.cutting_speed_screen_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate

class CuttingSpeedScreenComponentImpl(
    componentContext: ComponentContext
) : ICuttingSpeedScreenComponent, ComponentContext by componentContext {

    override val viewModel: CuttingSpeedViewModel = instanceKeeper.getOrCreate {
        CuttingSpeedViewModel()
    }


}