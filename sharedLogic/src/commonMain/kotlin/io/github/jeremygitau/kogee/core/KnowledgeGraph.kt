package io.github.jeremygitau.kogee.core

/**
 * An in-memory store of [Node]s and [Edge]s, with basic traversal support.
 *
 * This is the core, storage-level API — [remember] is a friendlier alias for
 * [addNode], kept separate so a richer "remember" behavior (e.g. automatic
 * extraction) can be added later without changing what [addNode] guarantees.
 */

class KnowledgeGraph {
    private val nodes = mutableMapOf<String, Node>()

    private val edges = mutableMapOf<String, MutableList<Edge>>()

    fun addNode(node: Node) {
        nodes[node.id] = node
    }

    fun remember(node: Node) {
        addNode(node)
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

    /**
     * Returns the ids of every node reachable from [from] within [maxHops] hops,
     * following any relation. A node already visited is never revisited, so cycles
     * in the graph cannot cause this to loop forever.
     */
    fun neighborhood(
        from: String,
        maxHops: Int,
    ): Set<String> {
        val visited = mutableSetOf(from)
        var frontier = listOf(from)

        repeat(maxHops) {
            val next =
                frontier
                    .flatMap { getEdgesFrom(it).map { edge -> edge.to } }
                    .filter { it !in visited }

            visited.addAll(next)
            frontier = next
        }

        visited.remove(from)
        return visited
    }
}
