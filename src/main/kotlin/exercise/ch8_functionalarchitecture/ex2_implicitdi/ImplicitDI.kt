package exercise.ch8_functionalarchitecture.ex2_implicitdi

import arrow.core.Either
import arrow.core.computations.either
import other.model.*

sealed class Error {
    object AuthorNotFound : Error()
    object FileNotFound : Error()
    object TagNotFound : Error()
}

interface AuthorServiceOps {
    suspend fun loadAuthors(): List<Author>
}

interface FileServiceOps {
    suspend fun findFilesByAuthor(fileNames: List<Author>): List<CustomFile>
}

interface TagDBOps {
    suspend fun findAll(): List<Tag>
}

class AuthorService : AuthorServiceOps {
    override suspend fun loadAuthors(): List<Author> =
        listOf(
            Author("Scott Wlaschin"),
            Author("Calliope Mori")
        )
}

class FileService : FileServiceOps {
    override suspend fun findFilesByAuthor(fileNames: List<Author>): List<CustomFile> =
        listOf(
            CustomFile(
                header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Domain Modeling Made Functional"), Author("Scott Wlaschin"))),
                content = Content("The useful knowledge of Domain Modeling and design mindset."),
                fileFormat = CustomFileFormat.DocumentFile(DocumentFileExtension(".pdf")),
                name = FileName("Domain Modeling Made Functional")
            ),
            CustomFile(
                header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("end of a life"), Author("Calliope Mori"))),
                content = Content("A beautiful song."),
                fileFormat = CustomFileFormat.MediaFile.AudioFile(AudioFileExtension(".mp3"), BitRateKBitPerS(320)),
                name = FileName("end of a life")
            )
        )
}

class TagRepository : TagDBOps {
    override suspend fun findAll(): List<Tag> = Tag.values().toList()
}

// TODO:
//  Try to refactor the explicit dependencies pass methods to implicitly DI.
// hint:
//  This test will pass initially, but we want to refactor these explicit dependencies passing.
//  1. We may need a abstract class to lazy initialize the dependencies.
//  2. Leverage extension functions to add our methods (exclude `loadFilesAndUpdateTagWithDependencies()`) to the abstract class.
//  3. In `loadFilesAndUpdateTagWithDependencies()`, try to use object expression to create an instance of the abstract class instead of newing dependencies directly.
// *** TODO section START ***

suspend fun findAllTags(tagRepository: TagDBOps): Either<Error, List<Tag>> =
    Either.catch {
        tagRepository.findAll()
    }.mapLeft { Error.TagNotFound }

suspend fun loadAuthors(authorService: AuthorServiceOps): Either<Error, List<Author>> =
    Either.catch {
        authorService.loadAuthors()
    }.mapLeft { Error.AuthorNotFound }

suspend fun findFilesByAuthor(fileService: FileServiceOps, authors: List<Author>): Either<Error, List<CustomFile>> =
    Either.catch {
        fileService.findFilesByAuthor(authors)
    }.mapLeft { Error.FileNotFound }

suspend fun loadFilesAndUpdateTag(fileService: FileServiceOps, authorService: AuthorServiceOps, tagRepository: TagDBOps): Either<Error, List<CustomFile>> =
    either {
        val authors = loadAuthors(authorService).bind()
        val files = findFilesByAuthor(fileService, authors).bind()
        val tags = findAllTags(tagRepository).bind()
        files.map {
            CustomFile.header.metadata.tag.set(it, tags.first())
        }
    }

suspend fun loadFilesAndUpdateTagWithDependencies(): Either<Error, List<CustomFile>> {
    val fileService = FileService()
    val authorService = AuthorService()
    val tagRepository = TagRepository()
    return loadFilesAndUpdateTag(fileService, authorService, tagRepository)
}

// *** TODO section END ***