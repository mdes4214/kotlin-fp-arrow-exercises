package exercise.ch3_optics.ex2_lens

import arrow.optics.Lens
import other.model.CustomMetadata
import other.model.Tag
import other.model.tag

fun customMetadataTagLens(): Lens<CustomMetadata, Tag> =
    Lens(
        get = { customMetadata -> customMetadata.tag },
        set = { customMetadata, newTag -> customMetadata.copy(tag = newTag) }
    )

fun CustomMetadata.updateTag(newTag: Tag): CustomMetadata =
    CustomMetadata.tag.set(this, newTag)

