package exercise.ch3_optics.ex1_deepcopy

@JvmInline value class Content(val value: String)
@JvmInline value class FileExtension(val value: String)
@JvmInline value class FileName(val value: String)
@JvmInline value class Title(val value: String)
@JvmInline value class Author(val value: String)

enum class Tag {
    TYPE_A, TYPE_B, TYPE_C
}

// TODO:
//  The operation of nested `data class` sometimes is ugly, e.g. deep copy.
//  Thus, please try to refactor these `data class` with `@optics`.
// hint:
//  This test will pass initially, but we want to refactor it with `@optics`.
// *** TODO section START ***

data class MyFile(
    val header: MyHeader,
    val content: Content,
    val extension: FileExtension,
    val name: FileName,
)

data class MyHeader(
    val metadata: MyMetadata
)

data class MyMetadata(
    val tag: Tag,
    val title: Title,
    val author: Author
)

fun MyFile.updateTag(newTag: Tag): MyFile =
    this.copy(
        header = this.header.copy(
            metadata = this.header.metadata.copy(
                tag = newTag
            )
        )
    )

// *** TODO section END ***