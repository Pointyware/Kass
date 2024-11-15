# Kass
Kotlin Common Assertions

## Platform Support
Kass is a multiplatform library, supporting JVM, Android, iOS (x64, ARM, Simulator-ARM), JS.

## Usage
`assert()` and `assume()` both return objects that allow you to write expectations in a fluent style.
`assert()` returns an `Assertion` object, while `assume()` returns an `Assumption` object.
When an assumption fails, the test execution will stop at that point, throwing a `FailedAssumption` error.
To catch this error and fail silently, use the `runScenario(subject)` which takes a collection that 
provides subjects and a lambda executed on a Scenario object that wraps each subject.
Use testHypothesis() to run a single test without an implicit subject.

When an assertion fails, tests fail normally.


```kotlin

@Test
fun some_hypothesis() = testHypothesis() {
    // Given a non-null subject
    val given = "a"
    assume().that(given).isNotNull()
    
    // When some operation is performed
    val result = lengthOperation(given)
    
    // Then the result is as expected
    assert().that(result).isEqualTo(1)
}

private val subjectList = listOf(null, "a", "b", "c")

@Test
fun some_scenario() = runScenario(subjectList) {
    // Given a non-null subject
    assume().that(subject).isNotNull()
    
    // When some operation is performed
    val result = someOperation(subject)
    
    // Then the result is as expected
    assert().that(result).isEqualTo(expectedValue)
}

```
