package ru.tolerances.app.components

import ru.tolerances.app.read_csv_repository.ICsvReader

interface IRootComponent {

    val csvReader: ICsvReader

}