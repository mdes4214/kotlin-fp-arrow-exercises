package exercise.ch4_errorhandling.ex1_errorhandling

import arrow.core.Either
import other.model.FileNotFoundException
import other.model.SimpleFile
import other.model.SimpleFileStorage

sealed class Error {
    data class FileNotFoundError(val msg: String) : Error()
    object NullTagError : Error()
    object ExternalError : Error()
}

fun Throwable.toDomain(): Error =
    when (this) {
        is FileNotFoundException -> Error.FileNotFoundError(this.message)
        else -> Error.ExternalError
    }

val simpleFileStorage = SimpleFileStorage()

// TODO:
//  Complete these three functions, then we can safely find the SimpleFile by specific file name and update the tag if it's invalid.
// *** TODO section START ***

fun findFileByFileNameSafely(fileName: String): Either<Error, SimpleFile> =
    TODO("`simpleFileStorage.findFileByFileName(fileName)` may cause exception we don't want to see. " +
            "Try to handle it and return `Either<Error, SimpleFile>`.")
    // hint:
    //  1. Try `Either.catch {}` and `mapLeft {}`.
    //  2. Remember to map the exception to domain error with `toDomain()`.

fun findTagByFileNameSafely(fileName: String): Either<Error, String> =
    TODO("`simpleFileStorage.findTagByFileName(fileName)` may return null `tag`. " +
            "Try to handle it and return `Either<Error, String>`.")
    // hint:
    //  1. Try `Either.fromNullable {}` and `mapLeft {}`.
    //  2. The null value should be mapped to `Error.NullTagError`
    //  3. [Advanced] What will happen when file is not found?

fun findTagByFileNameSafely(fileName: String, defaultTag: String): Either<Error, String> =
    TODO("Mostly the same as above, but this function needs to return the `defaultTag` if the `tag` we found is null.")
    // hint:
    //  1. Call the above function `findTagByFileNameSafely(fileName)`
    //  2. Try `fold()` to evaluate the `Either` of tag.
    //  3. Return `defaultTag` if `NullTagError` happened.
    //  4. [Advanced] What will happen when file is not found?

// *** TODO section END ***