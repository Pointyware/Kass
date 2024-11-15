package org.pointyware.kass.assertions

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.fail

/**
 */
class ScenarioTest {
    @Test
    fun failed_assumptions_within_test_scenario_prevent_any_further_execution_and_allow_tests_to_pass() {
        runTestWith("") {
            assume().that(subject).isNotEmpty()
            fail("This should not be reached")
        }
    }

    @Test
    fun failed_assumptions_outside_test_scenario_are_not_caught_and_cause_the_test_to_fail() {
        assertFailsWith<FailedAssumption> {
            assume().that("").isNotEmpty()
        }
    }
}
