package exercise.ch4_errorhandling.ex2_erroraccumulation

import arrow.core.ValidatedNel
import arrow.core.invalidNel
import arrow.core.validNel
import arrow.core.zip
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
    if (name.isBlank()) {
        Error.ValidationError.NameInvalidError.invalidNel()
    } else {
        name.validNel()
    }

fun validateInputFields(name: String, extension: String, author: String, tag: String): ValidatedNel<Error.ValidationError, SimpleFile> =
    validateName(name).zip(
        validateExtension(extension),
        validateAuthor(author),
        validateTag(tag)
    ) { validName, validExtension, validAuthor, validTag ->
        SimpleFile(validName, validExtension, validAuthor, validTag)
    }

fun evaluateValidatedName(validatedName: ValidatedNel<Error.ValidationError.NameInvalidError, String>): String =
    validatedName.fold(
        fe = { it.toString() },
        fa = { it }
    )