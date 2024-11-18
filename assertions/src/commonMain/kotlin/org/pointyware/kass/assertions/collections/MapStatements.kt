package org.pointyware.kass.assertions.collections

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object MapStatements {
    fun <K, V> containsKey(key: K) = Statement<Map<K, V>> { subject, asserter ->
        asserter.assertTrue("$subject does not contain key $key", key in subject)
    }

    fun <K, V> doesNotContainKey(key: K) = Statement<Map<K, V>> { subject, asserter ->
        asserter.assertTrue("$subject contains key $key", key !in subject)
    }
}
