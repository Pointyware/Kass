package org.pointyware.kass.assertions.result

import org.pointyware.kass.assertions.Condition
import org.pointyware.kass.assertions.Statement
import kotlin.test.Asserter

/**
 *
 */
object ResultStatements {
    fun <T:Any> isSuccess() = Statement<Result<T>> { subject, asserter ->
        asserter.assertTrue("Expected success, but was failure", subject.isSuccess)
    }

    fun <T:Any> isFailure() = Statement<Result<T>> { subject, asserter ->
        asserter.assertTrue("Expected failure, but was success", subject.isFailure)
    }
}
