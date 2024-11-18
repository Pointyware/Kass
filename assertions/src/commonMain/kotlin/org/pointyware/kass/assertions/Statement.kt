package org.pointyware.kass.assertions

import org.pointyware.kass.assertions.collections.CollectionCondition
import kotlin.test.Asserter
import kotlin.test.asserter

/**
 * Represents a statement about a subject that can be evaluated, using the given asserter.
 */
fun interface Statement<T> {
    fun evaluate(subject: T, asserter: Asserter)
}

/**
 * Evaluates the given [statement] with the given [subject] and the default asserter.
 */
fun <T:Any?> assertThat(subject: T, statement: Statement<T>) {
    statement.evaluate(subject, asserter)
}

/**
 * Evaluates the given [statement] with the given [subject] and a presumptuous asserter. When the
 * statement fails, it will throw a [FailedAssumption].
 */
fun <T:Any?> assumeThat(subject: T, statement: Statement<T>) {
    statement.evaluate(subject, presumptuousAsserter)
}

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
}

/**
 * Convenience property for creating a [StatementScope] with the default asserter.
 */
val assert: StatementScope get() {
    return StatementScope(asserter)
}

/**
 * Convenience property for creating a [StatementScope] with a presumptuous asserter. When a
 * statement fails, it will throw a [FailedAssumption].
 */
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

class FailedAssumption(msg: String?, cause: Throwable?): Exception(msg, cause)
