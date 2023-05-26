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
    } while (!TUI.auth(insertedUIN, insertedPIN))
    TUI.writeMessage(insertedUIN)
}

fun maintenanceMode() {
    init()
    
}