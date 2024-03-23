import kotlinx.coroutines.delay

/**
 * Simulates a delay in the execution of the block by suspending the coroutine
 * for the given number of milliseconds before executing the block.
 */
suspend fun <T> withSimulatedLatency(delayMillis: Long, block: () -> T): T {
    delay(delayMillis)
    return block()
}
