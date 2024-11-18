package org.pointyware.kass.assertions.collections

import org.pointyware.kass.assertions.Condition
import kotlin.test.Asserter

/**
 * Extends [Condition] to provide additional statements for maps.
 */
class MapCondition<K, V>(
    override val subject: Map<K, V>,
    asserter: Asserter
): Condition<Map<K, V>>(subject, asserter) {
    fun containsKey(key: K): MapCondition<K, V> {
        asserter.assertTrue("$subject does not contain key $key", key in subject)
        return this
    }

    fun doesNotContainKey(key: K): MapCondition<K, V> {
        asserter.assertTrue("$subject contains key $key", key !in subject)
        return this
    }

    override fun and(): MapCondition<K, V> {
        return this
    }
}
