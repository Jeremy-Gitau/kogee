package io.github.jeremygitau.kogee.core

import kotlin.test.Test
import kotlin.test.assertEquals

class GraphNeighborhoodTest {
    @Test
    fun `neighborhood returns all nodes reachable within maxHops`() {
        val graph = KnowledgeGraph()
        graph.addEdge(Edge(from = "order:9821", relation = "CREATED_BY", to = "customer:123"))
        graph.addEdge(Edge(from = "order:9821", relation = "PAID_WITH", to = "payment:9821"))
        graph.addEdge(Edge(from = "payment:9821", relation = "HAS_TRANSACTION", to = "transaction:TX92"))

        val result = graph.neighborhood(from = "order:9821", maxHops = 2)

        assertEquals(setOf("customer:123", "payment:9821", "transaction:TX92"), result)
    }

    @Test
    fun `neighborhood does not loop forever on a cycle`() {
        val graph = KnowledgeGraph()
        graph.addEdge(Edge(from = "a", relation = "LINKS_TO", to = "b"))
        graph.addEdge(Edge(from = "b", relation = "LINKS_TO", to = "a")) // cycle back to a

        val result = graph.neighborhood(from = "a", maxHops = 5)

        assertEquals(setOf("b"), result)
    }
}
