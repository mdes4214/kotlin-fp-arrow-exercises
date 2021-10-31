package exercise.ch6_resourcehandling.ex2_resource

import arrow.fx.coroutines.ExitCase
import other.model.SimpleFile

suspend fun loadFilesSafely(
    fileNames: List<String>,
    completedHandler: (ExitCase.Completed) -> Unit,
    canceledHandler: (ExitCase.Cancelled) -> Unit,
    failureHandler: (ExitCase.Failure) -> Unit
): List<SimpleFile> {
    TODO("Try to practice `Resource`." +
            "Same as ex1_bracketcase, but this time we have several files need to find by a list of file name. \n" +
            "hint: Try to use `parTraverse` of `List` when use the resource.")
}