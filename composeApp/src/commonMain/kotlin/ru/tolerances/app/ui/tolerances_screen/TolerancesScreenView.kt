package ru.tolerances.app.ui.tolerances_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.tolerances.app.components.tolerances_screen_component.ITolerancesScreenComponent
import ru.tolerances.app.ui.generals.InputTextField
import ru.tolerances.app.ui.generals.MyFilledButton
import ru.tolerances.app.ui.generals.TitleText
import ru.tolerances.app.ui.theme.OceanBlue
import ru.tolerances.app.ui.theme.medium14TextStyle
import ru.tolerances.app.ui.theme.medium16TextStyle
import ru.tolerances.app.ui.theme.regular12TextStyle
import ru.tolerances.app.utils.onKeyEnter
import ru.tolerances.app.utils.toIntRange

@Composable
fun TolerancesScreenView(component: ITolerancesScreenComponent) {

    val rangesList = component.viewModel.csvReader.intRanges.collectAsState()
    val tolerancesISOList = component.viewModel.csvReader.tolerancesISOList.collectAsState()
    val tolerancesGOSTList = component.viewModel.csvReader.tolerancesGOSTList.collectAsState()
    val tolerancesTable = component.viewModel.csvReader.tolerancesTable.collectAsState()

    val uiModel = component.viewModel.uiModel.subscribeAsState()

    val buttonEnabled = remember {
        derivedStateOf {
            (uiModel.value.searchRangeResultIndex.value != null) && (uiModel.value.searchToleranceResultIndex.size == 1)
        }
    }

    val searchedRange = remember {
        derivedStateOf {
            uiModel.value.searchRangeResultIndex.value?.let {
                rangesList.value[it].toIntRange()
            }
        }
    }

    val dialogSlot = component.dialog.subscribeAsState()

    dialogSlot.value.child?.let { child ->
        when (val item = child.instance) {
            is ITolerancesScreenComponent.DialogChild.ToleranceResult -> ToleranceResultDialogView(
                item.component
            )
        }
    }


//    LaunchedEffect(buttonEnabled.value) {
//        if (buttonEnabled.value) {
//            with(uiModel.value) {
//                searchRangeResultIndex.value?.let { rangeIndex ->
//                    searchToleranceResultIndex.firstOrNull()?.let { toleranceIndex ->
//                        component.showDialogToleranceResult(rangeIndex, toleranceIndex)
//                    }
//                }
//            }
//        }
//    }

    val focusRequester = remember { FocusRequester() }

    Surface() {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .onKeyEnter(focusRequester) {
                        if (buttonEnabled.value) {
                            with(uiModel.value) {
                                searchRangeResultIndex.value?.let { rangeIndex ->
                                    searchToleranceResultIndex.firstOrNull()
                                        ?.let { toleranceIndex ->
                                            component.showDialogToleranceResult(
                                                rangeIndex,
                                                toleranceIndex
                                            )
                                        }
                                }
                            }
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    TitleText(
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                        text = "Таблица допусков и посадок ЕСДП",
                        textAlign = TextAlign.Center,
                        textStyle = medium16TextStyle()
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                            .padding(top = 8.dp),
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
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Интервал",
                                        style = medium14TextStyle()
                                    )

                                    Text(
                                        modifier = Modifier.fillMaxWidth().padding(end = 8.dp)
                                            .padding(top = 0.dp)
                                            .border(
                                                width = 1.dp,
                                                shape = RectangleShape,
                                                color = OceanBlue
                                            )
                                            .padding(16.dp),
                                        textAlign = TextAlign.Center,
                                        text = searchedRange.value?.toString() ?: "",
                                        style = MaterialTheme.typography.displayMedium
                                    )
                                }
                            }
                        }

                        Column(modifier = Modifier.fillMaxWidth(1f)) {
                            InputTextField(
                                modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                                valueState = uiModel.value.tolerancesField,
                                onValueChange = { component.viewModel.onToleranceInputValue(it) },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.None
                                ),
                                label = { Text(text = "Допуск") },
                            )
                            AnimatedVisibility(uiModel.value.searchToleranceResultIndex.isNotEmpty()) {

                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Text(
                                            modifier = Modifier.padding(16.dp),
                                            text = "ISO",
                                            style = medium14TextStyle()
                                        )

                                        Text(
                                            modifier = Modifier.padding(16.dp),
                                            text = "ОСТ*",
                                            style = medium14TextStyle()
                                        )

                                    }

                                    uiModel.value.searchToleranceResultIndex.forEach { toleranceIndex ->

                                        Row(
                                            modifier = Modifier.padding(start = 8.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(0.5f).clickable {
                                                    with(uiModel.value) {
                                                        searchRangeResultIndex.value?.let { rangeIndex ->
                                                            if (tolerancesISOList.value[toleranceIndex] != "-") {
                                                                component.showDialogToleranceResult(
                                                                    rangeIndex,
                                                                    toleranceIndex
                                                                )
                                                            }
                                                        }
                                                    }
                                                }.padding(top = 0.dp)
                                                    .border(
                                                        width = 1.dp,
                                                        shape = RectangleShape,
                                                        color = OceanBlue
                                                    )
                                                    .padding(16.dp),
                                                textAlign = TextAlign.Start,
                                                text = tolerancesISOList.value[toleranceIndex],
                                                style = MaterialTheme.typography.displayMedium
                                            )


                                            Text(
                                                modifier = Modifier.fillMaxWidth(1f).clickable {
                                                    with(uiModel.value) {
                                                        searchRangeResultIndex.value?.let { rangeIndex ->
                                                            if (tolerancesGOSTList.value[toleranceIndex] != "-") {
                                                                component.showDialogToleranceResult(
                                                                    rangeIndex,
                                                                    toleranceIndex
                                                                )
                                                            }
                                                        }
                                                    }
                                                }.padding(top = 0.dp)
                                                    .border(
                                                        width = 1.dp,
                                                        shape = RectangleShape,
                                                        color = OceanBlue
                                                    )
                                                    .padding(16.dp),
                                                textAlign = TextAlign.End,
                                                text = tolerancesGOSTList.value[toleranceIndex],
                                                style = MaterialTheme.typography.displayMedium
                                            )
                                        }
                                    }

                                    Text(
                                        text = "*Предельные отклонения по ЕСДП и системе ОСТ совпадают не полностью",
                                        textAlign = TextAlign.End,
                                        style = regular12TextStyle()
                                    )
                                }


                            }
                        }


                    }
                }
                item { Spacer(modifier = Modifier.height(62.dp)) }
            }

            MyFilledButton(
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                    .padding(horizontal = 16.dp).padding(8.dp),
                text = "Показать",
                onClick = {
                    with(uiModel.value) {
                        searchRangeResultIndex.value?.let { rangeIndex ->
                            searchToleranceResultIndex.firstOrNull()?.let { toleranceIndex ->
                                component.showDialogToleranceResult(rangeIndex, toleranceIndex)
                            }
                        }
                    }
                },
                enabled = buttonEnabled.value
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

}