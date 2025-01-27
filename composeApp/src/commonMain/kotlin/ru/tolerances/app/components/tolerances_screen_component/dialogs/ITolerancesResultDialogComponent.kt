package ru.tolerances.app.components.tolerances_screen_component.dialogs

import com.arkivanov.decompose.value.Value

interface ITolerancesResultDialogComponent {

    val resultValue: Value<String>

    fun onDismissAction()

}