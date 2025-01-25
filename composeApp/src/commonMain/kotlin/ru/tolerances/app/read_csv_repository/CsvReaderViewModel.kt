package ru.tolerances.app.read_csv_repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import tolerancestabledata.composeapp.generated.resources.Res

class CsvReaderViewModel : ICsvReaderViewModel {

    companion object {
        const val NULL = "-"
    }

    private val _intRanges: MutableStateFlow<List<IntRange>> = MutableStateFlow(emptyList())

    override val intRanges: StateFlow<List<IntRange>>
        get() = _intRanges

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun init() {
        val csvString = Res.readBytes("files/pole_dop_utf_8.csv").decodeToString()
        val allLinesList = csvString.lines()

        allLinesList.initRangeListAndDropFirstStroke().let {
            println(it)
        }
    }


    private fun List<String>.initRangeListAndDropFirstStroke(): List<List<String>> {
        val rangeList: MutableList<IntRange> = mutableListOf()
        val resultList = this.mapIndexed { index, line ->
            val lineList = line.split(";")
            val first = lineList.first()
            if (first.contains(NULL).not()) {
                first.split("..").let {
                    rangeList.add(it[0].toInt()..it[1].toInt())
                }
            }
            lineList.drop(1)
        }
        _intRanges.value = rangeList
        return resultList
    }


}