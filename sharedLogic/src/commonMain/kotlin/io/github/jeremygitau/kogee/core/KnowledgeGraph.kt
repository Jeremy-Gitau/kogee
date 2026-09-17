package io.github.jeremygitau.kogee.core

class KnowledgeGraph {
    private val nodes = mutableMapOf<String, Node>()

    private val edges = mutableMapOf<String, MutableList<Edge>>()

    fun addNode(node: Node) {
        nodes[node.id] = node
    }

    fun getNode(id: String): Node? = nodes[id]

    fun addEdge(edge: Edge) {
        edges.getOrPut(edge.from) { mutableListOf() }.add(edge)
    }

    fun getEdgesFrom(id: String): List<Edge> = edges[id] ?: emptyList()

    fun follow(
        from: String,
        relation: String,
    ): List<String> =
        getEdgesFrom(from)
            .filter { it.relation == relation }
            .map { it.to }

    fun follow(
        from: String,
        relations: List<String>,
    ): List<String> {
        var current = listOf(from)

        for (relation in relations) {
            current = current.flatMap { follow(from = it, relation = relation) }
        }

        return current
    }
}
