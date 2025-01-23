package ru.tolerances.app.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val rootModules = module {

    includes(csvReaderModule)

}


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(rootModules)
        createEagerInstances()
    }