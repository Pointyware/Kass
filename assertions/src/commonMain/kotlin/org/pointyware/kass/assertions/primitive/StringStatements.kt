package org.pointyware.kass.assertions.primitive

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object StringStatements {
    fun isNotEmpty() = object: Statement<String> {
        override fun evaluate(subject: String): Boolean {
            return subject.isNotEmpty()
        }

        override val failureMessage: String
            get() = "String is empty"
    }
}
