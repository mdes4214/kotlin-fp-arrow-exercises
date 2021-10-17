package other.model

import arrow.optics.optics

@JvmInline
value class Content(val value: String)

@JvmInline
value class FileName(val value: String)

@JvmInline
value class DocumentFileExtension(val value: String)

@JvmInline
value class AudioFileExtension(val value: String)

@JvmInline
value class VideoFileExtension(val value: String)

@JvmInline
value class ImageFileExtension(val value: String)

@JvmInline
value class BitRate(val value: String)

@JvmInline
value class Title(val value: String)

@JvmInline
value class Author(val value: String)

enum class Tag {
    TYPE_A, TYPE_B, TYPE_C
}

@optics
data class CustomMetadata(
    val tag: Tag,
    val title: Title,
    val author: Author
) {
    companion object
}

@optics
data class CustomHeader(
    val metadata: CustomMetadata
) {
    companion object
}

@optics
sealed class CustomFileFormat {
    companion object {}

    @optics
    data class DocumentFile(val extension: DocumentFileExtension) : CustomFileFormat() {
        companion object
    }

    @optics
    sealed class MediaFile : CustomFileFormat() {
        companion object {}

        @optics
        data class AudioFile(val extension: AudioFileExtension, val bitRate: BitRate) : MediaFile() {
            companion object
        }

        @optics
        data class VideoFile(val extension: VideoFileExtension, val bitRate: BitRate) : MediaFile() {
            companion object
        }

        @optics
        data class ImageFile(val extension: ImageFileExtension) : MediaFile() {
            companion object
        }
    }

    object UndefinedFile : CustomFileFormat()
}

@optics
data class CustomFile(
    val header: CustomHeader,
    val content: Content,
    val fileFormat: CustomFileFormat,
    val name: FileName,
) {
    companion object
}
