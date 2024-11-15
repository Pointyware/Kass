# Kass
Kotlin Common Assertions

## Platform Support
Kass is a multiplatform library, supporting JVM, Android, iOS (x64, ARM, Simulator-ARM), JS.

## Usage
`assert()` and `assume()` both return objects that allow you to write expectations in a fluent style.
`assert()` returns an `Assertion` object, while `assume()` returns an `Assumption` object.
When an assumption fails, the test execution will stop at that point, throwing a `FailedAssumption` error.
To catch this error and fail silently, use the `runTestWith(subject)` which takes a Scenario.

When an assertion fails, tests fail normally.


```kotlin

@Test
fun testWithFailingAssumption() = runTestWith("") {
    // Given a non-null subject
    assume().that(subject).isNotNull()
    
    // When some operation is performed
    val result = someOperation(subject)
    
    // Then the result is as expected
    assert().that(result).isEqualTo(expectedValue)
}

```
