package ru.tolerances.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ru.tolerances.app.components.IRootComponent

@Composable
fun RootApp(component: IRootComponent) {

    LaunchedEffect(Unit) {
        val tolerancesTable = component.csvReader.read()
        tolerancesTable.forEach {
            println("LOG $it")
        }
    }


}

