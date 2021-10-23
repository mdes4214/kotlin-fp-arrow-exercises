package other.model

class SimpleFileStorage() {
    private val filePool = listOf(
        SimpleFile(name = "FP_note", extension = ".md", author = "Joe", tag = null),
        SimpleFile(name = "screenshot_2021_10_23", extension = ".png", author = "SYSTEM", tag = "SYSTEM"),
        SimpleFile(name = "course_1005", extension = ".mp4", author = "Mark", tag = "TYPE_C"),
        SimpleFile(name = "end of a life", extension = ".mp3", author = "Calliope Mori", tag = null),
        SimpleFile(name = "RGB", extension = ".mp3", author = "YOASOBI", tag = null)
    )

    fun readFile(fileName: String): SimpleFile =
        filePool.find { "${it.name}.${it.extension}" == fileName } ?: throw Exception("File [${fileName}] not found in storage.")

    fun findTagByFileName(fileName: String): String? =
        try {
            readFile(fileName).tag
        } catch (e: Exception) {
            throw e
        }
}