package org.pointyware.kass.assertions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 *
 */
class StatementUnitTest {

    @Test
    fun and_should_pass_only_if_both_statements_pass() {
        // Given two statements combined with and
        //  And both statements pass
        val statement1 = object: Statement<Int> {
            override fun evaluate(subject: Int) = true
            override val failureMessage = "Statement 1 failed"
        }
        val statement2 = object: Statement<Int> {
            override fun evaluate(subject: Int) = true
            override val failureMessage = "Statement 2 failed"
        }
        val combined = statement1.and(statement2)

        // When the combination is evaluated
        val result = combined.evaluate(0)

        // Then the evaluation should pass
        //  And the result should be true
        assertTrue(result)
    }

    @Test
    fun and_should_not_pass_if_first_statement_fails() {
        // Given two statements combined with and
        //  And the first statement fails
        val statement1 = object: Statement<Int> {
            override fun evaluate(subject: Int) = false
            override val failureMessage = "Statement 1 failed"
        }
        val statement2 = object: Statement<Int> {
            override fun evaluate(subject: Int) = true
            override val failureMessage = "Statement 2 failed"
        }
        val combined = statement1.and(statement2)

        // When the combination is evaluated
        val result = combined.evaluate(0)

        // Then the evaluation should fail
        //  And the failure message should contain the failure message of both statements
        assertFalse(result)
        assertEquals("Statement 1 failed or Statement 2 failed", combined.failureMessage)
    }

    @Test
    fun and_should_not_pass_if_second_statement_fails() {
        // Given two statements combined with and
        //  And the second statement fails
        val statement1 = object: Statement<Int> {
            override fun evaluate(subject: Int) = true
            override val failureMessage = "Statement 1 failed"
        }
        val statement2 = object: Statement<Int> {
            override fun evaluate(subject: Int) = false
            override val failureMessage = "Statement 2 failed"
        }
        val combined = statement1.and(statement2)

        // When the combination is evaluated
        val result = combined.evaluate(0)

        // Then the evaluation should fail
        //  And the failure message should contain the failure message of both statements
        assertFalse(result)
        assertEquals("Statement 1 failed or Statement 2 failed", combined.failureMessage)
    }

    @Test
    fun assertThat_should_pass_when_statement_passes() {
        // Given a subject and a statement that passes
        val statementFake = object: Statement<Int> {
            override fun evaluate(subject: Int) = true
            override val failureMessage = "Statement failed"
        }

        // When the statement is evaluated with the subject
        assertThat(0, statementFake)

        // Then the evaluation should pass
    }

    @Test
    fun assertThat_should_fail_when_statement_fails() {
        // Given a subject and a statement that fails
        val statementFake = object: Statement<Int> {
            override fun evaluate(subject: Int) = false
            override val failureMessage = "Statement failed"
        }

        // When the statement is evaluated with the subject
        val error = assertFailsWith(AssertionError::class) {
            assertThat(0, statementFake)
        }

        // Then the evaluation should fail
        //  And the message of the exception should contain the failure message of the statement
        assertEquals("Statement failed", error.message)
    }

    @Test
    fun assumeThat_should_pass_when_statement_passes() {
        // Given a subject and a statement that passes
        val statementFake = object: Statement<Int> {
            override fun evaluate(subject: Int) = true
            override val failureMessage = "Statement failed"
        }

        // When the statement is evaluated with the subject
        assumeThat(0, statementFake)

        // Then the evaluation should pass
    }

    @Test
    fun assumeThat_should_throw_FailedAssumption_when_statement_fails() {
        // Given a subject and a statement that fails
        val statementFake = object: Statement<Int> {
            override fun evaluate(subject: Int) = false
            override val failureMessage = "Statement failed"
        }

        // When the statement is evaluated with the subject
        val error = assertFailsWith<FailedAssumption> {
            assumeThat(0, statementFake)
        }

        // Then the evaluation should throw a FailedAssumption
        //  And the message of the exception should contain the failure message of the statement
        assertEquals("Assumption failed - Statement failed", error.message)
    }
}
