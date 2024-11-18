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
