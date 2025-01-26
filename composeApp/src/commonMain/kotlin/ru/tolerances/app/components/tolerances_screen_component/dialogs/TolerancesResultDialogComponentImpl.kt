package ru.tolerances.app.components.tolerances_screen_component.dialogs

import com.arkivanov.decompose.ComponentContext

class TolerancesResultDialogComponentImpl(
    componentContext: ComponentContext,
    private val onDismiss: () -> Unit
) :
    ITolerancesResultDialogComponent, ComponentContext by componentContext {

    override fun onDismissAction() {
        onDismiss()
    }

}