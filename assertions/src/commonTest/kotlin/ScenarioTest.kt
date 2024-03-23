package org.pointyware.kass.assertions

import org.pointyware.kass.assertions.FailedAssumption
import org.pointyware.kass.assertions.assume
import org.pointyware.kass.assertions.runTestWith
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.fail

/**
 */
class ScenarioTest {
    @Test
    fun `failed assumptions within test scenario prevent any further execution and allow tests to pass`() {
        runTestWith("") {
            assume().that(subject).isNotEmpty()
            fail("This should not be reached")
        }
    }

    @Test
    fun `failed assumptions outside test scenario are not caught and cause the test to fail`() {
        assertFailsWith<FailedAssumption> {
            assume().that("").isNotEmpty()
        }
    }
}
