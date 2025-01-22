package ru.tolerances.app.components

import com.arkivanov.decompose.ComponentContext

class RootComponentImpl(componentContext: ComponentContext) : IRootComponent,
    ComponentContext by componentContext {

}