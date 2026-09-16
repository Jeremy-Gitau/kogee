package io.github.jeremygitau.kogee

class Greeting {
    private val platform = getPlatform()

    fun greet(): String = sayHello(platform.name)
}
