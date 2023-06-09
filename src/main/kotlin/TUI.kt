object TUI {

    fun readPIN(message: String): String {
        LCD.clear()
        var pinString = ""
        var curColumn = 0
        LCD.writeString(message)
        while(pinString.length < 4 ) {
            val newKey = KBD.waitKey(5000)
            if (newKey == '*') {
                if(pinString.isEmpty()){
                    LCD.clear()
                    return CANCEL
                } else {
                    return readPIN(message)
                }
            }
            else if(newKey != 'Z' && newKey != '#') {
                pinString += newKey
                LCD.cursor(1, curColumn)
                LCD.writeString("*")
                curColumn++
            } else {
                LCD.clear()
                return CANCEL
            }

        }
        LCD.clear()
        return pinString
    }


    fun readUIN(message: String): String {
        LCD.clear()
        var uinString = ""
        var curColumn = 0
        LCD.writeString(message)
        while(uinString.length < 3) {
            val newKey = KBD.waitKey(5000)
            if (newKey == '*') {
                if (uinString.isEmpty()) {
                    LCD.clear()
                    return CANCEL
                } else {
                    return readUIN(message)
                }
            } else if(newKey != 'Z') {
                uinString += newKey
                LCD.cursor(1, curColumn)
                LCD.writeString(newKey.toString())
                curColumn++
            } else {
                return CANCEL
            }
        }
        LCD.clear()
        return uinString
    }
}