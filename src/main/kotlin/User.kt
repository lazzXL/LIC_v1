import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object User {
    private val userFile = FileAccess.openFile("users.txt")
    private var userArray = FileAccess.readFile(userFile)
    fun newUser(userName: String, pin: Int) {
        if (!FileAccess.openFile("users.txt").exists()) {
            FileAccess.createFile("users.txt")
        }
        val newUserIN = userArray.last().split("|")[0] + 1
        FileAccess.writeFile(userFile, "| $newUserIN | $userName | $pin")
    }
    fun removeUser(UIN: Int) = FileAccess.editFile(userFile, UIN, "User $UIN removed")


}