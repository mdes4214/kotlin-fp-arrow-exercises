package ch3_optics.ex2_lens

import arrow.optics.Lens
import other.model.CustomMetadata
import other.model.Tag

fun customMetadataTagLens(): Lens<CustomMetadata, Tag> =
    TODO("Write a `Lens` which must be able to read and modify the `CustomMetadata` `tag` property.")

fun CustomMetadata.updateTag(newTag: Tag): CustomMetadata =
    TODO("Leverage the above `Lens` to complete this function.")
