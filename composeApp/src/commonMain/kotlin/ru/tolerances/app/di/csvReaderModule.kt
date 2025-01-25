package ru.tolerances.app.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.tolerances.app.read_csv_repository.CsvReaderViewModel
import ru.tolerances.app.read_csv_repository.ICsvReaderViewModel

val csvReaderModule: Module = module {

    single<ICsvReaderViewModel> {
        CsvReaderViewModel()
    }

}
