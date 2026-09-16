package io.github.jeremygitau.kogee.core

class KnowledgeGraph {
    private val nodes = mutableMapOf<String, Node>()

    fun addNode(node: Node) {
        nodes[node.id] = node
    }

    fun getNode(id: String): Node? = nodes[id]
}
