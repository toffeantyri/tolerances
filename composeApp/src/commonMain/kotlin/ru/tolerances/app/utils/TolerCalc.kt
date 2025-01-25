package ru.tolerances.app.utils


public inline fun <T> Iterable<T>.firstIndexOrNull(predicate: (T) -> Boolean): Int? {
    for ((index, element) in this.withIndex()) if (predicate(element)) return index
    return null
}