package exercise.ch6_resourcehandling.ex1_bracketcase

import arrow.fx.coroutines.ExitCase
import arrow.fx.coroutines.bracketCase
import other.model.SimpleFile
import other.model.SimpleFileStorage

suspend fun loadFileSafely(
    fileName: String,
    completedHandler: (ExitCase.Completed) -> Unit,
    canceledHandler: (ExitCase.Cancelled) -> Unit,
    failureHandler: (ExitCase.Failure) -> Unit
): SimpleFile =
    bracketCase(
        acquire = { SimpleFileStorage.open() },
        use = { db -> db.loadFile(fileName) },
        release = { db, exitCase ->
            when(exitCase) {
                is ExitCase.Completed -> { completedHandler(exitCase) }
                is ExitCase.Cancelled -> { canceledHandler(exitCase) }
                is ExitCase.Failure -> { failureHandler(exitCase) }
            }
            db.close()
        }
    )