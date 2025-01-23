package ru.tolerances.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.tolerances.app.components.IRootComponent

@Composable
fun RootApp(component: IRootComponent) {

    val rangesList = component.csvReader.intRanges.collectAsState()

    LaunchedEffect(Unit) {
        component.csvReader.init()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(rangesList.value) {
            Text(modifier = Modifier.fillMaxWidth(), text = it.toString())

        }

    }


}

