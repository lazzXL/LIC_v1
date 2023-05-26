object TUI {
fun readPIN(): String {
    var pinArray = emptyArray<Char>()
    while(pinArray.size < 4) {
        pinArray += KBD.getKey()
    }
    return pinArray.toString()
}
fun readUIN(): String {
    var uinArray = emptyArray<Char>()
    while(uinArray.size < 3) {
        uinArray += KBD.getKey()
    }
    return uinArray.toString()
}
fun clearLCD() {

    TODO()
}
fun readObjects() {
    TODO()
}
fun writeLCD () {
    TODO()
}
}

