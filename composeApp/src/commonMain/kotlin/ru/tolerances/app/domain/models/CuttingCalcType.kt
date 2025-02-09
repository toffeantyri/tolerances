package ru.tolerances.app.domain.models

sealed interface CuttingCalcType {

    data object CalcV : CuttingCalcType {
        override fun getTypeTitle(): String {
            return "Скорость резания"
        }

        override fun getShortName(): String {
            return "Vc"
        }
    }

    data object CalcN : CuttingCalcType {
        override fun getTypeTitle(): String {
            return "Количество оборотов"
        }

        override fun getShortName(): String {
            return "N"
        }
    }

    fun getTypeTitle(): String

    fun getShortName(): String

}