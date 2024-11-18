package org.pointyware.kass.assertions.primitive

import org.pointyware.kass.assertions.Condition
import org.pointyware.kass.assertions.Statement
import kotlin.test.Asserter

/**
 *
 */
object StringStatements {
    fun isNotEmpty() = Statement<String> { subject, asserter ->
        asserter.assertTrue("String is empty", subject.isNotEmpty())
    }
}
