package other.model

data class FileNotFoundException(override val message: String) : Exception(message)

class SimpleFileStorage {
    private val filePool = listOf(
        SimpleFile(name = "Domain Modeling Made Functional", extension = ".pdf", author = "Scott Wlaschin", tag = null),
        SimpleFile(name = "screenshot_2021_10_23", extension = ".png", author = "SYSTEM", tag = "SYSTEM"),
        SimpleFile(name = "course_1005", extension = ".mp4", author = "Joe", tag = "TYPE_B"),
        SimpleFile(name = "end of a life", extension = ".mp3", author = "Calliope Mori", tag = null),
        SimpleFile(name = "RGB", extension = ".mp3", author = "YOASOBI", tag = null)
    )

    fun findFileByFileName(fileName: String): SimpleFile =
        filePool.find { "${it.name}${it.extension}" == fileName }
            ?: throw FileNotFoundException("File [${fileName}] is not found in storage.")

    fun findTagByFileName(fileName: String): String? =
        try {
            findFileByFileName(fileName).tag
        } catch (e: Exception) {
            throw e
        }
}