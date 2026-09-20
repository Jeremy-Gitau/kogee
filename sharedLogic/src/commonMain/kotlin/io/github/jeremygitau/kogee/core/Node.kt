package io.github.jeremygitau.kogee.core

/**
 * A single entity in the knowledge graph — a customer, an order, a payment, or
 * any other piece of application knowledge.
 *
 * @property id A unique identifier for this node, e.g. "order:9821".
 * @property type The kind of entity this node represents, e.g. "Order".
 * @property properties Arbitrary facts about this node, e.g. "status" to "pending".
 */

data class Node(
    val id: String,
    val type: String,
    val properties: Map<String, String> = emptyMap(),
)
