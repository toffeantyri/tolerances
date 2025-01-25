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
import kotlin.math.roundToInt

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
        viewModelScope.launch {
            uiModel.value.tolerancesField.value = value

        }
    }


    fun onUserInputValue(value: String) {
        fun clearSearchResult() = run { uiModel.value.searchRangeResult.clear() }
        viewModelScope.launch {
            clearSearchResult()
            uiModel.value.userValueField.value = value
            value.toDoubleOrNull()?.roundToInt().let { int ->
                csvReader.intRanges.value.firstOrNull { int in it }?.let { searchResult ->
                    uiModel.value.searchRangeResult.add(searchResult.toString())
                } ?: clearSearchResult()
            }


        }
    }


    data class Model(
        val userValueField: MutableState<String> = mutableStateOf(EMPTY),
        val tolerancesField: MutableState<String> = mutableStateOf(EMPTY),
        val searchRangeResult: SnapshotStateList<String> = mutableStateListOf(),
        val searchToleranceResult: SnapshotStateList<String> = mutableStateListOf()
    )


}