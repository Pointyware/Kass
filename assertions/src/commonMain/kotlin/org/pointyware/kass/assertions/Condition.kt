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

    open fun and(): Condition<T> {
        return this
    }
}
