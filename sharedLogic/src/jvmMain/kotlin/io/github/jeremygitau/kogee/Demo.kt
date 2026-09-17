package io.github.jeremygitau.kogee

import io.github.jeremygitau.kogee.core.Edge
import io.github.jeremygitau.kogee.core.KnowledgeGraph
import io.github.jeremygitau.kogee.core.Node

fun main() {
    val graph = KnowledgeGraph()

    graph.addNode(Node(id = "order:9821", type = "Order"))
    graph.addNode(Node(id = "customer:123", type = "Customer"))
    graph.addNode(Node(id = "payment:9821", type = "Payment"))
    graph.addNode(Node(id = "transaction:TX92", type = "Transaction"))

    graph.addEdge(Edge(from = "order:9821", relation = "CREATED_BY", to = "customer:123"))
    graph.addEdge(Edge(from = "order:9821", relation = "PAID_WITH", to = "payment:9821"))
    graph.addEdge(Edge(from = "payment:9821", relation = "HAS_TRANSACTION", to = "transaction:TX92"))

    println("Why is order:9821 unpaid?")
    println()

    val customer = graph.follow(from = "order:9821", relation = "CREATED_BY")
    println("Order created by: $customer")

    val transaction =
        graph.follow(
            from = "order:9821",
            relations = listOf("PAID_WITH", "HAS_TRANSACTION"),
        )
    println("Order's transaction: $transaction")
}
