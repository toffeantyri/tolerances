package ru.tolerances.app.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.tolerances.app.read_csv_repository.CsvReader
import ru.tolerances.app.read_csv_repository.ICsvReader

val csvReaderModule: Module = module {

    single<ICsvReader> {
        CsvReader()
    }

}
