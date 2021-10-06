package ch3_optics.ex1_deepcopy

import arrow.optics.optics

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
// This test will pass initially, but we want to refactor it with `@optics`.
// *** TODO section START ***

@optics
data class MyFile(
    val header: MyHeader,
    val content: Content,
    val extension: FileExtension,
    val name: FileName,
) {
    companion object
}

@optics
data class MyHeader(
    val metadata: MyMetadata
) {
    companion object
}

@optics
data class MyMetadata(
    val tag: Tag,
    val title: Title,
    val author: Author
) {
    companion object
}

fun MyFile.updateTag(newTag: Tag): MyFile =
    MyFile.header.metadata.tag.set(this, newTag)


// *** TODO section END ***