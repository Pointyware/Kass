package org.pointyware.kass.assertions.result

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object ResultStatements {
    fun <T:Any> isSuccess() = object: Statement<Result<T>> {
        override fun evaluate(subject: Result<T>): Boolean {
            return subject.isSuccess
        }

        override val failureMessage: String
            get() = "Expected success, but was failure"
    }

    fun <T:Any> isFailure() = object: Statement<Result<T>> {
        override fun evaluate(subject: Result<T>): Boolean {
            return subject.isFailure
        }

        override val failureMessage: String
            get() = "Expected failure, but was success"
    }
}
