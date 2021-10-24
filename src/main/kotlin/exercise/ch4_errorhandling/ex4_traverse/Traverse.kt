package exercise.ch4_errorhandling.ex4_traverse

import arrow.core.Either
import other.model.FileNotFoundException
import other.model.SimpleFile
import other.model.SimpleFileStorage

sealed class Error {
    data class FileNotFoundError(val msg: String) : Error()
    object ExternalError : Error()
}

fun Throwable.toDomain(): Error =
    when (this) {
        is FileNotFoundException -> Error.FileNotFoundError(this.message)
        else -> Error.ExternalError
    }

val simpleFileStorage = SimpleFileStorage()

fun findFileByFileNameSafely(fileName: String): Either<Error, SimpleFile> =
    Either.catch {
        simpleFileStorage.findFileByFileName(fileName)
    }.mapLeft { e ->
        e.toDomain()
    }

fun findFilesByFileNameInefficient(fileNames: List<String>): List<Either<Error, SimpleFile>> =
    fileNames.map { findFileByFileNameSafely(it) }

fun findFilesByFileNameEfficient(fileNames: List<String>): Either<Error, List<SimpleFile>> =
    TODO("Call `findFileByFileNameSafely()` and flatten the result, i.e., from `List<Either<E, A>>` to `Either<E, List<A>>`.")

fun evaluateFilesByFileName(fileNames: List<String>): String =
    findFilesByFileNameEfficient(fileNames).fold(
        ifLeft = { it.toString() },
        ifRight = { it.toString() }
    )