package exercise.ch6_resourcehandling.ex3_guaranteecase

import arrow.fx.coroutines.ExitCase
import arrow.fx.coroutines.guaranteeCase
import arrow.fx.coroutines.parZip
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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

    coroutineScope {
        val job = async {
            parZip(
                { taskA(exitCaseHandler) },
                { taskB(exitCaseHandler) }
            ) { _, _ -> }
        }

        delay(jobDelayTimeMS)
        job.cancel()
    }

    return result
}