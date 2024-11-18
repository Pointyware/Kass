package org.pointyware.kass.assertions.objects

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object ObjectStatements {
    fun <T> isEqualTo(other: T) = Statement<T> { subject, asserter ->
        asserter.assertEquals("$subject is not equal to $other", other, subject)
    }

    fun <T> isNotEqualTo(other: T) = Statement<T> { subject, asserter ->
        asserter.assertNotEquals("$subject is equal to $other", other, subject)
    }

    fun <T> isNull() = Statement<T> { subject, asserter ->
        asserter.assertNull("$subject is not null", subject)
    }

    fun <T> isNotNull() = Statement<T> { subject, asserter ->
        asserter.assertNotNull("$subject is null", subject)
    }
}
