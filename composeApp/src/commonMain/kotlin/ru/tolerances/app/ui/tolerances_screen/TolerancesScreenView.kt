package ru.tolerances.app.ui.tolerances_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.tolerances.app.components.tolerances_screen_component.ITolerancesScreenComponent
import ru.tolerances.app.ui.generals.InputTextField
import ru.tolerances.app.ui.generals.TitleText

@Composable
fun TolerancesScreenView(component: ITolerancesScreenComponent) {

    val rangesList = component.viewModel.csvReader.intRanges.collectAsState()
    val tolerancesISOList = component.viewModel.csvReader.tolerancesISOList.collectAsState()
    val tolerancesGOSTList = component.viewModel.csvReader.tolerancesGOSTList.collectAsState()
    val tolerancesTable = component.viewModel.csvReader.tolerancesTable.collectAsState()

    val uiModel = component.viewModel.uiModel.subscribeAsState()
    val searchedRange = remember {
        derivedStateOf {
            uiModel.value.searchRangeResultIndex.value?.let {
                rangesList.value[it]
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TitleText(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            text = "Таблица допусков и посадок",
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputTextField(
                    modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                    valueState = uiModel.value.userValueField,
                    onValueChange = { component.viewModel.onUserInputValue(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "Размер") },
                )

                AnimatedVisibility(searchedRange.value != null) {
                    Text(
                        modifier = Modifier.clickable {
                            component.viewModel.onUserInputValue(searchedRange.value.toString())
                        }.padding(top = 2.dp)
                            .border(width = 1.dp, shape = RectangleShape, color = Color.DarkGray)
                            .padding(16.dp),
                        text = searchedRange.value?.toString() ?: "",
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth(1f)) {
                InputTextField(
                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                    valueState = uiModel.value.tolerancesField,
                    onValueChange = { component.viewModel.onToleranceInputValue(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "Допуск") },
                )
            }


        }

    }
}