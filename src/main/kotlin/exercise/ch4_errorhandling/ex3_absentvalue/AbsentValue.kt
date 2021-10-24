package exercise.ch4_errorhandling.ex3_absentvalue

import arrow.core.Either
import arrow.core.Option
import other.model.Tag

sealed class Error {
    sealed class ValidationError : Error() {
        object TagInvalidError : ValidationError()
    }
}

fun findInTag(tag: String): Tag? =
    Tag.values().find { it.name == tag }

fun findInTagSafely(tag: String): Option<Tag> =
    Option.fromNullable(findInTag(tag))

fun findInTagSafelyWithError(tag: String): Either<Error, Tag> =
    findInTagSafely(tag).toEither(
        ifEmpty = { Error.ValidationError.TagInvalidError }
    )

fun evaluateOptionalTag(optionalTag: Option<Tag>): String =
    optionalTag.fold(
        ifEmpty = { "EMPTY" },
        ifSome = { it.toString() }
    )
