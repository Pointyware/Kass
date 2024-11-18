package org.pointyware.kass.assertions.primitive

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object NumberStatements {
    fun <T:Number> isGreaterThan(value: T) = Statement<T> { subject, asserter ->
        asserter.assertTrue("$value is less than or equal to $subject", subject.toDouble() > value.toDouble())
    }
    fun <T:Number> isAtMost(value: T) = Statement<T> { subject, asserter ->
        asserter.assertTrue("$subject is greater than $value", subject.toDouble() <= value.toDouble())
    }
}
