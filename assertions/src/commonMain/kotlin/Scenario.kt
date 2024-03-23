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
fun <T: Any> runTestWith(subject: T, testCase: Scenario<T>.() -> Unit) {
    Scenario(subject).also {
        try {
            it.testCase()
        } catch (e: FailedAssumption) {
            println("Assumption failed: ${e.message}")
            println(e.stackTraceToString())
        } catch (e: Throwable) {
            throw e
        }
    }
}
