package org.pointyware.kass.assertions.primitive

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object StringStatements {
    fun isNotEmpty() = Statement<String> { subject, asserter ->
        asserter.assertTrue("String is empty", subject.isNotEmpty())
    }
}
