package io.github.jeremygitau.kogee.core

import kotlin.test.Test
import kotlin.test.assertEquals

class EdgeTest {
    @Test
    fun `edge connects a from node to a to node with a relation`() {
        val edge = Edge(from = "order:9821", relation = "CREATED_BY", to = "customer:123")

        assertEquals("order:9821", edge.from)
        assertEquals("CREATED_BY", edge.relation)
        assertEquals("customer:123", edge.to)
    }
}
