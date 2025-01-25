package ru.tolerances.app.read_csv_repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import tolerancestabledata.composeapp.generated.resources.Res

class CsvReaderImpl : ICsvReader {

    companion object {
        const val NULL = "-"
    }

    private val _intRanges: MutableStateFlow<List<ClosedFloatingPointRange<Double>>> =
        MutableStateFlow(emptyList())
    private val _tolerancesISOList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val _tolerancesGOSTList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val _tolerancesTable: MutableStateFlow<List<List<String>>> =
        MutableStateFlow(emptyList())

    override val intRanges: StateFlow<List<ClosedFloatingPointRange<Double>>> get() = _intRanges
    override val tolerancesISOList: StateFlow<List<String>> get() = _tolerancesISOList
    override val tolerancesGOSTList: StateFlow<List<String>> get() = _tolerancesGOSTList
    override val tolerancesTable: StateFlow<List<List<String>>> get() = _tolerancesTable

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun init() {
        val csvString = Res.readBytes("files/pole_dop_utf_8.csv").decodeToString()
        val allLinesList = csvString.lines()

        _tolerancesTable.value =
            allLinesList.initRangeListAndDropFirstStroke().initTolerancesListsAndDropIt()
                .validateTable()
    }


    private fun List<String>.initRangeListAndDropFirstStroke(): List<List<String>> {
        val rangeList: MutableList<ClosedFloatingPointRange<Double>> = mutableListOf()
        val resultList = this.mapIndexed { _, line ->
            val lineList = line.split(";")
            val first = lineList.first()
            if (first.contains(NULL).not()) {
                val asd = 1.0..2.0
                first.split("..").let {
                    rangeList.add(it[0].toDouble()..it[1].toDouble())
                }
            }
            lineList.drop(1)
        }
        _intRanges.value = rangeList
        return resultList
    }

    private fun List<List<String>>.initTolerancesListsAndDropIt(): List<List<String>> {
        _tolerancesISOList.value = this[0]
        _tolerancesGOSTList.value = this[this.lastIndex]
        return this.dropLast(1).drop(1)
    }

    private fun List<List<String>>.validateTable(): List<List<String>> {
        val strokeSize = this.size
        val lineSize = _tolerancesISOList.value.size
        if (_intRanges.value.size != strokeSize) throw IllegalArgumentException("table stroke size not equals intRanges size")
        if (_tolerancesISOList.value.size != _tolerancesGOSTList.value.size) throw IllegalArgumentException(
            "tolerances lists not equals by size"
        )
        this.forEachIndexed { index, line ->
            if (line.size != lineSize) throw IllegalArgumentException("line index=$index not equals tolerances iso list size")
        }
        return this
    }


}