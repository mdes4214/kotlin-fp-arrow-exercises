package exercise.ch4_errorhandling.ex2_erroraccumulation

import arrow.core.ValidatedNel
import arrow.core.invalidNel
import arrow.core.validNel
import other.model.SimpleFile
import other.model.Tag

sealed class Error {
    sealed class ValidationError : Error() {
        object TagInvalidError : ValidationError()
        object AuthorInvalidError : ValidationError()
        object ExtensionInvalidError : ValidationError()
        object NameInvalidError : ValidationError()
    }
}

fun validateTag(tag: String): ValidatedNel<Error.ValidationError.TagInvalidError, String> =
    if (!Tag.values().any { it.name == tag }) {
        Error.ValidationError.TagInvalidError.invalidNel()
    } else {
        tag.validNel()
    }

fun validateAuthor(author: String): ValidatedNel<Error.ValidationError.AuthorInvalidError, String> =
    if (!author.matches("^[a-zA-Z ]*$".toRegex()) || author.isBlank()) {
        Error.ValidationError.AuthorInvalidError.invalidNel()
    } else {
        author.validNel()
    }

fun validateExtension(extension: String): ValidatedNel<Error.ValidationError.ExtensionInvalidError, String> =
    if (!extension.startsWith(".")) {
        Error.ValidationError.ExtensionInvalidError.invalidNel()
    } else {
        extension.validNel()
    }

fun validateName(name: String): ValidatedNel<Error.ValidationError.NameInvalidError, String> =
    TODO("Invalidate the `name` if it's blank, otherwise it's valid. This is similar with above validation functions.")

fun validateInputFields(name: String, extension: String, author: String, tag: String): ValidatedNel<Error.ValidationError, SimpleFile> =
    TODO("`zip` all the validated result with above four validation functions.")

fun evaluateValidatedName(validatedName: ValidatedNel<Error.ValidationError.NameInvalidError, String>): String =
    TODO("Evaluate the `validatedName`. Return `toString()` whether the result is invalid or valid.")