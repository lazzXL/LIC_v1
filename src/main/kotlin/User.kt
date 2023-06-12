//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter

object User {

    var users = arrayOfNulls<Userr>(1000)

    fun usersInit(){
        users = readFile("USERS.TXT")
    }

    fun usersWrite(){
        writeFile("USERS.txt",users)
    }

    fun newUser(userName: String, PIN: Int) {
        val newUIN = users.indexOfFirst { it == null }
        val newUser = Userr(newUIN.toString(),PIN.toString(),userName,"")
        users[newUIN]=newUser
    }

    fun removeUser( UIN: Int) {
        users[UIN] = null
    }

    fun getUser(UIN: Int):Userr? = users[UIN]

    fun addUserMessage(UIN: Int, message:String){
        val us = users[UIN]  ?: return
        us.message = message
    }

    fun editPin(UIN: Int, newPIN: String) {
        val us = users[UIN] ?: return
        us.PIN = newPIN
    }

    fun checkUser(UIN: Int,PIN: String):Boolean{
        val a = getUser(UIN) ?: return false
        if(a.PIN == PIN) return true
        return false
    }

    /*
    private val userFile = FileAccess.openFile("USERS.txt")
    private var userArray = FileAccess.readFile(userFile)
    fun newUser(userName: String, PIN: Int) {
        val newUserIN = userArray.last().split(";")[0].toInt() + 1
        if (newUserIN <= 9) FileAccess.editFile(userFile,newUserIN, "00$newUserIN;$PIN;$userName")
        else if (newUserIN <= 99) FileAccess.editFile(userFile,newUserIN, "0$newUserIN;$PIN;$userName")
        else FileAccess.editFile(userFile, newUserIN,"$newUserIN;$PIN;$userName")
    }
    fun addUserMessage(UIN: Int, Message: String) = FileAccess.editFile(userFile, UIN, userArray[UIN] + ";$Message")

    fun checkUser(UIN: String, PIN: String) : Boolean = userArray[UIN.toInt()].split(";")[1] == PIN
    fun removeUser(UIN: Int) = FileAccess.editFile(userFile, UIN, "$UIN;User Removed;")

    fun checkMessage(UIN: String): Boolean = userArray[UIN.toInt()].split(";").lastIndex == 3

    fun getMessage(UIN: String) : String = userArray[UIN.toInt()].split(";")[3]

    fun getName(UIN: String) : String = userArray[UIN.toInt()].split(";")[2]

    fun editPin(UIN: Int, PIN: Int) {
        val userLine = userArray[UIN].split(";")
        var newUserLine = emptyArray<String>()
        for (i in userLine.indices) newUserLine += if (i != 1) userLine[i] else PIN.toString()
        val newUserLineString = newUserLine.joinToString { ";" }
        FileAccess.editFile(userFile, UIN, "$newUserLineString")
    }
*/
}