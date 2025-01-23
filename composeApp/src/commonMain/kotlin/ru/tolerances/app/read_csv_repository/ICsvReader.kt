package ru.tolerances.app.read_csv_repository

import kotlinx.coroutines.flow.StateFlow

interface ICsvReader {

    val intRanges: StateFlow<List<IntRange>>

    suspend fun init()

}
