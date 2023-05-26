object TUI {
fun readPIN(message: String): String {
    var pinArray = emptyArray<Char>()
    LCD.writeString(message)
    while(pinArray.size < 4 ) {
        LCD.cursor(0, 0)
        LCD.writeString(message)
        var curColumn = 0
        LCD.cursor(1, curColumn)
        var newKey = KBD.waitKey(5)
        LCD.writeString(newKey.toString())
        curColumn ++
        if (newKey == '*' || newKey == 'Z') {
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
fun readUIN(message: String): String {
    var uinArray = emptyArray<Char>()
    LCD.writeString(message)
    while(uinArray.size < 3) {
        uinArray += KBD.getKey()
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

