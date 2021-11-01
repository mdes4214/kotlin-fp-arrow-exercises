package exercise.ch8_functionalarchitecture.ex1_algebras

import arrow.core.Either
import arrow.core.computations.either
import arrow.core.right
import other.model.*

sealed class Error {
    object UploadFileError : Error()
    object FileNotFound : Error()
    object InvalidTag : Error()
}

// TODO:
//  Try to refactor these methods with interface algebras.
// hint:
//  This test will pass initially, but we want to refactor these scattered functions.
//  1. We need to use an interface to create our algebras with these functions' declaration, excluding `updateOnlineFileTag()`.
//  2. Implement all the functions of the interface just like current code.
//  3. Adjust the function calling way in `updateOnlineFileTag()` to use the implementation class.
// *** TODO section START ***

interface FileOps {
    suspend fun downloadFile(fileName: FileName): Either<Error.FileNotFound, CustomFile>
    suspend fun updateTag(file: CustomFile, newTag: Tag): Either<Error.InvalidTag, CustomFile>
    suspend fun uploadFile(file: CustomFile): Either<Error.UploadFileError, CustomFile>
}

class FileService: FileOps {
    override suspend fun downloadFile(fileName: FileName): Either<Error.FileNotFound, CustomFile> =
        CustomFile(
            header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Note A"), Author("Joe"))),
            content = Content("Note A Content"),
            fileFormat = CustomFileFormat.DocumentFile(DocumentFileExtension(".doc")),
            name = FileName("Note_A")
        ).right()

    override suspend fun updateTag(file: CustomFile, newTag: Tag): Either<Error.InvalidTag, CustomFile> =
        CustomFile.header.metadata.tag.set(file, newTag).right()

    override suspend fun uploadFile(file: CustomFile): Either<Error.UploadFileError, CustomFile> =
        file.right()
}

suspend fun updateOnlineFileTag(fileName: FileName, newTag: Tag): Either<Error, CustomFile> {
    val fileService = FileService()
    val updatedFile = either<Error, CustomFile> {
        val file = fileService.downloadFile(fileName).bind()
        val updatedFile = fileService.updateTag(file, newTag).bind()
        val uploadedFile = fileService.uploadFile(updatedFile).bind()
        uploadedFile
    }
    return updatedFile
}

// *** TODO section END ***