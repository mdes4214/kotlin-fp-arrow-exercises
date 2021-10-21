package exercise.ch3_optics.ex3_prism

import arrow.core.left
import arrow.core.right
import arrow.optics.Prism
import other.model.CustomFileFormat
import other.model.DocumentFileExtension
import other.model.documentFile
import other.model.extension

fun documentFilePrism(): Prism<CustomFileFormat, CustomFileFormat.DocumentFile> =
    Prism(
        getOrModify = { customFileFormat ->
            when (customFileFormat) {
                is CustomFileFormat.DocumentFile -> customFileFormat.right()
                else -> customFileFormat.left()
            }
        },
        reverseGet = { documentFile -> documentFile } // (::identity)
    )

fun CustomFileFormat.updateDocumentFileExtension(newExtension: DocumentFileExtension): CustomFileFormat =
    CustomFileFormat.documentFile.extension.modify(this) { newExtension }