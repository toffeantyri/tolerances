package ru.tolerances.app.components.tolerances_screen_component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.tolerances.app.read_csv_repository.ICsvReader
import ru.tolerances.app.utils.EMPTY
import ru.tolerances.app.utils.firstIndexOrNull

class TolerancesViewModel(
    private val viewModelScope: CoroutineScope,
    val csvReader: ICsvReader
) : InstanceKeeper.Instance {

    val uiModel: Value<Model> = MutableValue(Model())

    init {
        viewModelScope.launch {
            csvReader.init()
        }
    }

    fun onToleranceInputValue(value: String) {
        fun clearSearchResult() = run { uiModel.value.searchToleranceResultIndex.clear() }
        viewModelScope.launch {
            clearSearchResult()
            uiModel.value.tolerancesField.value = value
            if (value.isEmpty()) return@launch
            val findedIndex = mutableListOf<Int>()
            csvReader.tolerancesISOList.value.filterIndexed { index, s ->
                val result = s.contains(value)
                if (result) findedIndex.add(index)
                result
            }

            if (findedIndex.isEmpty()) {
                csvReader.tolerancesGOSTList.value.filterIndexed { index, s ->
                    val result = s.contains(value)
                    if (result) findedIndex.add(index)
                    result
                }
            }
            if (findedIndex.isNotEmpty()) {
                uiModel.value.searchToleranceResultIndex.addAll(findedIndex)
            } else {
                clearSearchResult()
            }

        }
    }


    fun onUserInputValue(value: String) {
        fun clearSearchResult() = run { uiModel.value.searchRangeResultIndex.value = null }
        viewModelScope.launch {
            clearSearchResult()
            uiModel.value.userValueField.value = value
            value.toDoubleOrNull()?.let { num ->

                csvReader.intRanges.value.firstIndexOrNull { num in it }?.let { rangeIndex ->
                    uiModel.value.searchRangeResultIndex.value = rangeIndex
                } ?: clearSearchResult()
            }


        }
    }


    data class Model(
        val userValueField: MutableState<String> = mutableStateOf(EMPTY),
        val tolerancesField: MutableState<String> = mutableStateOf(EMPTY),
        val searchRangeResultIndex: MutableState<Int?> = mutableStateOf(null),
        val searchToleranceResultIndex: SnapshotStateList<Int> = mutableStateListOf()
    )


}