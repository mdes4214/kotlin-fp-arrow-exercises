package exercise.ch3_optics.ex5_every

import arrow.optics.Every
import arrow.optics.dsl.every
import other.model.*

fun updateAllAudioBitRate(customFiles: CustomFiles, newBitRateKBitPerS: BitRateKBitPerS): CustomFiles =
    CustomFiles.customFiles
        .every(Every.list())
        .fileFormat
        .mediaFile
        .audioFile
        .bitRateKBitPerS
        .set(customFiles, newBitRateKBitPerS)