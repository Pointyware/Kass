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

    fun <T:Number> isLessThan(other: T) = object: Statement<T> {
        override val failureMessage: String
            get() = "The subject is greater than or equal to $other."

        override fun evaluate(subject: T): Boolean {
            return subject.toDouble() < other.toDouble()
        }
    }

    fun <T:Number> isAtLeast(value: T) = object: Statement<T> {
        override fun evaluate(subject: T): Boolean {
            return subject.toDouble() >= value.toDouble()
        }

        override val failureMessage: String
            get() = "The subject is less than $value"
    }

    fun isEven() = object: Statement<Int> {
        override val failureMessage: String
            get() = "Subject is not even."

        override fun evaluate(subject: Int): Boolean {
            return subject % 2 == 0
        }
    }

    fun isOdd() = object: Statement<Int> {
        override val failureMessage: String
            get() = "Subject is not odd."

        override fun evaluate(subject: Int): Boolean {
            return subject % 2 != 0
        }
    }

    fun isDividedBy(divisor: Int) = object: Statement<Int> {
        override val failureMessage: String
            get() = "Subject is not evenly divided by $divisor."

        override fun evaluate(subject: Int): Boolean {
            return subject % divisor == 0
        }
    }
}
