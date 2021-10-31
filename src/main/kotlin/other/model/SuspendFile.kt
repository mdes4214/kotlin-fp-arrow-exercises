package other.model

class SuspendFile(val fileName: String) {
    suspend fun open(): SuspendFile = this.also { println("File [${it.fileName}] opened") }
    suspend fun close(): Unit = println("File [$fileName] closed")
    suspend fun readContent(): String = "The content of [$fileName]"
}