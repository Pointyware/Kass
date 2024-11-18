package org.pointyware.kass.assertions

import org.pointyware.kass.assertions.collections.CollectionCondition
import kotlin.test.Asserter
import kotlin.test.asserter

/**
 * Represents a scope in which statements about a subject can be made.
 */
data class StatementScope(
    val asserter: Asserter
) {
    fun <T: Any?> that(subject: T): Condition<T> {
        return Condition(subject, asserter)
    }
    fun <N: Number> that(subject: N): NumberCondition<N> {
        return NumberCondition(subject, asserter)
    }
    fun that(subject: String): StringCondition {
        return StringCondition(subject, asserter)
    }
    fun <E> that(subject: Collection<E>): CollectionCondition<E> {
        return CollectionCondition(subject, asserter)
    }
    fun <E> that(subject: Result<E>): ResultCondition<E> {
        return ResultCondition(subject, asserter)
    }
}

val assert: StatementScope get() {
    return StatementScope(asserter)
}

val assume: StatementScope get() {
    return StatementScope(presumptuousAsserter)
}

private val presumptuousAsserter = object: Asserter {
    override fun fail(message: String?): Nothing {
        throw FailedAssumption("Assumption failed - $message", null)
    }

    override fun fail(message: String?, cause: Throwable?): Nothing {
        throw FailedAssumption("Assumption failed - $message", cause)
    }
}

class FailedAssumption(msg: String?, cause: Throwable?): Throwable(msg, cause)
