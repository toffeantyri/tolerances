package ru.tolerances.app.components.tolerances_screen_component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.tolerances.app.read_csv_repository.ICsvReader
import ru.tolerances.app.utils.EMPTY

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


    data class Model(
        val userValue: MutableState<String> = mutableStateOf(EMPTY)
    )

    fun onUserInputValue(value: String) {
        uiModel.value.userValue.value = value
    }

}