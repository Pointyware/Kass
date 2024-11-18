package org.pointyware.kass.assertions

import kotlin.test.Asserter

/**
 * Represents subject-based statements about some system that can be used to establish
 * pre-conditions, post-conditions, and invariants.
 */
open class Condition<T>(
    open val subject: T,
    val asserter: Asserter
) {
    fun isEqualTo(other: T): Condition<T> {
        asserter.assertEquals("$subject is not equal to $other", other, subject)
        return this
    }
    fun isNotEqualTo(other: T): Condition<T> {
        asserter.assertNotEquals("$subject is equal to $other", other, subject)
        return this
    }
    fun isIn(items: Collection<T>): Condition<T> {
        asserter.assertTrue("$subject is not in $items", subject in items)
        return this
    }
    fun isNotIn(items: Collection<T>): Condition<T> {
        asserter.assertTrue("$subject is in $items", subject !in items)
        return this
    }
    fun isNull(): Condition<T> {
        asserter.assertNull("$subject is not null", subject)
        return this
    }
    fun isNotNull(): Condition<T> {
        asserter.assertNotNull("$subject is null", subject)
        return this
    }

    open fun and(): Condition<T> {
        return this
    }
}

/**
 * Extends [Condition] to provide additional statements for numbers.
 */
open class NumberCondition<T: Number>(
    override val subject: T,
    asserter: Asserter
): Condition<T>(subject, asserter) {
    fun isGreaterThan(value: T): NumberCondition<T> {
        asserter.assertTrue("$subject is not greater than $value", subject.toDouble() > value.toDouble())
        return this
    }
    fun isAtMost(value: T): NumberCondition<T> {
        asserter.assertTrue("$subject is not at most $value", subject.toDouble() <= value.toDouble())
        return this
    }

    override fun and(): NumberCondition<T> {
        return this
    }
}
