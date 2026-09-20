package io.github.jeremygitau.kogee.core

/**
 * A directed relationship between two nodes in the knowledge graph.
 *
 * @property from The id of the node this relationship starts at.
 * @property relation The name of the relationship, e.g. "CREATED_BY" or "PAID_WITH".
 * @property to The id of the node this relationship points to.
 */

data class Edge(
    val from: String,
    val relation: String,
    val to: String,
)
