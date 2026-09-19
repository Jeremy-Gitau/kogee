package io.github.jeremygitau.kogee.core

data class Node(
    val id: String,
    val type: String,
    val properties: Map<String, String> = emptyMap(),
)
