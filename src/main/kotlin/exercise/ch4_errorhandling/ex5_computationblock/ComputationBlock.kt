package exercise.ch4_errorhandling.ex5_computationblock

import arrow.core.*
import other.model.*

sealed class Error {
    data class FileNotFoundError(val msg: String) : Error()
    object ExternalError : Error()
    sealed class ValidationError : Error() {
        object TagInvalidError : ValidationError()
        object ExtensionInvalidError : ValidationError()
        object NameInvalidError : ValidationError()
    }

    data class InvalidInputFieldsError(val nel: List<ValidationError>) : Error()
}

fun ValidatedNel<Error.ValidationError, Pair<FileName, FileExtension>>.toDomainError(): Validated<Error.InvalidInputFieldsError, Pair<FileName, FileExtension>> =
    mapLeft { Error.InvalidInputFieldsError(it) }

fun Throwable.toDomain(): Error =
    when (this) {
        is FileNotFoundException -> Error.FileNotFoundError(this.message)
        else -> Error.ExternalError
    }

@JvmInline
value class FileExtension(val value: String)

data class SafeSimpleFile(
    val name: FileName,
    val extension: FileExtension,
    val author: Author,
    val tag: Tag
)

val simpleFileStorage = SimpleFileStorage()

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

fun findInTag(tag: String?): Tag? =
    Tag.values().find { it.name == tag }


fun validateInputFields(
    fileName: String,
    fileExtension: String
): ValidatedNel<Error.ValidationError, Pair<FileName, FileExtension>> =
    validateName(fileName).zip(
        validateExtension(fileExtension)
    ) { validName, validExtension ->
        Pair(FileName(validName), FileExtension(validExtension))
    }

fun findFileByFileNameSafely(fileName: FileName, fileExtension: FileExtension): Either<Error, SimpleFile> =
    Either.catch {
        simpleFileStorage.findFileByFileName("${fileName.value}${fileExtension.value}")
    }.mapLeft { e ->
        e.toDomain()
    }

fun findTagWithDefault(tag: String?, defaultTag: Tag): Option<Tag> =
    Option.fromNullable(findInTag(tag))
        .handleError { defaultTag }

fun createSafeSimpleFile(fileName: String, fileExtension: String, defaultTag: Tag): Either<Error, SafeSimpleFile> =
    TODO("We want to do some tasks in sequence to create a `SafeSimpleFile`. Try to compose them with `either.eager {}`:\n" +
            "1. Validate the input `fileName` and `fileExtension` with calling `validateInputFields()`.\n" +
            "2. Find the file in storage with calling `findFileByFileNameSafely()`.\n" +
            "3. Find the valid enum value of `tag` or return `defaultTag` if it's invalid, with calling `findTagWithDefault()`. " +
            "   Note that you need to use `TagInvalidError` while converting `Option` to `Either` for binding.\n" +
            "4. Create a `SafeSimpleFile` with above results.")