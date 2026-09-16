package io.github.jeremygitau.kogee.core

import kotlin.test.Test
import kotlin.test.assertEquals

class NodeTest {
    @Test
    fun `node stores its id and value`() {
        val node = Node(id = "node1", value = "value1")
        assertEquals("node1", node.id)
        assertEquals("value1", node.value)
    }
}
