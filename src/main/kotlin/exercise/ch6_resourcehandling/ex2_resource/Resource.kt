package exercise.ch6_resourcehandling.ex2_resource

import arrow.fx.coroutines.ExitCase
import arrow.fx.coroutines.Resource
import arrow.fx.coroutines.parTraverse
import other.model.SimpleFile
import other.model.SimpleFileStorage

suspend fun loadFilesSafely(
    fileNames: List<String>,
    completedHandler: (ExitCase.Completed) -> Unit,
    canceledHandler: (ExitCase.Cancelled) -> Unit,
    failureHandler: (ExitCase.Failure) -> Unit
): List<SimpleFile> {
    val resource = Resource(
        acquire = { SimpleFileStorage.open() },
        release = { db, exitCase ->
            when(exitCase) {
                is ExitCase.Completed -> { completedHandler(exitCase) }
                is ExitCase.Cancelled -> { canceledHandler(exitCase) }
                is ExitCase.Failure -> { failureHandler(exitCase) }
            }
            db.close()
        }
    )

    return fileNames.parTraverse { fileName -> resource.use { db -> db.loadFile(fileName) } }
}