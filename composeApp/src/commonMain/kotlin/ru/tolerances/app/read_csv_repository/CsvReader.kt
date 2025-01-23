package ru.tolerances.app.read_csv_repository

import org.jetbrains.compose.resources.ExperimentalResourceApi
import tolerancestabledata.composeapp.generated.resources.Res

class CsvReader : ICsvReader {

    companion object {
        const val NULL = "-"
    }

    private val _rangeList: MutableList<IntRange> = mutableListOf()
    val rangeList: List<IntRange> get() = _rangeList

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun read(): List<List<String>> {
        val csvString = Res.readBytes("files/pole_dop_utf_8.csv").decodeToString()

        val strokeList = csvString.lines()

        val result = strokeList.mapIndexed { index, line ->
            val lineList = line.split(";")

            val first = lineList.first()
            if (first.contains(NULL).not()) {
                first.split("..").let {
                    _rangeList.add(it[0].toInt()..it[1].toInt())
                }
            }

            lineList.drop(1)
        }
        return result
    }


}