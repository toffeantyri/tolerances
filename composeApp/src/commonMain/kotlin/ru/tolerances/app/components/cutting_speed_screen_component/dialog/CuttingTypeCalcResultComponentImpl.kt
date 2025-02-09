package ru.tolerances.app.components.cutting_speed_screen_component.dialog

import com.arkivanov.decompose.ComponentContext

class CuttingTypeCalcResultComponentImpl(
    componentContext: ComponentContext,
    private val onDismiss: () -> Unit
) :
    ICuttingTypeCalcResultComponent, ComponentContext by componentContext {

}