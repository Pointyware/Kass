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

/**
 * Extends [Condition] to provide additional statements for strings.
 */
class StringCondition(
    override val subject: String,
    asserter: Asserter
): Condition<String>(subject, asserter) {
    fun isNotEmpty(): StringCondition {
        asserter.assertTrue("String is empty", subject.isNotEmpty())
        return this
    }
}

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

/**
 * Extends [Condition] to provide additional statements for results.
 */
class ResultCondition<T>(
    override val subject: Result<T>,
    asserter: Asserter
): Condition<Result<T>>(subject, asserter) {
    fun isFailure(): ResultCondition<T> {
        asserter.assertTrue("Result is not a failure", subject.isFailure)
        return this
    }

    fun isSuccess(): ResultCondition<T> {
        asserter.assertTrue("Result is not a success", subject.isSuccess)
        return this
    }

    override fun and(): ResultCondition<T> {
        return this
    }
}
