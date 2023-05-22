import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object User {
    private val userFile = FileAccess.openFile("users.txt")
    private var userArray = FileAccess.readFile(userFile)
    fun newUser(userName: String, PIN: Int) {
        if (!FileAccess.openFile("users.txt").exists()) {
            FileAccess.createFile("users.txt")
        }
        val newUserIN = userArray.last().split("|")[0] + 1
        FileAccess.writeFile(userFile, "$newUserIN | $userName | $PIN")
    }
    fun removeUser(UIN: Int) = FileAccess.editFile(userFile, UIN, "User $UIN removed")

    fun editPin(UIN: Int, PIN: Int) {
        val userLine = userArray[UIN].split("|")
        var newUserLine = emptyArray<String>()
        for (i in userLine.indices) newUserLine += if(i != 2) userLine[i] else PIN.toString()
        val newUserLineString = newUserLine.joinToString { " | " }
        FileAccess.editFile(userFile, UIN, "$newUserLineString")
    }

}