package ru.tolerances.app.components.tolerances_screen_component.dialogs

import com.arkivanov.decompose.value.Value
import ru.tolerances.app.read_csv_repository.ICsvReader

interface ITolerancesResultDialogComponent {

    val resultValue: Value<ICsvReader.UnitData>

    val inputedSize: Value<Float>

    fun onDismissAction()

}