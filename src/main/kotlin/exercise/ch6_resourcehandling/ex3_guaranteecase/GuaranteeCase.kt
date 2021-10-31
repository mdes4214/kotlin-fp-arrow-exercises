package exercise.ch6_resourcehandling.ex3_guaranteecase

import arrow.fx.coroutines.ExitCase
import arrow.fx.coroutines.guaranteeCase
import kotlinx.coroutines.delay

suspend fun taskA(exitCaseHandler: (ExitCase) -> Unit): Unit =
    guaranteeCase(
        fa = { delay(1000) },
        finalizer = { exitCase -> exitCaseHandler(exitCase) }
    )

suspend fun taskB(exitCaseHandler: (ExitCase) -> Unit): Unit =
    guaranteeCase(
        fa = { delay(1000) },
        finalizer = { exitCase -> exitCaseHandler(exitCase) }
    )

suspend fun runJobWithTasks(jobDelayTimeMS: Long): List<ExitCase> {
    val result: MutableList<ExitCase> = mutableListOf()
    val exitCaseHandler: (ExitCase) -> Unit = { result.add(it) }

    TODO("Try to create a async job in a `CoroutineScope` to run taskA and taskB in parallel. \n" +
            "Before the end of the scope, please call `delay(jobDelayTimeMS)` and then *cancel* the job. \n" +
            "hint: Try to use `parZip` to compose the two tasks.")

    return result
}
