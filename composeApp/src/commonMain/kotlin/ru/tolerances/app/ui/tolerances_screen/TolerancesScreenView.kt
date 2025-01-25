package ru.tolerances.app.ui.tolerances_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.tolerances.app.components.tolerances_screen_component.ITolerancesScreenComponent
import ru.tolerances.app.ui.generals.InputTextField

@Composable
fun TolerancesScreenView(component: ITolerancesScreenComponent) {

    val rangesList = component.viewModel.csvReader.intRanges.collectAsState()
    val tolerancesISOList = component.viewModel.csvReader.tolerancesISOList.collectAsState()
    val tolerancesGOSTList = component.viewModel.csvReader.tolerancesGOSTList.collectAsState()
    val tolerancesTable = component.viewModel.csvReader.tolerancesTable.collectAsState()

    LaunchedEffect(Unit) {
        println(tolerancesGOSTList)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InputTextField(
                modifier = Modifier.weight(0.5f).padding(end = 8.dp),
                valueState = component.viewModel.uiModel.value.userValue,
                onValueChange = { component.viewModel.onUserInputValue(it) },
            )

            InputTextField(
                modifier = Modifier.weight(0.5f).padding(start = 8.dp),
                valueState = component.viewModel.uiModel.value.userValue,
                onValueChange = { component.viewModel.onUserInputValue(it) },
            )

        }


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(rangesList.value) {
                Text(modifier = Modifier.wrapContentWidth(), text = it.toString())
            }
        }

    }
}