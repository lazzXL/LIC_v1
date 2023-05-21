import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
object Log {
    fun newLog (event: String) {
        if (!FileAccess.openFile("log.txt").exists()) {
            FileAccess.createFile("log.txt")
        }
        val formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm:ss")
        val formattedDate = LocalDateTime.now().format(formatter)
        FileAccess.writeFile(FileAccess.openFile("log.txt"), "$formattedDate $event")
    }
}