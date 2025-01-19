package ru.tolerances.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform