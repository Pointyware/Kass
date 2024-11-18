package org.pointyware.kass.assertions.primitive

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object NumberStatements {
    fun <T:Number> isGreaterThan(value: T) = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return subject.toDouble() > value.toDouble()
        }

        override val failureMessage: String
            get() = "The subject is less than or equal to $value"
    }
    fun <T:Number> isAtMost(value: T) = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return subject.toDouble() <= value.toDouble()
        }

        override val failureMessage: String
            get() = "The subject is greater than $value"
    }
}
