package ru.tolerances.app.domain.models

import kotlin.math.PI

sealed interface CuttingCalcType {

    data class CalcV(val n: Float) : CuttingCalcType {
        override fun getTypeTitle(): String {
            return "Скорость резания"
        }

        override fun getShortName(): String {
            return "Vc"
        }

        override fun calculateResult(diam: Float): String {
            return ((PI * diam * n) / 1000).toString()
        }
    }

    data class CalcN(val v: Float) : CuttingCalcType {
        override fun getTypeTitle(): String {
            return "Количество оборотов"
        }

        override fun getShortName(): String {
            return "N"
        }

        override fun calculateResult(diam: Float): String {
            return ((1000 * v) / (PI * diam)).toString()
        }
    }

    fun getTypeTitle(): String

    fun getShortName(): String

    fun calculateResult(diam: Float): String

}