package io.github.jeremygitau.kogee

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
