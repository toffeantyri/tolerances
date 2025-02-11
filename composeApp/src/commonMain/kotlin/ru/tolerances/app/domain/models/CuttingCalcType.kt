package ru.tolerances.app.domain.models

import kotlin.math.PI

sealed interface CuttingCalcType {

    data class CalcV(val n: Float) : CuttingCalcType {
        override fun getTypeTitle(): String {
            return "Скорость резания"
        }

        override fun getShortName(): String {
            return "N"
        }

        override fun calculateResult(diam: Float): String {
            if (n <= 0) return "Количество оборотов в минуту должно быть больше 0"
            if (diam <= 0) return "Введите диаметр больше 0"
            return ((PI * diam * n) / 1000).toString()
        }
    }

    data class CalcN(val v: Float) : CuttingCalcType {
        override fun getTypeTitle(): String {
            return "Количество оборотов"
        }

        override fun getShortName(): String {
            return "Vc"
        }

        override fun calculateResult(diam: Float): String {
            if (v <= 0) return "Введите скорость резания больше 0"
            if (diam <= 0) return "Введите диаметр больше 0"
            return ((1000 * v) / (PI * diam)).toString()
        }
    }

    fun getTypeTitle(): String

    fun getShortName(): String

    fun calculateResult(diam: Float): String

}