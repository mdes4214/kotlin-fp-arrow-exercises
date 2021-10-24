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
    TODO("Call `findInTag()` and handle the nullable value with `Option`.")

fun findInTagSafelyWithError(tag: String): Either<Error, Tag> =
    TODO("Call `findInTagSafely()` and convert the `Option` to `Either`. Map to the `TagInvalidError` if the value doesn't exist.")

fun evaluateOptionalTag(optionalTag: Option<Tag>): String =
    TODO("Evaluate the `optionalTag`. Return the string `EMPTY` if the value doesn't exist, otherwise return `toString()`.")

