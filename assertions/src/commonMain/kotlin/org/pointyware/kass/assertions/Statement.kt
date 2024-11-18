package org.pointyware.kass.assertions

import kotlin.test.Asserter
import kotlin.test.asserter

/**
 * Represents a statement about a subject that can be evaluated, using the given asserter.
 */
fun interface Statement<T> {
    fun evaluate(subject: T, asserter: Asserter)
}

/**
 * Combines the receiver statement with [other] to create a new statement that evaluates both.
 */
fun <T> Statement<T>.and(other: Statement<T>) = Statement<T> { subject, asserter ->
    evaluate(subject, asserter)
    other.evaluate(subject, asserter)
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

private val presumptuousAsserter = object: Asserter {
    override fun fail(message: String?): Nothing {
        throw FailedAssumption("Assumption failed - $message", null)
    }

    override fun fail(message: String?, cause: Throwable?): Nothing {
        throw FailedAssumption("Assumption failed - $message", cause)
    }
}

class FailedAssumption(msg: String?, cause: Throwable?): Exception(msg, cause)
