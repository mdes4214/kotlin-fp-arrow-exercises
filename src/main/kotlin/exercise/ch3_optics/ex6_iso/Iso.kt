package exercise.ch3_optics.ex6_iso

import arrow.core.Tuple4
import arrow.optics.Iso
import other.model.*

fun customFileIso(): Iso<CustomFile, Tuple4<CustomHeader, Content, CustomFileFormat, FileName>> =
    Iso(
        get = { customFile -> Tuple4(customFile.header, customFile.content, customFile.fileFormat, customFile.name) },
        reverseGet = { tuple4 -> CustomFile(header = tuple4.first, content = tuple4.second, fileFormat = tuple4.third, name = tuple4.fourth) }
    )

fun CustomFile.toTuple4(): Tuple4<CustomHeader, Content, CustomFileFormat, FileName> =
    customFileIso().get(this)

fun Tuple4<CustomHeader, Content, CustomFileFormat, FileName>.toCustomFile(): CustomFile =
    customFileIso().reverseGet(this)