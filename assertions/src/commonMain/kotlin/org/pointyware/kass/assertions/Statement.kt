package org.pointyware.kass.assertions

import kotlin.test.Asserter
import kotlin.test.asserter

/**
 * Represents a statement that can be evaluated against a subject. The statement also provides a
 * [failureMessage] in case the evaluation returns false.
 */
interface Statement<T> {
    fun evaluate(subject: T): Boolean
    val failureMessage: String
}

/**
 * Combines the receiver statement with [other] to create a new statement that evaluates both.
 */
fun <T> Statement<T>.and(other: Statement<T>) = object: Statement<T> {

    override fun evaluate(subject: T): Boolean {
        return this@and.evaluate(subject) && other.evaluate(subject)
    }

    override val failureMessage: String
        get() = this@and.failureMessage + " or " + other.failureMessage
}

private inline fun <T> throwOnEvaluationFail(subject: T, statement: Statement<T>, asserter: Asserter) {
    if (statement.evaluate(subject).not()) {
        asserter.fail(statement.failureMessage)
    }
}

/**
 * Evaluates the given [statement] with the given [subject] and the default asserter.
 */
fun <T:Any?> assertThat(subject: T, statement: Statement<T>) {
    throwOnEvaluationFail(subject, statement, asserter)
}

/**
 * Evaluates the given [statement] with the given [subject] and a presumptuous asserter. When the
 * statement fails, it will throw a [FailedAssumption].
 */
fun <T:Any?> assumeThat(subject: T, statement: Statement<T>) {
    throwOnEvaluationFail(subject, statement, presumptuousAsserter)
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
