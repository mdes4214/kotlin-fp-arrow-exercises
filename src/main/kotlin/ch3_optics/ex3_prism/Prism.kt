package ch3_optics.ex3_prism

import arrow.optics.Prism
import other.model.CustomFileFormat
import other.model.DocumentFileExtension

fun documentFilePrism(): Prism<CustomFileFormat, CustomFileFormat.DocumentFile> =
    TODO("Write a `Prism` that is able to focus on an `CustomFileFormat` only when it is a `DocumentFile`.")

fun CustomFileFormat.updateDocumentFileExtension(newExtension: DocumentFileExtension): CustomFileFormat =
    TODO("Complete this `updateDocumentFileExtension` function leverage the above `Prism`.")