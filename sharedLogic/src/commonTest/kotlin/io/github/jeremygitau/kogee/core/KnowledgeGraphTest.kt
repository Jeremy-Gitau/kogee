package io.github.jeremygitau.kogee.core

import kotlin.test.Test
import kotlin.test.assertEquals

class KnowledgeGraphTest {
    @Test
    fun `graph returns a node that was added to it`() {
        val graph = KnowledgeGraph()
        val node = Node(id = "order:9821", type = "Order")

        graph.addNode(node)

        assertEquals(node, graph.getNode("order:9821"))
    }

    @Test
    fun `graph returns edges added from a given node`() {
        val graph = KnowledgeGraph()
        val edge = Edge(from = "order:9821", relation = "CREATED_BY", to = "customer:123")

        graph.addEdge(edge)

        assertEquals(listOf(edge), graph.getEdgesFrom("order:9821"))
    }

    @Test
    fun `remember stores a node the same way addNode does`() {
        val graph = KnowledgeGraph()
        val node = Node(id = "order:9821", type = "Order")

        graph.remember(node)

        assertEquals(node, graph.getNode("order:9821"))
    }
}
