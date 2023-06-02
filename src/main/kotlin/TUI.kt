object TUI {
fun readPIN(message: String): String {
    var pinArray = emptyArray<Char>()
    var curColumn = 0
    LCD.cursor(0, 0)
    LCD.writeString(message)
    while(pinArray.size < 4 ) {
        var newKey = KBD.waitKey(5000)
        if(newKey != 'Z') {
            LCD.cursor(1, curColumn)
            LCD.writeString("*")
            curColumn++
        } else {
            return errorValue
        }
        if (newKey == '*') {
            pinArray = emptyArray()
            LCD.clear()
            curColumn = 0
        } else {
            pinArray += newKey
        }
    }
    LCD.clear()
    return pinArray.toString()
}
fun auth(UIN: String, PIN: String): Boolean = User.checkUser(UIN, PIN)

fun writeMessage(UIN: String) = if (User.checkMessage(UIN)) {
        LCD.writeString(User.getName(UIN))
        LCD.cursor(1, 0)
        LCD.writeString(User.getMessage(UIN))
        if(KBD.waitKey(5000) != '*') {
            LCD.clear()
            LCD.writeString(User.getName(UIN))
        } else {
            LCD.clear()
        }

} else {
    LCD.writeString(User.getName(UIN))
}



fun readUIN(message: String): String {
    var uinArray = emptyArray<Char>()
    var curColumn = 0
    LCD.cursor(0, 0)
    LCD.writeString(message)
    while(uinArray.size < 3) {
        var newKey = KBD.waitKey(5000)
        if(newKey != 'Z') {
            LCD.cursor(1, curColumn)
            LCD.writeString(newKey.toString())
            curColumn++
        } else {
            return errorValue
        }
        if (newKey == '*') {
            uinArray = emptyArray()
            LCD.clear()
            curColumn = 0
        } else {
            uinArray += newKey
        }
    }
    LCD.clear()
    return uinArray.toString()
}

fun clearLCD() {

    TODO()
}
fun readObjects() {
    TODO()
}

}

