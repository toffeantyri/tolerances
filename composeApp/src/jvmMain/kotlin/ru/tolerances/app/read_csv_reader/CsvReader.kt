package ru.tolerances.app.read_csv_reader

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ru.tolerances.app.read_csv_repository.ICsvReader
import tolerancestabledata.composeapp.generated.resources.Res

class CsvReader : ICsvReader {

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun read() {
        val csvStrung = Res.readBytes("files/pole_dop_utf_8.csv").decodeToString()
        println("LOG $csvStrung")
        csvReader {

        }

    }

}