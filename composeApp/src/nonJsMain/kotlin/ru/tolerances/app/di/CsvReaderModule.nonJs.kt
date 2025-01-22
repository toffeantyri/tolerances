package ru.tolerances.app.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.tolerances.app.read_csv_reader.CsvReader
import ru.tolerances.app.read_csv_repository.ICsvReader

actual val csvReaderModule: Module = module {
    single<ICsvReader> { CsvReader() }
}