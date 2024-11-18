package org.pointyware.kass.assertions.objects

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object ObjectStatements {
    fun <T> isEqualTo(other: T) = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return other == subject
        }

        override val failureMessage: String
            get() = "The subject is not equal to $other"
    }

    fun <T> isNotEqualTo(other: T) = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return other != subject
        }

        override val failureMessage: String
            get() = "The subject is equal to $other"
    }

    fun <T> isNull() = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return subject == null
        }

        override val failureMessage: String
            get() = "The subject is not null"
    }

    fun <T> isNotNull() = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return subject != null
        }

        override val failureMessage: String
            get() = "The subject is null"
    }
}
