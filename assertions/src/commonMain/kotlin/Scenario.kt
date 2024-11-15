package org.pointyware.kass.assertions

/**
 * Represents a scenario in which a subject is tested.
 */
data class Scenario<T: Any>(
    val subject: T
)

/**
 * Creates a new test scenario for the given subject, executing the
 * given test block. Catches any [FailedAssumption], skipping the test, and
 * re-throws any other exception to allow normal test failure handling.
 */
fun <T: Any> runScenarioWith(subject: T, testCase: Scenario<T>.() -> Unit) {
    Scenario(subject).also {
        testHypothesis { it.testCase() }
    }
}

/**
 * Tests a hypothesis expressed as assumptions and assertions.
 * Catches any [FailedAssumption], skipping the test, and
 * re-throws any other exception to allow normal test failure handling.
 */
fun testHypothesis(testCase: () -> Unit) {
    try {
        testCase()
    } catch (e: FailedAssumption) {
        println("Assumption failed: ${e.message}")
        println(e.stackTraceToString())
    } catch (e: Throwable) {
        throw e
    }
}
