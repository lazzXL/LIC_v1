object User {

    private var users = arrayOfNulls<UserInfo>(1000)

    fun usersInit(){
        users = FileAccess.readFile("USERS.TXT")
    }

    fun usersWrite(){
        FileAccess.writeFile("USERS.txt",users)
    }

    fun newUser(userName: String, PIN: String) {
        val newUIN = users.indexOfFirst { it == null }
        val newUser = UserInfo(newUIN.toString(),PIN,userName,"")
        users[newUIN]=newUser
    }

    fun removeUser( UIN: Int) {
        users[UIN] = null
    }

    fun getUser(UIN: Int):UserInfo? = users[UIN]

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
}