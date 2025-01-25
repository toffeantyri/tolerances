package ru.tolerances.app.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.tolerances.app.read_csv_repository.CsvReaderImpl
import ru.tolerances.app.read_csv_repository.ICsvReader

val csvReaderModule: Module = module {

    single<ICsvReader> {
        CsvReaderImpl()
    }

}
