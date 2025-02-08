package ru.tolerances.app.domain.models

sealed interface CuttingCalcType {

    data object CalcV : CuttingCalcType {
        override fun getTypeTitle(): String {
            return "Скорость резания"
        }
    }

    data object CalcN : CuttingCalcType {
        override fun getTypeTitle(): String {
            return "Количество оборотов"
        }
    }

    fun getTypeTitle(): String

}