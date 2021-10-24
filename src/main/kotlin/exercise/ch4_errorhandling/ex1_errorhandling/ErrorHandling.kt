package exercise.ch4_errorhandling.ex1_errorhandling

import arrow.core.Either
import arrow.core.right
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
    Either.catch {
        simpleFileStorage.findFileByFileName(fileName)
    }.mapLeft { e ->
        e.toDomain()
    }

fun findTagByFileNameSafely(fileName: String): Either<Error, String> =
    Either.fromNullable(simpleFileStorage.findTagByFileName(fileName)).mapLeft { Error.NullTagError }
// [Advanced]
//    Either.catch {
//        simpleFileStorage.findTagByFileName(fileName)
//    }.mapLeft { e ->
//        e.toDomain()
//    }.flatMap { tag ->
//        Either.fromNullable(tag).mapLeft { Error.NullTagError }
//    }

fun findTagByFileNameSafely(fileName: String, defaultTag: String): Either<Error, String> =
    findTagByFileNameSafely(fileName).fold(
        ifLeft = { defaultTag.right() },
        ifRight = { it.right() }
    )
//    findTagByFileNameSafely(fileName).handleError { defaultTag }
// [Advanced]
//    findTagByFileNameSafely(fileName).fold(
//        ifLeft = { e ->
//            when(e) {
//                is Error.NullTagError -> defaultTag.right()
//                else -> e.left()
//            }
//        },
//        ifRight = { it.right() }
//    )

// *** TODO section END ***