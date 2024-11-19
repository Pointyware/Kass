# Kass
Kotlin Common Assertions

## Platform Support
Kass is a multiplatform library, supporting JVM, Android, iOS (x64, ARM, Simulator-ARM), JS.

## Usage
`assertThat(subject, statement)` and `assumeThat(subject, statement)` both evaluate the given subject against a given statement, which can be written in a fluent style using the provided statement functions.

When an assumption fails, the test execution will stop at that point, throwing a `FailedAssumption` error.
To catch this error and fail silently, use `testScenario(subjects)` which takes a collection that 
provides subjects and a lambda executed on a Scenario object that wraps each subject.
Use `testHypothesis()` to run a single test without an implicit subject.

When an assertion fails, tests fail normally.


```kotlin

@Test
fun some_hypothesis() = testHypothesis() {
    // Given a non-null subject
    val given = "a"
    assumeThat(given, isNotNull())
    
    // When some operation is performed
    val result = lengthOperation(given)
    
    // Then the result is as expected
    assertThat(result, isEqualTo(1))
}

private val subjectList = listOf(null, "a", "b", "c")

@Test
fun some_scenario() = runScenario(subjectList) {
    // Given a non-null subject
    assumeThat(subject, isNotNull())
    
    // When some operation is performed
    val result = someOperation(subject)
    
    // Then the result is as expected
    assertThat(result, isEqualTo(expectedValue))
}

```
