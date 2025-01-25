package ru.tolerances.app.ui.tolerances_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.tolerances.app.components.tolerances_screen_component.ITolerancesScreenComponent

@Composable
fun TolerancesScreenView(component: ITolerancesScreenComponent) {

    val rangesList = component.viewModel.csvReader.intRanges.collectAsState()
    val tolerancesISOList = component.viewModel.csvReader.tolerancesISOList.collectAsState()
    val tolerancesGOSTList = component.viewModel.csvReader.tolerancesGOSTList.collectAsState()
    val tolerancesTable = component.viewModel.csvReader.tolerancesTable.collectAsState()

    LaunchedEffect(Unit) {
        println(tolerancesGOSTList)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(modifier = Modifier.padding(start = 30.dp).fillMaxWidth()) {
            itemsIndexed(tolerancesISOList.value) { index, item ->
                Column {
                    Text(modifier = Modifier.wrapContentSize(), text = item.toString())

                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = tolerancesGOSTList.value[index].toString()
                    )
                }

            }

        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(rangesList.value) {
                Text(modifier = Modifier.wrapContentWidth(), text = it.toString())
            }

        }

    }
}