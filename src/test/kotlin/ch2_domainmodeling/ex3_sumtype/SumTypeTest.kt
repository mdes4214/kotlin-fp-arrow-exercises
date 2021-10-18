package ch2_domainmodeling.ex3_sumtype

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SumTypeTest {
    @Test
    fun testProcessState() {
        var callbackCalled = false
        val cancelled = State.Cancelled
        processState(
            cancelled,
            processCancelled = { callbackCalled = true },
            processError = {},
            processSuccess = {}
        )

        Assertions.assertTrue(callbackCalled, "Callback is not called for Cancelled state")

        callbackCalled = false
        val error = State.Error(RuntimeException("Boom!"))
        processState(
            error,
            processCancelled = {},
            processError = { callbackCalled = true },
            processSuccess = {}
        )

        Assertions.assertTrue(callbackCalled, "Callback is not called for Error state")

        callbackCalled = false
        val success = State.Success("Some data")
        processState(
            success,
            processCancelled = {},
            processError = {},
            processSuccess = { callbackCalled = true }
        )

        Assertions.assertTrue(callbackCalled, "Callback is not called for Success state")
    }
}