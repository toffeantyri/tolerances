package ru.tolerances.app

class JsPlatform: Platform {
    override val name: String = "WebJs with Kotlin"
}

actual fun getPlatform(): Platform = JsPlatform()