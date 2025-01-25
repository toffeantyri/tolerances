package ru.tolerances.app.read_csv_repository

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.flow.StateFlow

interface ICsvReaderViewModel : InstanceKeeper.Instance {

    val intRanges: StateFlow<List<IntRange>>

    suspend fun init()

}
