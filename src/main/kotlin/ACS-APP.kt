const val testInsertedUIN = "110"
const val testInsertedPIN = "1010"
fun init() {
    HAL.init()
    KBD.init()
    LCD.init()
    DoorMechanism.init()
}


fun accessMode() {
    init()
    login()

}

fun login() {
    var insertedUIN: String
    var insertedPIN: String
    do {
        insertedUIN = TUI.readUIN("Insert UIN")
        insertedPIN = TUI.readPIN("Insert PIN")
    } while (insertedUIN != testInsertedUIN && insertedPIN != testInsertedPIN)
    TUI.writeMessage(insertedUIN)
    DoorMechanism.open(1)
    while(!DoorMechanism.finished()){}
    DoorMechanism.close(1)
}

fun maintenanceMode() {
    init()
    
}