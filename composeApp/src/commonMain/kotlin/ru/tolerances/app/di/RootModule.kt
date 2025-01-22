package ru.tolerances.app.di

import org.koin.dsl.module

val rootModule = module {

    includes(csvReaderModule)

}