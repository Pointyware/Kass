package org.pointyware.kass.assertions.collections

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object CollectionStatements {
    fun <T> contains(item: T) = object: Statement<Collection<T>> {
        override fun evaluate(subject: Collection<T>): Boolean {
            return item in subject
        }

        override val failureMessage: String
            get() = "The collection does not contain $item"
    }

    fun <T> doesNotContain(item: T) = object: Statement<Collection<T>> {
        override fun evaluate(subject: Collection<T>): Boolean {
            return item !in subject
        }

        override val failureMessage: String
            get() = "The collection contains $item"
    }

    fun <T> isIn(items: Collection<T>) = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return subject in items
        }

        override val failureMessage: String
            get() = "The item is not in $items"
    }

    fun <T> isNotIn(items: Collection<T>) = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return subject !in items
        }

        override val failureMessage: String
            get() = "The subject is in $items"
    }
}
