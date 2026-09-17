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
}
