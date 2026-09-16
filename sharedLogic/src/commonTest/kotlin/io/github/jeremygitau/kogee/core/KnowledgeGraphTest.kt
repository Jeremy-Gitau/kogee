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
}
