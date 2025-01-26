package ru.tolerances.app.utils

import kotlin.math.roundToInt


inline fun <T> Iterable<T>.firstIndexOrNull(predicate: (T) -> Boolean): Int? {
    for ((index, element) in this.withIndex()) if (predicate(element)) return index
    return null
}

fun ClosedFloatingPointRange<Double>.toIntRange(): IntRange {
    return this.start.roundToInt()..this.endInclusive.roundToInt()
}