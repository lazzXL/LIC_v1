import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object User {
    private val userFile = FileAccess.openFile("USERS.txt")
    private var userArray = FileAccess.readFile(userFile)
    fun newUser(userName: String, PIN: Int) {
        val newUserIN = userArray.last().split(";")[0].toInt() + 1
        if (newUserIN <= 9) FileAccess.writeFile(userFile, "00$newUserIN;$PIN;$userName")
        else if (newUserIN <= 99) FileAccess.writeFile(userFile, "0$newUserIN;$PIN;$userName")
        else FileAccess.writeFile(userFile, "$newUserIN;$PIN;$userName")
    }
    fun addUserMessage(UIN: Int, Message: String) = FileAccess.editFile(userFile, UIN, userArray[UIN] + ";$Message")

    fun checkUser(UIN: String, PIN: String) : Boolean = userArray[UIN.toInt()].split(";")[1] == PIN
    fun removeUser(UIN: Int) = FileAccess.editFile(userFile, UIN, "User $UIN removed")

    fun editPin(UIN: Int, PIN: Int) {
        val userLine = userArray[UIN].split(";")
        var newUserLine = emptyArray<String>()
        for (i in userLine.indices) newUserLine += if (i != 1) userLine[i] else PIN.toString()
        val newUserLineString = newUserLine.joinToString { ";" }
        FileAccess.editFile(userFile, UIN, "$newUserLineString")
    }
}

