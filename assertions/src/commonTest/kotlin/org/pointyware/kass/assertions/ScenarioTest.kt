package org.pointyware.kass.assertions

import org.pointyware.kass.assertions.primitive.StringStatements.isNotEmpty
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.fail

/**
 *
 */
class ScenarioTest {

    private val emptySubjectList = listOf("")

    @Test
    fun failed_assumptions_within_test_scenario_prevent_any_further_execution_and_allow_tests_to_pass() {
        runScenarioWith(emptySubjectList) {
            assumeThat(subject, isNotEmpty())
            fail("This should not be reached")
        }
    }

    @Test
    fun failed_assumptions_within_hypothesis_prevent_any_further_execution_and_allow_tests_to_pass() {
        testHypothesis {
            assumeThat("", isNotEmpty())
            fail("This should not be reached")
        }
    }

    @Test
    fun failed_assumptions_outside_test_scenario_are_not_caught_and_cause_the_test_to_fail() {
        assertFailsWith<FailedAssumption> {
            assumeThat("", isNotEmpty())
        }
    }
}
