package io.github.jeremygitau.kogee.core

import kotlin.test.Test
import kotlin.test.assertEquals

class GraphTraversalTest {
    @Test
    fun `follow returns node ids reachable via a given relation`() {
        val graph = KnowledgeGraph()
        graph.addEdge(Edge(from = "order:9821", relation = "CREATED_BY", to = "customer:123"))
        graph.addEdge(Edge(from = "order:9821", relation = "PAID_WITH", to = "payment:9821"))

        val result = graph.follow(from = "order:9821", relation = "CREATED_BY")

        assertEquals(listOf("customer:123"), result)
    }

    @Test
    fun `follow chains multiple relations across hops`() {
        val graph = KnowledgeGraph()
        graph.addEdge(Edge(from = "order:9821", relation = "PAID_WITH", to = "payment:9821"))
        graph.addEdge(Edge(from = "payment:9821", relation = "HAS_TRANSACTION", to = "transaction:TX92"))

        val result = graph.follow(from = "order:9821", relations = listOf("PAID_WITH", "HAS_TRANSACTION"))

        assertEquals(listOf("transaction:TX92"), result)
    }

    @Test
    fun `follow returns an empty list when no edge matches the relation`() {
        val graph = KnowledgeGraph()
        graph.addEdge(Edge(from = "order:9821", relation = "CREATED_BY", to = "customer:123"))

        val result = graph.follow(from = "order:9821", relation = "PAID_WITH")

        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `follow returns an empty list from a node with no edges`() {
        val graph = KnowledgeGraph()

        val result = graph.follow(from = "order:does-not-exist", relation = "CREATED_BY")

        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `chained follow returns empty when a hop in the middle has no matching edge`() {
        val graph = KnowledgeGraph()
        graph.addEdge(Edge(from = "order:9821", relation = "PAID_WITH", to = "payment:9821"))
        // no HAS_TRANSACTION edge exists from payment:9821

        val result = graph.follow(from = "order:9821", relations = listOf("PAID_WITH", "HAS_TRANSACTION"))

        assertEquals(emptyList<String>(), result)
    }
}
