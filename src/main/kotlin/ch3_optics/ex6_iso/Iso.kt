package ch3_optics.ex6_iso

import arrow.core.Tuple4
import arrow.optics.Iso
import other.model.*

fun customFileIso(): Iso<CustomFile, Tuple4<CustomHeader, Content, CustomFileFormat, FileName>> =
    TODO("Write a `Iso` to convert from `CustomFile` to `Tuple4` `and reverse.")

fun CustomFile.toTuple4(): Tuple4<CustomHeader, Content, CustomFileFormat, FileName> =
    TODO("Leverage the above `Iso` to complete this function.")

fun Tuple4<CustomHeader, Content, CustomFileFormat, FileName>.toCustomFile(): CustomFile =
    TODO("Leverage the above `Iso` to complete this function.")