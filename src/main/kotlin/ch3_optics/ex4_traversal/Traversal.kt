package ch3_optics.ex4_traversal

import arrow.optics.Traversal
import other.model.*

fun updateAllTag(customFiles: CustomFiles, newTag: Tag): CustomFiles {
    val traversalTag =
        CustomFiles.customFiles compose
                Traversal.list() compose
                CustomFile.header compose
                CustomHeader.metadata compose
                CustomMetadata.tag

    return traversalTag.set(customFiles, newTag)
}
