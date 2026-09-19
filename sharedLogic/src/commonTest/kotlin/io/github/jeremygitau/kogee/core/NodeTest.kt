package io.github.jeremygitau.kogee.core

import kotlin.test.Test
import kotlin.test.assertEquals

class NodeTest {
    @Test
    fun `node stores its id and value`() {
        val node = Node(id = "node1", type = "value1")
        assertEquals("node1", node.id)
        assertEquals("value1", node.type)
    }

    @Test
    fun `node can store and retrieve a property`() {
        val node = Node(id = "payment:9821", type = "Payment", properties = mapOf("status" to "failed"))

        assertEquals("failed", node.properties["status"])
    }
}
