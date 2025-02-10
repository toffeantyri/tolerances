package ru.tolerances.app.components.cutting_speed_screen_component.dialog

import com.arkivanov.decompose.value.Value

interface ICuttingTypeCalcResultComponent {

    fun onDismissAction()

    val resultValue: Value<String>

}