package exercise.ch6_resourcehandling.ex1_bracketcase

import arrow.fx.coroutines.ExitCase
import other.model.SimpleFile

suspend fun loadFileSafely(
    fileName: String,
    completedHandler: (ExitCase.Completed) -> Unit,
    canceledHandler: (ExitCase.Cancelled) -> Unit,
    failureHandler: (ExitCase.Failure) -> Unit
): SimpleFile =
    TODO("Try to practice `bracketCase`. \n" +
            "There are `open`, `loadFile(fileName)`, `close` functions in resource `SimpleFileStorage`, " +
            "please call them when acquire, use, release the resource respectively. \n" +
            "Also, please call the handler functions corresponding to `ExitCase` before close the resource, " +
            "e.g., when `ExitCase.Completed`, call `completedHandler(exitCase)`")

