package ch3_optics.ex5_every

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import other.model.*

class EveryTest {
    @Test
    fun testUpdateAllAudioBitRate() {
        val customFiles = CustomFiles(
            listOf(
                CustomFile(
                    header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Note A"), Author("Joe"))),
                    content = Content("Note A Content"),
                    fileFormat = CustomFileFormat.DocumentFile(DocumentFileExtension(".doc")),
                    name = FileName("Note_A")
                ),
                CustomFile(
                    header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Photo A"), Author("Sam"))),
                    content = Content("(Binary Encoded)"),
                    fileFormat = CustomFileFormat.MediaFile.ImageFile(ImageFileExtension(".jpg")),
                    name = FileName("Photo_A")
                ),
                CustomFile(
                    header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Video A"), Author("Mark"))),
                    content = Content("(Binary Encoded)"),
                    fileFormat = CustomFileFormat.MediaFile.VideoFile(VideoFileExtension(".mp4"), BitRateKBitPerS(700)),
                    name = FileName("Video_A")
                ),
                CustomFile(
                    header = CustomHeader(CustomMetadata(Tag.TYPE_C, Title("Audio A"), Author("Tom"))),
                    content = Content("(Binary Encoded)"),
                    fileFormat = CustomFileFormat.MediaFile.AudioFile(AudioFileExtension(".mp3"), BitRateKBitPerS(128)),
                    name = FileName("Audio_A")
                )
            )
        )
        val actualFiles = updateAllAudioBitRate(customFiles, BitRateKBitPerS(320))
        customFiles.customFiles.zip(actualFiles.customFiles) { customFile, actualFile ->
            when (customFile.fileFormat) {
                is CustomFileFormat.MediaFile.AudioFile -> Assertions.assertEquals(320, (actualFile.fileFormat as CustomFileFormat.MediaFile.AudioFile).bitRateKBitPerS.value, "The bit rate is not updated correctly.")
                else -> Assertions.assertEquals(customFile, actualFile, "Only the Audio file need to update.")
            }
        }
    }
}