package org.pointyware.kass.assertions.collections

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object MapStatements {
    fun <K, V> containsKey(key: K) = object: Statement<Map<K, V>> {
        override fun evaluate(subject: Map<K, V>): Boolean {
            return key in subject
        }

        override val failureMessage: String
            get() = "The subject does not contain key $key"
    }

    fun <K, V> doesNotContainKey(key: K) = object: Statement<Map<K, V>> {
        override fun evaluate(subject: Map<K, V>): Boolean {
            return key !in subject
        }

        override val failureMessage: String
            get() = "The subject contains key $key"
    }
}
